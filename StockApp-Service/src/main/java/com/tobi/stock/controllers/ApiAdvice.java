package com.tobi.stock.controllers;

import com.tobi.stock.controllers.api.Response;
import com.tobi.stock.controllers.api.Error;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 25/09/2019
 * Time: 5:01 PM
 **/


@ControllerAdvice(annotations = RestController.class)
@ResponseBody
public class ApiAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleValidationExceptions(MethodArgumentNotValidException ex) {
        Response response = new Response();
        response.setCode("10400");
        response.setDescription("Bad request");
        BindingResult result = ex.getBindingResult();
        List<FieldError> errorList = result.getFieldErrors();
        List<Error> errors = new ArrayList<>();
        for (FieldError fieldError : errorList) {
            errors.add(new Error(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        response.setErrors(errors);
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadCredentialsException.class)
    public Response handleValidationExceptions(BadCredentialsException ex) {
        Response response = new Response("10400", "Bad request");
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("header", ex.getMessage()));
        response.setErrors(errors);
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response handleValidationExceptions(HttpRequestMethodNotSupportedException ex) {
        Response response = new Response("10405", "Request method not supportedst");
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("header", ex.getMessage()));
        response.setErrors(errors);
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JwtException.class)
    public Response handleValidationExceptions(JwtException ex) {
        Response response = new Response("10505", "Expired or invalid JWT token");
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("header", ex.getMessage()));
        response.setErrors(errors);
        return response;
    }
}
