package ru.alex.tasksmanagementsystem.util.exception.handler;

import org.springframework.http.ProblemDetail;
import org.springframework.web.context.request.WebRequest;

import java.util.Locale;

public interface ExceptionHandlerStrategy {

    ProblemDetail execute(RuntimeException exception, WebRequest request);

    Class<? extends RuntimeException> getExceptionClass();
}
