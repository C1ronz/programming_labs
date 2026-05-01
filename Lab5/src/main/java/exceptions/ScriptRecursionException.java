package exceptions;

/**
 * Выбрасывается, если скрипт попадает в бесконечную рекурсию
 * @author C1ronz
 */
public class ScriptRecursionException extends RuntimeException {
    public ScriptRecursionException(String message) {
        super(message);
    }
}
