package com.ferendia.emailservice.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalExceptionHandlerController {

   

	@ExceptionHandler(PermissionDeniedDataAccessException.class)
    public void handlePermissionDeniedDataAccessException(HttpServletResponse res) throws IOException {
        res.sendError(HttpStatus.FORBIDDEN.value(), "Permission denied");
    }
	
	@ExceptionHandler(CustomException.class)
	public void handleCustomException(HttpServletResponse res, CustomException ex) throws IOException {
		res.sendError(ex.getHttpStatus().value(), ex.getMessage());
	}
   
	@ExceptionHandler(MailSendException.class)
	public void handleMailSendException(HttpServletResponse res, CustomException ex) throws IOException {
		res.sendError(ex.getHttpStatus().value(), ex.getMessage());
	}
}
