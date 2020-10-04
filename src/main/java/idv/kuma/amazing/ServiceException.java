package idv.kuma.amazing;

public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }


    public ServiceException(String message, Exception e) {
        super(message, e);
    }
}
