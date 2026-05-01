package exceptions;

/**
 * Исключение, выбрасываемое при ошибках валидации.
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
