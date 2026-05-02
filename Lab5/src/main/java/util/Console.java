package util;

import java.util.Scanner;

/**
 * Консоль для вывода результата, ввода команд и аргументов.
 * @author C1ronz
 */
public class Console {

    public static final String PS1 = "-> ";
    private static final Scanner scanner = new Scanner(System.in);

    public static void println (String line){
        System.out.println(line);
    }

    public static void print (String text){
        System.out.print(text);
    }

    /**
     * Выводит "Ошибка: переданный текст.
     */
    public static void printErr (String errorText) {System.out.println("Ошибка: " + errorText);}

    /**
     * Читает команду и аргументы с ней в одной строке
     */
    public static String[] readCommand () {
        Console.print(PS1);
        return (scanner.nextLine().trim() + " ").split(" ");
    }

    /**
     * Запрашивает, получает аргумент от пользователя
     * @param name запрос к пользователю
     * @return введёный пользователем аргумент
     */
    public static String readArgument (String name) {
        Console.print(name + ": ");
        return scanner.nextLine().trim();
    }
}
