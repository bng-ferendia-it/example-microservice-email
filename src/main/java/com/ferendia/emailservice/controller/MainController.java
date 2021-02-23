package com.ferendia.emailservice.controller;

import java.util.HashMap;

import com.ferendia.emailservice.dto.EmailDTO;
import com.ferendia.emailservice.dto.RequestDTO;
import com.ferendia.emailservice.exception.CustomException;
import com.ferendia.emailservice.model.TokenType;
import com.ferendia.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
public class MainController {
	
	@Autowired
    EmailService emailService;
	
	
	@GetMapping("/")
	public String helloTest() {
		return "micro-service-email";
	}
	
	@PostMapping("/send/request")
	public HashMap<String, String> sendRequest(@RequestBody RequestDTO requestBody) {
		HashMap<String, String> response = new HashMap<>();
		
		if(requestBody == null) {
			throw new CustomException("Body element empty", HttpStatus.BAD_REQUEST);
		}
		
		emailService.sendRequestEmail(requestBody.getEmail(),requestBody.getRequestToken(), TokenType.valueOf(requestBody.getRequestType()));
		response.put("message", "Email sent");
		return response;
		
	}

	@PostMapping("/send/email")
	public HashMap<String, String> sendEmail(@RequestBody EmailDTO emailDTO) {
		HashMap<String, String> response = new HashMap<>();

		if(emailDTO == null) {
			throw new CustomException("Body element empty", HttpStatus.BAD_REQUEST);
		}

		if(emailDTO.getEmail().isEmpty() || emailDTO.getEmail().isBlank()) {
			throw new CustomException("Email are empty", HttpStatus.BAD_REQUEST);
		}
		if(emailDTO.getObject().isEmpty() || emailDTO.getObject().isBlank()) {
			throw new CustomException("Object are empty", HttpStatus.BAD_REQUEST);
		}
		if(emailDTO.getBody().isEmpty() || emailDTO.getBody().isBlank()) {
			throw new CustomException("Body are empty", HttpStatus.BAD_REQUEST);
		}

		emailService.sendTextMail(emailDTO.getEmail(),emailDTO.getObject(),emailDTO.getBody());
		response.put("message", "Email sent");
		return response;

	}
	

}
