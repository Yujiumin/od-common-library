package cn.fullstack.od.common.core.exception;

public class BadOperationException extends RuntimeException {

    public BadOperationException() {
        super();
    }

    public BadOperationException(String message) {
        super(message);
    }

    public BadOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadOperationException(Throwable cause) {
        super(cause);
    }

    protected BadOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
