package com.carlease.car.exception;



import com.carlease.car.config.CarErrorCodeConfig;
import com.carlease.car.model.error.CarErrorModel;
import com.carlease.car.model.error.CarRequestErrorModel;
import com.carlease.car.model.error.ErrorSeverityLevelCodeType;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the custom exception handler class
 */
@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity(body, headers, status);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        final CarRequestErrorModel error = new CarRequestErrorModel(errors, CarErrorCodeConfig.INVALID_INPUT, ErrorSeverityLevelCodeType.ERROR);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(CarNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> handleNotFoundException(CarNotFoundException ex) {
        final CarErrorModel error = new CarErrorModel(ex.getMessage(), CarErrorCodeConfig.INVALID_INPUT, ErrorSeverityLevelCodeType.ERROR);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

   

    @ExceptionHandler(CarDuplicationException.class)
    @ResponseBody
    public ResponseEntity<Object> handleNotFoundException(CarDuplicationException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        final CarErrorModel error = new CarErrorModel(ex.getMessage(), CarErrorCodeConfig.INVALID_INPUT, ErrorSeverityLevelCodeType.ERROR);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler({IllegalArgumentException.class, InvalidDataAccessApiUsageException.class})
    @ResponseBody
    public ResponseEntity<Object> handleArgumentException(Exception ex) {
        final CarErrorModel error = new CarErrorModel(ex.getMessage(), CarErrorCodeConfig.INVALID_INPUT, ErrorSeverityLevelCodeType.ERROR);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String message = ex.getMessage();
        if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
            message = CarErrorCodeConfig.DB_ERROR;
        }

        final CarErrorModel error = new CarErrorModel(message, CarErrorCodeConfig.DB_ERROR, ErrorSeverityLevelCodeType.ERROR);
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public final ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        final CarErrorModel error = new CarErrorModel(ex.getMessage(), CarErrorCodeConfig.DB_ERROR, ErrorSeverityLevelCodeType.ERROR);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        final CarErrorModel error = new CarErrorModel(ex.getMessage(), CarErrorCodeConfig.INTERNAL_ERROR, ErrorSeverityLevelCodeType.ERROR);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleGlobalException(Exception ex) {
        final CarErrorModel error = new CarErrorModel(ex.getMessage(), CarErrorCodeConfig.GLOBAL_ERROR, ErrorSeverityLevelCodeType.ERROR);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}
