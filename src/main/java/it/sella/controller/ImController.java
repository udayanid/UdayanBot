package it.sella.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import it.sella.model.im.Eventdatum;
import it.sella.model.im.MessagePayload;
import it.sella.model.im.NewChatInfo;
import it.sella.model.im.PollResponse;
import it.sella.model.im.Result;

@RestController
public class ImController {
	
	private static final Logger logger = LoggerFactory.getLogger(ImController.class);

	@GetMapping("/im/{message}")
	public ResponseEntity<?> verify(@PathVariable("message") String message) {
		String url = "https://sella.it/sellabot/chatinit?nome=nome4&cognome=cognome4&email=test3@sella.it&CHANNEL=Sella_sito_free";
		
		logger.info("url>>>>{}",url);
		RestTemplate restTemplate=new RestTemplate();
		
		
		ResponseEntity<String> indexHtml= restTemplate.getForEntity(url,String.class);
		
		if(indexHtml.getStatusCode()!=HttpStatus.FOUND) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		 }else {
			 logger.info("loggedin successfully");
		 }			
		
		HttpHeaders headers=indexHtml.getHeaders();		
		String cookieInfo=headers.getFirst("Set-Cookie");		
		final String chatUrl="https://sella.it/sellabot/execute/user/chat";				
		
		String newChatPayload="{\"action\":\"newchat\",\"sourceIntentCode\":\"\"}";
		restTemplate=new RestTemplate();
		
		headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Cookie", cookieInfo);
		
		HttpEntity<String> chatEntity=new HttpEntity<>(newChatPayload,headers);
		
		NewChatInfo newChatInfo = restTemplate.postForEntity(chatUrl, chatEntity, NewChatInfo.class).getBody();
		
		MessagePayload  messagepayload=new MessagePayload();
		messagepayload.setAction("chatevent");
		messagepayload.setIdevent("chatmessage");
		messagepayload.setChatid(newChatInfo.getChatid());
		Eventdatum eventdatum =  new Eventdatum();
		eventdatum.setName("message");
		eventdatum.setValue(message);
		messagepayload.addEventDatum(eventdatum);
		HttpEntity<MessagePayload> messageEntity=new HttpEntity<>(messagepayload,headers);
		restTemplate.postForEntity(chatUrl, messageEntity, String.class);
		
		String pollUrl = "https://sella.it/sellabot/execute/user/poll";
		String pollPayload = String.format("{\"chatid\":\"%s\"}",newChatInfo.getChatid());
		
		
		
		
		HttpEntity<String> pollEntity=new HttpEntity<>(pollPayload,headers);
		
		 StringBuffer stringBuffer=new StringBuffer();
		 
		
		for(int i=0;i<10;i++) {			
			PollResponse pollResponse = restTemplate.postForEntity(pollUrl, pollEntity, PollResponse.class).getBody();
			System.out.println("pollresponse count:::"+pollResponse.getResults().size());
			for(Result result:pollResponse.getResults()) {
				System.out.println("Result:::"+result);
				if(result.getAnswer()!=null || result.getMessage()!=null)	{
					String responseString = result.getAnswer()!=null?result.getAnswer():result.getMessage();
					stringBuffer.append(responseString).append(", ");
					 //return ResponseEntity.ok(responseString);
				 }
			}			 
		}
		return  ResponseEntity.ok(stringBuffer.toString());
		
		
	}
	

}
