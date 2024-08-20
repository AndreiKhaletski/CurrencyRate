package krainet.trainee.service.exceptions;

public class FailMailSendException extends Exception{
    public FailMailSendException() {
        super();
    }

    public FailMailSendException(String message) {
        super(message);
    }

    public FailMailSendException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailMailSendException(Throwable cause) {
        super(cause);
    }

    protected FailMailSendException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
