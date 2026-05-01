package exceptions;

public class WrongCommandPattern extends Exception {
    public WrongCommandPattern(String message) {
        super("Неверная конструкция команды. "+ message);
    }
}


