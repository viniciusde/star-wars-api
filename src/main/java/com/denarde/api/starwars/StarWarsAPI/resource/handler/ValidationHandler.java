package com.denarde.api.starwars.StarWarsAPI.resource.handler;

import com.denarde.api.starwars.StarWarsAPI.exception.BusinessException;
import com.denarde.api.starwars.StarWarsAPI.exception.dto.ErrorMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.ValidationException;
import java.util.stream.Collectors;

import static java.text.MessageFormat.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@ControllerAdvice
public class ValidationHandler {

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    void exceptionHandler(ValidationException e) {
        throw new BusinessException(BAD_REQUEST, e.getMessage(), e);
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    ErrorMessageDTO exceptionHandler(ConstraintViolationException e, HttpServletRequest httpServletRequest) {
        return ErrorMessageDTO.builder()
                .message(e.getConstraintViolations()
                        .parallelStream()
                        .map(objectError -> format("{0}: {1}", ((FieldError) objectError).getField(), (objectError).getMessage()))
                        .sorted()
                        .collect(Collectors.toList()))
                .error(e.getClass().getSimpleName())
                .path(httpServletRequest.getRequestURI())
                .build();
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ErrorMessageDTO handleValidationExceptions(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest) {
        return ErrorMessageDTO.builder()
                .message(e.getBindingResult()
                        .getAllErrors()
                        .parallelStream()
                        .map(objectError -> format("{0}: {1}", ((FieldError) objectError).getField(), (objectError).getDefaultMessage()))
                        .sorted()
                        .collect(Collectors.toList()))
                .error(e.getClass().getSimpleName())
                .path(httpServletRequest.getRequestURI())
                .build();
    }
}
