package max.shop.common.exception;

public class PasswordPatternException extends RuntimeException {
    public PasswordPatternException() {
        super();
    }

    public PasswordPatternException(String message) {
        super(message);
    }

    public PasswordPatternException(String message, Throwable cause) {
        super(message, cause);
    }
}
