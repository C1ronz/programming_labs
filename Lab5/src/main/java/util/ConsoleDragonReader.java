package util;

import exceptions.ValidationException;

import java.util.function.Function;
import java.util.function.Consumer;

public class ConsoleDragonReader {

    public static <T> T getValidatedInput(String request, Function<String,T> parser, Consumer<T> validator){
        while (true){
            try {
                String input = Console.readArgument(request);
                T value = parser.apply(input);
                validator.accept(value);
                return value;
            }
            catch (ValidationException e){
                Console.printErr(e + "Попробуйте снова.");
            }
            catch (Exception e){
                Console.printErr("Некорректный ввод. Попробуйте снова");
            }
        }
    }

    public static String getValidatedInput(String request, Consumer<String> validator) {
        return getValidatedInput(request, Function.identity(), validator);
    }
}
