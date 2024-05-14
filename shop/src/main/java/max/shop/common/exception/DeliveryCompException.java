package max.shop.common.exception;

public class DeliveryCompException extends RuntimeException {
    public DeliveryCompException() {
        super();
    }

    public DeliveryCompException(String message) {
        super(message);
    }

    public DeliveryCompException(String message, Throwable cause) {
        super(message, cause);
    }
}
