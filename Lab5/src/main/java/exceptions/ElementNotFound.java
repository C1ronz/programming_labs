package exceptions;

/**
 * Выбрасывается при отсутствии элемента в коллекции.
 * @author C1ronz
 */
public class ElementNotFound extends RuntimeException {
    public ElementNotFound(String message) {
        super(message);
    }
}
