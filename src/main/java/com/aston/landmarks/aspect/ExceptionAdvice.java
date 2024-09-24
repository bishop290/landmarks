package com.aston.landmarks.aspect;

import com.aston.landmarks.dtos.DefaultResponse;
import com.aston.landmarks.exceptions.locality.LocalityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(LocalityNotFoundException.class)
    public ResponseEntity<DefaultResponse> handleLocalityNotFoundException(LocalityNotFoundException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(
                new DefaultResponse(false, e.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = createMessage(e.getBindingResult());
        log.error(message);
        return new ResponseEntity<>(
                new DefaultResponse(false, message), HttpStatus.BAD_REQUEST);
    }

    private String createMessage(BindingResult result) {
        List<String> messages = result.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        return String.join("; ", messages);
    }
}