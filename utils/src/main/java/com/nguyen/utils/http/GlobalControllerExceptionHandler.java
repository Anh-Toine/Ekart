package com.nguyen.utils.http;

import com.nguyen.utils.exceptions.InvalidInputException;
import com.nguyen.utils.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class GlobalControllerExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public EkartErrorInfo handleNotFoundException(ServerHttpRequest request,Exception e){
        return generateError(NOT_FOUND,request,e);
    }

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(InvalidInputException.class)
    public EkartErrorInfo handleInvalidInputException(ServerHttpRequest request,Exception e){
        return generateError(UNPROCESSABLE_ENTITY,request,e);
    }
    private EkartErrorInfo generateError(HttpStatus status, ServerHttpRequest request, Exception e) {
        final String errorPath = request.getPath().pathWithinApplication().value();
        final String message = e.getMessage();
        LOGGER.debug("Returning HTTP status: {} for path {} with message: {}",status,errorPath,message);
        return new EkartErrorInfo(status,errorPath,message);
    }
}
