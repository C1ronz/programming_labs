package exceptions;

public class MustNotNullException extends Exception {
    public MustNotNullException(String message) {
        super(message + ". ");
    }

    @Override
    public String toString (){
        return "Непредвиденный null. ";
    }
}
