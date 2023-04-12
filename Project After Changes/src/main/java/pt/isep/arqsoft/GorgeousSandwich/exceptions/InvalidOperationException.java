package pt.isep.arqsoft.GorgeousSandwich.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class InvalidOperationException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidOperationException(String message){ super(message); }
}
