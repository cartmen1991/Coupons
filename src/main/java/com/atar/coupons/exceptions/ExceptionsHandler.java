package com.atar.coupons.exceptions;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.atar.coupons.beans.ErrorBean;
import com.atar.coupons.enums.ErrorTypes;


//Exception handler class
@RestControllerAdvice
public class ExceptionsHandler {

	// Response - Object in spring
	@ExceptionHandler
	@ResponseBody

	// Variable name is throwable in order to remember that it handles exceptions and errors
	public ErrorBean toResponse(Throwable throwable, HttpServletResponse response) {

		// ErrorBean errorBean;

		if(throwable instanceof ApplicationException) {
			response.setStatus(700);
			ApplicationException applicationException = (ApplicationException) throwable;

			ErrorTypes errorType = applicationException.getErrorType();
			int errorNumber = errorType.getErrorNumber();
			String errorMessage = errorType.getErrorMessage(); 
			String errorName = errorType.getErrorName();

			ErrorBean errorBean = new ErrorBean(errorNumber, errorMessage, errorName);

			if(applicationException.getErrorType().isShowStackTrace()) {
				applicationException.printStackTrace();
			}

			return errorBean;
		}

		response.setStatus(600);

		String errorMessage = throwable.getMessage(); 
		ErrorBean errorBean = new ErrorBean(600, errorMessage, "General error");
		throwable.printStackTrace();

		return errorBean;
	}
}
