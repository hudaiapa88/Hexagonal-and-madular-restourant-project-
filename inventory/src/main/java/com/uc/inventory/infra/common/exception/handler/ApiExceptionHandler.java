package com.uc.inventory.infra.common.exception.handler;

import com.uc.inventory.domain.common.exception.base.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice(name = "inventoryApiExceptionHandler")
@RequiredArgsConstructor
public class ApiExceptionHandler  {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<ErrorResponse> errorResponseList = new ArrayList<>();
        ex.getBindingResult().getAllErrors().stream().forEach((error)->{
            ErrorResponse errorResponse= new ErrorResponse();
            errorResponse.setExceptionClass(ex.getNestedPath());
            errorResponse.setField(ex.getFieldError().getField());
            errorResponse.setMessage(error.getDefaultMessage());
            errorResponseList.add(errorResponse);

        });
        return new ResponseEntity<>(errorResponseList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Object> baseException(BaseException e) {
        List<ErrorResponse> errorResponseList = new ArrayList<>();
        ErrorResponse errorResponse= new ErrorResponse();
        errorResponse.setExceptionClass(e.getClass().getSimpleName());
        errorResponse.setField(e.getField());
        errorResponse.setMessage(e.getMessage());
        errorResponseList.add(errorResponse);
        return new ResponseEntity<>(errorResponseList, e.getHttpStatus());
    }


  /*  @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> accessDeniedException(RuntimeException e) {
        return new ResponseEntity<>("Yetkisiz Giriş", HttpStatus.FORBIDDEN);
    }*/

}
