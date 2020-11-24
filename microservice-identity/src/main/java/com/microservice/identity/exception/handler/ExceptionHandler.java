package com.microservice.identity.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.microservice.identity.dto.ResponseMessage;
import com.microservice.identity.exception.MicroserviceIdentityException;


@ControllerAdvice
@RestController
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public final ResponseEntity<ResponseMessage> handleInternalErrorException(Exception ex, WebRequest request) {
		HttpStatus httpStatus=null;
		ResponseMessage.ResponseMessageBuilder responseMessageBuilder=ResponseMessage.builder();
		if(ex instanceof MicroserviceIdentityException) {
			responseMessageBuilder.message(ex.getMessage());
			httpStatus=HttpStatus.valueOf(((MicroserviceIdentityException)ex).getStatusCode());
		}
		else {
			responseMessageBuilder.message(ex.getMessage());
			httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(responseMessageBuilder.build(),httpStatus);
	}
}
