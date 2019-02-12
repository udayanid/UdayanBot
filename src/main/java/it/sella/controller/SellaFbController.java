
package it.sella.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import it.sella.BotSession;
import it.sella.JsonUtil;
import it.sella.model.Entry;
import it.sella.model.Messaging;
import it.sella.model.RequestPayload;
import it.sella.model.SendMessageAcknowledgement;
import it.sella.model.UserDetail;
import it.sella.model.im.ChatResponse;
import it.sella.model.im.Eventdatum;
import it.sella.model.im.MessagePayload;
import it.sella.model.im.NewChatInfo;
import it.sella.model.im.PollResponse;
import it.sella.model.im.Result;



@RestController
public class SellaFbController {
	Map<String, BotSession> botSessionMap = new HashMap<String, BotSession>();
    //https://sella.it/sellabot/chatinit?nome=nome1&cognome=cognome1&email=test@sella.it&CHANNEL=Sella_sito_free
	private static final String SIGNATURE_HEADER_NAME = "X-Hub-Signature";
	private static final String ACCESS_TOKEN = "EAADwyglYT3gBACIGJ5VroCVMAiZAtbW2zsKihP6iClcAeCDrPgusQQNuI6jPvEshBF0TgwW2CzVRIQZCf5ZC6uYe8CXMTY8cnat4OfBgJzsWZAZCRDWaw9N29ZCsy2KZCcCS5mvRmooIkbB3TclHrJIAZAah0SPJTVJ2g2ZB9fExG9w0QmPyWRyQR";
	private static final String FB_GRAPH_API_URL_MESSAGES = "https://graph.facebook.com/v2.6/me/messages?access_token=%s";
	private static final String IM_LOGIN_URL = "https://sella.it/sellabot/chatinit?nome=%s&cognome=%s&email=test3@sella.it&CHANNEL=Sella_sito_free";
	private static final String CHAT_URL="https://sella.it/sellabot/execute/user/chat";
    private static final String POLL_URL="https://sella.it/sellabot/execute/user/poll";

