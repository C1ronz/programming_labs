package exceptions;

/**
 * Выбрасывается при использовании неверного формата команды.
 * @author C1ronz
 */
public class WrongCommandPattern extends Exception {
    public WrongCommandPattern(String message) {
        super("Неверная конструкция команды. "+ message);
    }
}


