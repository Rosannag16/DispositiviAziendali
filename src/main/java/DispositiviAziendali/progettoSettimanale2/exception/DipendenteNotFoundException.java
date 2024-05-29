package DispositiviAziendali.progettoSettimanale2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DipendenteNotFoundException extends RuntimeException {

    public DipendenteNotFoundException(String message) {
        super(message);
    }
}
