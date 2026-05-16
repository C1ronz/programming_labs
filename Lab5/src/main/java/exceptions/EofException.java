package exceptions;

public class EofException extends RuntimeException {
    public EofException(String message) {
        super(message);
    }
}
