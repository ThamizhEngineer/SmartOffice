package com.ss.smartoffice.shared.common;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ss.smartoffice.shared.model.SmartOfficeException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
  
   //other exception handlers
  
   @ExceptionHandler(SmartOfficeException.class)
   protected ResponseEntity<Object> handleEntityNotFound(
		   SmartOfficeException soe) {
       ApiError apiError = new ApiError(HttpStatus.EXPECTATION_FAILED);
       apiError.setMessage(soe.getMessage());
       return buildResponseEntity(apiError);
   }
   private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
       return new ResponseEntity<>(apiError, apiError.getStatus());
   }
}