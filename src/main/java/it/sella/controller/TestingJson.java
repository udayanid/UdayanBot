package it.sella.controller;

import java.text.DateFormat;
import java.util.Optional;

import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.sella.model.RequestPayload;
import it.sella.model.UserDetail;

public class TestingJson {
	public static void main(String[] args) {
		String jsonString="{ \"object\":\"page\", \"entry\":[ { \"id\":\"437062153490261\", \"time\":1539616619429, \"messaging\":[ { \"sender\":{ \"id\":\"1841499292614128\" }, \"recipient\":{ \"id\":\"437062153490261\" }, \"timestamp\":1539616618858, \"message\":{ \"mid\":\"F0LZKLRf0MQRHGQ6dYWTT4e0xl-rcOSVgGN5z_iUHtiBdMDf3S8XzLzrnz-rruC5Op_r4Bg2sBUpZb0_yGPGIw\", \"seq\":127, \"text\":\"hi\" } } ] } ] }";
		Gson gson =new GsonBuilder().setDateFormat(DateFormat.LONG).create();
		RequestPayload payload=gson.fromJson(jsonString, RequestPayload.class);
		//System.out.println(payload);
		//System.out.println(QnaResponse.getJsonResponse("111", "hi"));
		String formattedUrl = "https://graph.facebook.com/%saccess_token=%s";
		String ACCESS_TOKEN = "EAADwyglYT3gBAADvD7dQiT1ANwuht7BafeUUmp0ATRfL5XwJTgEaqG5URT7zGeZCbb8Hmwkp7Iy9vjrvd7sJISIWNDPsqMRUZB29jdU4Rp1qaseqsLaG1xxdnjOXmzjy3ZAS1JgmZAcjDbanwOvWZBI5tU6oZBmBDhvCOlVydZBjgZDZD";
		String url = String.format(formattedUrl, "1841499292614128?fields=first_name,last_name,profile_pic&", ACCESS_TOKEN);
		RestTemplate restTemplate=new RestTemplate();	
		UserDetail userDetail = restTemplate.getForObject(url, UserDetail.class);
		System.out.println("userDetail"+userDetail);
		
		
		String jsonResponse="{ \"recipient\": { \"id\": \"recipientId\" }, \"message\": { \"text\": \"%s\" } }";
		System.out.println(String.format(jsonResponse,"hello moto"));
	      Integer value1 = null;
	      Integer value2 = new Integer(10);
			
	      //Optional.ofNullable - allows passed parameter to be null.
	      Optional<Integer> a = Optional.ofNullable(value1);
	      Integer s=a.orElse(30);
	      System.out.println("a"+s);
			
	      //Optional.of - throws NullPointerException if passed parameter is null
	      Optional<Integer> b = Optional.of(value2);
	      System.out.println(b);
	}
}
