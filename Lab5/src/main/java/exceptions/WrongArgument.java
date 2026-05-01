package exceptions;

/**
 * Выбрасывается при неверных аргументах.
 * @author C1ronz
 */
public class WrongArgument extends Exception {
    public WrongArgument(String message) {
        super(message + ". ");
    }

    @Override
    public String toString (){
        return "Неверный аргумент. ";
    }
}
