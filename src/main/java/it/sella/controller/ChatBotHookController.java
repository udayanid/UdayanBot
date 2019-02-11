package it.sella.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatBotHookController {

	@GetMapping("/")
	public ResponseEntity<?> sayConnected() {
		return new ResponseEntity<String>("Default controller is Listening", HttpStatus.OK);
	}
}
//https://messengerdevelopers.com/resources/messaging