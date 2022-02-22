package com.example.restservice.exception;
import com.example.restservice.exception.dto.ErrorDetailsDTO;
import com.example.restservice.exception.dto.ErrorInfoDTO;
import com.example.restservice.util.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class LoanExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetailsDTO> handleInternalError(final RuntimeException ex) {
        return createResponseError(ex, ErrorType.GENERIC_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(LoanException.class)
    public final ResponseEntity<ErrorDetailsDTO> handleLoanException(final LoanException ex) {
        return createResponseError(ex, ex.getError(), HttpStatus.valueOf(ex.getHttpStatus()));
    }

    @ExceptionHandler(InvalidLoanTypeException.class)
    public final ResponseEntity<ErrorDetailsDTO> handleInvalidLoanTypeException(final LoanException ex) {
        return createResponseError(ex, ex.getError(), HttpStatus.valueOf(ex.getHttpStatus()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorDetailsDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });
        log.error("Error: [{}]", errors, ex);
        ErrorDetailsDTO detalleErrorDTO = new ErrorDetailsDTO(new ErrorInfoDTO(ErrorType.ERROR_DATA.getCode(),
                errors.toString(), ErrorType.ERROR_DATA.getDescription()));
        return new ResponseEntity<>(detalleErrorDTO, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorDetailsDTO> createResponseError(final Exception ex, final ErrorType error, final HttpStatus httpStatus) {
        log.error("Error: [{}]", error, ex);
        final ErrorDetailsDTO errorDetail = new ErrorDetailsDTO(new ErrorInfoDTO(error.getCode(), ex.getMessage(), error.getDescription()));
        return new ResponseEntity<>(errorDetail, httpStatus);
    }

}
