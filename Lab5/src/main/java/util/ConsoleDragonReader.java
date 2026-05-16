package util;

import exceptions.EofException;
import exceptions.ValidationException;

import java.io.EOFException;
import java.util.function.Function;

/**
 * Класс запрашивающий (в том числе повторно) данные для построения dragon.
 * @author C1ronz
 */
public class ConsoleDragonReader {

    /**
     * Запрашивает, парсит, валидирует, при необходимости повторно запрашивает пользовательский ввод.
     * @param <T> тип значения, в который преобразуется ввод пользователя
     * @param request текст запроса, выводимый пользователю
     * @param parser функция для преобразования строки в тип T
     * @param validator потребитель для валидации полученного значения
     * @return валидное значение типа T
     */
    public static <T> T getValidatedInput(String request, Function<String,T> parser, Function<T,T> validator) {
        while (true){
            try {
                String input = Console.readArgument(request);
                T value = parser.apply(input);
                return validator.apply(value);
            }
            catch (EofException e){
                return null;
            }
            catch (ValidationException e){
                Console.printErr(e.getMessage() + " Попробуйте снова.");
            }
            catch (Exception e){
                Console.printErr("Некорректный ввод. Попробуйте снова");
            }
        }
    }
}
