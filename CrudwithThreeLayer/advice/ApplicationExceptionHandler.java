package com.hb.crud.CrudwithThreeLayer.advice;

import com.hb.crud.CrudwithThreeLayer.response.ApiErrorResponse;
import com.hb.crud.CrudwithThreeLayer.response.ResponseData;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidException(HttpClientErrorException.Forbidden ex){
        ApiErrorResponse apiError= ResponseData.setApiErrorResponse(null,ex.getMessage(),null);
        return new ResponseEntity<ApiErrorResponse>(apiError,HttpStatus.FORBIDDEN);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiErrorResponse apiError= ResponseData.setApiErrorResponse(null, ex.getMessage(), null); //"Not readable, write valid inputs..."
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiErrorResponse apiError= ResponseData.setApiErrorResponse(null,"Type mismatched...",null);
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }



    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiErrorResponse apiError= ResponseData.setApiErrorResponse(null,ex.getMessage(),null);
        return new ResponseEntity<>(apiError,status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiErrorResponse apiError= ResponseData.setApiErrorResponse(null,ex.getMessage(),null);
        return new ResponseEntity<>(apiError,status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiErrorResponse apiError= ResponseData.setApiErrorResponse(null,ex.getMessage(),null);
        return new ResponseEntity<>(apiError,status);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiErrorResponse apiError= ResponseData.setApiErrorResponse(null,ex.getMessage(),null);
        return new ResponseEntity<>(apiError,status);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiErrorResponse> handleEntityNotFound(NoSuchElementException ex){
        ApiErrorResponse apiError= ResponseData.setApiErrorResponse(null,ex.getMessage(),null);
        return new ResponseEntity<ApiErrorResponse>(apiError,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ApiErrorResponse> handleEntityNotFound(EmptyResultDataAccessException ex){
        ApiErrorResponse apiError= ResponseData.setApiErrorResponse(null, ex.getMessage(),null);
        return new ResponseEntity<ApiErrorResponse>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleAllException(Exception ex){
        ApiErrorResponse apiError= ResponseData.setApiErrorResponse(null, ex.getMessage(),null);
        return new ResponseEntity<ApiErrorResponse>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ApiErrorResponse> handleMalformedJwtException(MalformedJwtException ex){
        ApiErrorResponse apiError= ResponseData.setApiErrorResponse(null, ex.getMessage(),null);
        return new ResponseEntity<ApiErrorResponse>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> entityNotFoundException(EntityNotFoundException ex){
        ApiErrorResponse apiError= ResponseData.setApiErrorResponse(null, ex.getMessage(),null);
        return new ResponseEntity<ApiErrorResponse>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiErrorResponse> expiredTokenException(ExpiredJwtException ex){
        ApiErrorResponse apiError= ResponseData.setApiErrorResponse(null, ex.getMessage(),null);
        return new ResponseEntity<ApiErrorResponse>(apiError,HttpStatus.BAD_REQUEST);
    }





}
