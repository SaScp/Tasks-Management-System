package ru.alex.tasksmanagementsystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.alex.tasksmanagementsystem.util.exception.ResourceNotFoundException;
import ru.alex.tasksmanagementsystem.util.exception.handler.ExceptionHandlerStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestControllerAdvice
public class GlobalControllerAdvice {

    private final Map<Class<? extends RuntimeException>, ExceptionHandlerStrategy> handlers;

    public GlobalControllerAdvice(List<ExceptionHandlerStrategy> exceptionHandlerStrategies) {
        handlers = new HashMap<>();

        for(var handler : exceptionHandlerStrategies) {
            handlers.put(handler.getExceptionClass(), handler);
        }
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ProblemDetail> globalException() {
        return ResponseEntity.internalServerError()
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "error from server"));
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ProblemDetail> exceptionHandler(RuntimeException exception, WebRequest request) {
        ExceptionHandlerStrategy exceptionHandlerStrategy = handlers.get(exception.getClass());
        ProblemDetail problemDetail = exceptionHandlerStrategy.execute(exception, request);
        return ResponseEntity.status(problemDetail.getStatus())
                .body(problemDetail);
    }

}
