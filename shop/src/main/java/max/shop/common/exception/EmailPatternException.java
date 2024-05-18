package max.shop.common.exception;

public class EmailPatternException extends RuntimeException{
    public EmailPatternException() {
        super();
    }

    public EmailPatternException(String message) {
        super(message);
    }

    public EmailPatternException(String message, Throwable cause) {
        super(message, cause);
    }
}
