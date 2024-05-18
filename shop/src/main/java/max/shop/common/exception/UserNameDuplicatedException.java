package max.shop.common.exception;

public class UserNameDuplicatedException extends RuntimeException {
    public UserNameDuplicatedException() {
        super();
    }

    public UserNameDuplicatedException(String message) {
        super(message);
    }

    public UserNameDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