	private static final Logger logger = LoggerFactory.getLogger(SellaFbController.class);
	@GetMapping("/webhook")
	public ResponseEntity<?> verify(@RequestParam("hub.challenge") String challenge,
			@RequestParam("hub.verify_token") String token) {
		logger.info("<<<<<<<<<<<<<Challenge is:{} and token is {}>>>>>>>>>>>", challenge, token);
		if (token.equals("sellatoken123"))
			return new ResponseEntity<String>(challenge, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
	
	@PostMapping(path = "/webhook", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> getMessage(@RequestBody final String payLoad,
			@RequestHeader(SIGNATURE_HEADER_NAME) final String signature) {
		logger.info("<<<<<<<<<Response payload:{} && signature: {}>>>>>>>>>>", payLoad, signature);
		RequestPayload reqPayload=getResponseObject(payLoad);
		logger.info("<<<<<<<<<<<<<<<<reqpayload>>>>{}>>>>>>>>>>>>>",reqPayload);
		final String senderId = reqPayload.getEntry().get(0).getMessaging().get(0).getSender().getId();
		final String recipientId = reqPayload.getEntry().get(0).getMessaging().get(0).getRecipient().getId();
		logger.info("<<<<<<<<<<senderId>>>>{},RecipientId>>>{}>>>>>>>>>>>>>>>", senderId, recipientId);
		final UserDetail userDetail = getUserDetail(senderId);
		String eventType=getEventType(reqPayload);		
		BotSession botSession=null;		
		if(botSessionMap.containsKey(recipientId)) {
			botSession = (BotSession) botSessionMap.get(recipientId);
			logger.info("<<<<<<<<<<<<<<<botsession available>>>>>>>>>>>>>>>>>");
		}else {
			ResponseEntity<String> imLoginResponseEntity = imLogin(userDetail);
			if (imLoginResponseEntity.getStatusCode() != HttpStatus.FOUND) {
				logger.info("<<<<<<<<<<Login failed>>>>>>>>>");
			} else {
				logger.info("<<<<<<<<<<Loggedin successfully>>>>>>>>>");
				botSession = new BotSession();
				botSession.setFbReceipientId(recipientId);
				botSession.setFbSenderId(senderId);
				botSession.setImChatId(getChatId(imLoginResponseEntity.getHeaders().getFirst("Set-Cookie")));
				botSession.setCokkieInfo(imLoginResponseEntity.getHeaders().getFirst("Set-Cookie"));
				botSessionMap.put(recipientId, botSession);
			}
			
		}
		
		for (Entry entry : reqPayload.getEntry()) {
			for (Messaging messaging : entry.getMessaging()) {
				final String textMessage = eventType.equals("PostbackEvent") ? messaging.getPostback().getPayload()	: messaging.getMessage().getText();
				logger.info("<<<<<<<<<<<<TextMessage::{},EventyType:::{}>>>>>>>>>>>>>>", textMessage, eventType);
				logger.info("<<<<<<<<<<<<BotSession ::{}>>>>>>>>>>>>>", botSession);
				String senderActionAcknowledge = sendMessage(getSenderActionResonse("mark_seen", senderId));
				logger.info("<<<<<<<<<<senderActionAcknowledge::::{}>>>>>>>>>>>>", senderActionAcknowledge);
				senderActionAcknowledge = sendMessage(getSenderActionResonse("typing_on", senderId));
				logger.info("<<<<<<<<<<<<<senderActionAcknowledge::::{}>>>>>>>>>>>>>>", senderActionAcknowledge);
				logger.info("<<<<<<<<<<<<<ChatId As of Now::::{}>>>>>>>>>>>>>>", botSession.getImChatId());
				try {
					getPollResponse(senderId, botSession.getImChatId(),botSession.getCokkieInfo().concat("hello"),1);

				}catch(Exception e) {
					logger.info("thi is the error we got::{}",e.getMessage(),e);
				}
				ChatResponse chatResponse = sendImMessage(botSession.getImChatId(), textMessage, botSession.getCokkieInfo()).getBody();
				logger.info("<<<<<<<<<ChatResponse:::{}",chatResponse);
				if(chatResponse.getStatus().equals("EXCEPTION")) {
					final String chatId = getChatId(botSession.getCokkieInfo());
	            	botSession.setImChatId(chatId);
	            	botSessionMap.put(recipientId, botSession);
	            	logger.info("<<<<<<Newww chatId:::{}>>>>>>>",chatId);
	            	sendImMessage(chatId, textMessage, botSession.getCokkieInfo());
	            }
				getPollResponse(senderId, botSession.getImChatId(), botSession.getCokkieInfo(), 10);
				// sendMessage(QnaResponse.getJsonResponse(senderId,textMessage!=null?textMessage.toLowerCase():"",userDetail));
				senderActionAcknowledge = sendMessage(getSenderActionResonse("typing_off", senderId));
				logger.info("senderActionAcknowledge>>>>{}", senderActionAcknowledge);
			}
		}		
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	
	public String sendMessage(String payLoad) {
		logger.info("<<<<<<<current log:::{}>>>>>",payLoad);
		String url = String.format(FB_GRAPH_API_URL_MESSAGES, ACCESS_TOKEN);
		logger.info("<<<<<<<<FB_GRAPH_API_URL_MESSAGES:::::::{}>>>>>>>>>>>",url);
		RestTemplate restTemplate=new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);	
		HttpEntity<String> entity = new HttpEntity<String>(payLoad, headers);		
		return restTemplate.postForObject(url, entity, String.class);
	}
	
	public UserDetail getUserDetail(String senderId) {
		String formattedUrl = "https://graph.facebook.com/%saccess_token=%s";
		String url = String.format(formattedUrl, senderId + "?fields=first_name,last_name,profile_pic&", ACCESS_TOKEN);
		RestTemplate restTemplate=new RestTemplate();	
		UserDetail userDetail = restTemplate.getForObject(url, UserDetail.class);
		return userDetail;
	}
	
	
	private String getSenderActionResonse(final String senderAction, final String senderId) {
		return String.format("{ \"recipient\":{ \"id\":\"%s\" }, \"sender_action\":\"%s\" }", senderId,senderAction);
	}	
	

	//to get the json to gson object
	public static RequestPayload getResponseObject(final String responsePayload) {		
		return JsonUtil.getInstance().fromJson(responsePayload, RequestPayload.class);
	}
	
	//to get the requested eventype
	private String getEventType(RequestPayload requestPayload) {
		String eventType="TextEvent";
		if(requestPayload.getEntry().get(0).getMessaging().get(0).getPostback()!=null) {
			eventType="PostbackEvent";
		}
		logger.info("The requested event Tyepe {}",eventType);
		return eventType;		
	}	
	
	private ResponseEntity<String> imLogin(final UserDetail userDetail){
		String url = String.format(IM_LOGIN_URL, userDetail.getFirstName(), userDetail.getLastName());
		logger.info("IM chat init url>>>>{}", url);		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> imLoginResponseEntity = restTemplate.getForEntity(url, String.class);
		return imLoginResponseEntity;
	}
	
	
	private String getChatId(String cookieInfo) {		
		String newChatPayload = "{\"action\":\"newchat\",\"sourceIntentCode\":\"\"}";
		final RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Cookie", cookieInfo);
		final HttpEntity<String> chatEntity = new HttpEntity<>(newChatPayload, headers);
		final NewChatInfo newChatInfo = restTemplate.postForEntity(CHAT_URL, chatEntity, NewChatInfo.class).getBody();
		logger.info("<<<<<<<<ChatId:::{}>>>>>>>",newChatInfo.getChatid());
		return newChatInfo.getChatid();
	}
	
	private ResponseEntity<ChatResponse> sendImMessage(final String chatId, final String fbMessage, final String cookieInfo) {
		final MessagePayload messagepayload = new MessagePayload();
		messagepayload.setAction("chatevent");
		messagepayload.setIdevent("chatmessage");
		messagepayload.setChatid(chatId);
		final Eventdatum eventdatum = new Eventdatum();
		eventdatum.setName("message");
		eventdatum.setValue(fbMessage);
		messagepayload.addEventDatum(eventdatum);
		logger.info("<<<<<<<<<<<<<<<<messagePayload::{}>>>>>>>>>>>",messagepayload);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Cookie",cookieInfo);
		final HttpEntity<MessagePayload> messageEntity = new HttpEntity<>(messagepayload, headers);
		final RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ChatResponse> sendMessageResponseEntity = restTemplate.postForEntity(CHAT_URL, messageEntity, ChatResponse.class);
		return sendMessageResponseEntity;
	}
	
	private void getPollResponse(final String recipientId, String chatId, final String cookieInfo, int totalPolls) {
		String pollPayload = String.format("{\"chatid\":\"%s\"}", chatId);
		logger.info("<<<<<<<<<pollpayload::{}>>>>>>>>>>>>>>>", pollPayload);
		final RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Cookie", cookieInfo);
		HttpEntity<String> pollEntity = new HttpEntity<>(pollPayload, headers);
		for (int i = 0; i < 5; i++) {
			PollResponse pollResponse = restTemplate.postForEntity(POLL_URL, pollEntity, PollResponse.class).getBody();
			logger.info("<<<<<<<<<<<<<poll No.{} =>Total  Results :::{}>>>>>>>>>>>", (i + 1), pollResponse.getResults().size());
			logger.info("<<<<<<<<<<<<PollResponse Status :::{}>>>>>>>>>>>", pollResponse.getStatus());
			for (Result result : pollResponse.getResults()) {
				logger.info("<<<<<<<<<<<<Each Result :::{}>>>>>>>>>>>>>", result);
				final String answer = result.getAnswer();
				final String message = result.getMessage();
				if (answer != null && !answer.isEmpty()) {					
					String imResponse = String.format("{ \"recipient\": { \"id\": \"%s\" }, \"message\": { \"text\": \"%s\" } }", recipientId, answer);
					String fbAcknowledgement = sendMessage(imResponse);
					logger.info("<<<<Answer Acknowledgement of fb:::{}>>>>>",fbAcknowledgement);
					if (result.getLink() != null && !result.getLink().isEmpty()) {
						imResponse = String.format("{ \"recipient\":{ \"id\":\"%s\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"open_graph\", \"elements\":[ { \"url\":\"%s\", \"buttons\":[ { \"type\":\"web_url\", \"url\":\"https://www.sella.it\", \"title\":\"View More\" } ] } ] } } } }",	recipientId, result.getLink());
						sendMessage(imResponse);
						logger.info("<<<<Acknowledgement of fb link:::{}>>>>>",fbAcknowledgement);
					}
				}else if(message!=null && !message.isEmpty()) {
					String imResponse = String.format("{ \"recipient\": { \"id\": \"%s\" }, \"message\": { \"text\": \"%s\" } }", recipientId, message);
					String fbAcknowledgement = sendMessage(imResponse);
					logger.info("<<<<Message Acknowledgement of fb:::{}>>>>>",fbAcknowledgement);

				}
			}
			try {
				Thread.sleep(new Long(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
	

	
	
}
