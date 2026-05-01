package exceptions;

public class WrongArgument extends Exception {
    public WrongArgument(String message) {
        super(message + ". ");
    }

    @Override
    public String toString (){
        return "Неверный аргумент. ";
    }
}
