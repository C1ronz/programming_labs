package exceptions;

/**
 * Выбрасывается при ошибках валидации.
 * @author C1ronz
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super("Значение не прошло валидацию" + message);
    }
}
