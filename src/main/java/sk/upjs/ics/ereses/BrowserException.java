package sk.upjs.ics.ereses;

import java.io.IOException;

public class BrowserException extends RuntimeException {

    public BrowserException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public BrowserException(String message) {
        super(message);
    }
    
    
}
