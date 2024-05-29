package DispositiviAziendali.progettoSettimanale2.exception;

import DispositiviAziendali.progettoSettimanale2.exception.DipendenteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DipendenteNotFoundException.class)
    public ResponseEntity<Object> handleDipendenteNotFoundException(DipendenteNotFoundException ex) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DispositivoNotFoundException.class)
    public ResponseEntity<Object> handleDispositivoNotFoundException(DispositivoNotFoundException ex) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }



}
