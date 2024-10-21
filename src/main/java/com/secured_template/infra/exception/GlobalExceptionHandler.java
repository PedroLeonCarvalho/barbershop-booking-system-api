package com.secured_template.infra.exception;

import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Exemplo: Entrapment de exceções de integridade de dados (exemplo de e-mail duplicado)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
String message = ex.getMostSpecificCause().getMessage();
        if (message.contains("email")) {
            message = "Já existe uma conta cadastrada com esse email.";
        }

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(message);
    }

    // Exemplo: Tratamento genérico de exceções de acesso ao banco de dados
    @ExceptionHandler (DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException(DataAccessException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocorreu um erro no banco de dados: " + ex.getMostSpecificCause().getMessage());
    }

    // Exemplo: Tratamento genérico de exceções de acesso ao banco de dados
    @ExceptionHandler (BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentials (BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Email ou senha inválidos.");
    }

    // Exemplo: Exceção genérica
    @ExceptionHandler(AppointmentTimeUnavailableException.class)
    public ResponseEntity<String> handleGenericException(ApiException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        // Verifica se a mensagem da exceção está relacionada ao token JWT
        if (ex.getMessage().equals("Token inválido ou expirado")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Token inválido ou expirado.");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocorreu um erro interno no servidor.");
    }

    @ExceptionHandler(JWTDecodeException.class)
    public ResponseEntity<String> handleGenericException(JWTDecodeException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ex.getMessage());
    }
}

