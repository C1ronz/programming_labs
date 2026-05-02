package exceptions;

/**
 * Выбрасывается, если поле, которое не должно быть null, равно null.
 * @author C1ronz
 */
public class MustNotNullException extends Exception {
    public MustNotNullException(String message) {
        super("Непредвиденный null." + message);
    }
}
