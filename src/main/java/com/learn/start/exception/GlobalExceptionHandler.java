package com.learn.start.exception;

import com.learn.start.response.Res;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Res<Object>> handleValidationException(MethodArgumentNotValidException ex) {
        System.out.println(" lỗi VALIDATION");

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Dữ liệu không hợp lệ");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Res<>(false, message, null));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Res<Object>> handleJsonParseError(HttpMessageNotReadableException ex) {
        System.out.println(" lỗi PARSE JSON");

        String message = "Dữ liệu gửi lên không hợp lệ. Vui lòng kiểm tra định dạng JSON.";
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Res<>(false, message, null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Res<Object>> handleAll(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Res<>(false, "Lỗi hệ thống: " + ex.getMessage(), null));
    }
}
