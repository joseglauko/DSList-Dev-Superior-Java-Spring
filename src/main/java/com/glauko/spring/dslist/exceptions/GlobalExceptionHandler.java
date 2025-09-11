package com.glauko.spring.dslist.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { GameNotFoundException.class })
    public ProblemDetail handleGameNotFound(GameNotFoundException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Recurso não encontrado");
        problemDetail.setType(URI.create("https://localhost:8080/errors/game-not-found"));
        problemDetail.setProperty("path", request.getRequestURI());
        return problemDetail;
    }
    /*
    public ResponseEntity<ApiError> handleGameNotFound(GameNotFoundException e, HttpServletRequest request) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now(), request.getRequestURI());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
    */

    @ExceptionHandler(value = { GameListNotFoundException.class })
    public ProblemDetail handleGameListNotFound(GameListNotFoundException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Recurso não encontrado");
        problemDetail.setType(URI.create("https://localhost:8080/errors/game-list-not-found"));
        problemDetail.setProperty("path", request.getRequestURI());
        return problemDetail;
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ProblemDetail handleIndexOutOfBounds(Exception ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Você passou indíces fora do limite da lista!");
        problemDetail.setTitle("Indíces fora do alcance da lista");
        problemDetail.setType(URI.create("https://localhost:8080/errors/index-out-of-bounds"));
        problemDetail.setProperty("path", request.getRequestURI());
        return problemDetail;
    }


    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor");
        problemDetail.setTitle("Erro inesperado");
        problemDetail.setType(URI.create("https://localhost:8080/errors/internal-server-error"));
        problemDetail.setProperty("path", request.getRequestURI());
        return problemDetail;
    }



    /*
    public ResponseEntity<ApiError> handleGenericException(Exception ex, HttpServletRequest request) {
        ApiError error = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor!",
                LocalDateTime.now(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
     */
}
