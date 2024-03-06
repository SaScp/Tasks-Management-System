package ru.alex.tasksmanagementsystem.util.exception.handler;

import org.springframework.context.MessageSource;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import ru.alex.tasksmanagementsystem.util.exception.ResourceNotFoundException;

import java.util.Locale;

@Component
public class ResourceNotFoundExceptionHandler implements ExceptionHandlerStrategy {

    @Override
    public ProblemDetail execute(RuntimeException exception, WebRequest request) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @Override
    public Class<? extends RuntimeException> getExceptionClass() {
        return ResourceNotFoundException.class;
    }
}
