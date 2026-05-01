package util;

import java.util.Scanner;

public class Console {

    public static final String P1 = "-> ";
    private static final Scanner scanner = new Scanner(System.in);

    public static void println (String line){
        System.out.println(line);
    }

    public static void print (String text){
        System.out.print(text);
    }

    public static void printErr (String errorText) {System.out.println("Ошибка: " + errorText);}

    public static String[] readCommand () {
        Console.print(P1);
        String[] userCommand = (scanner.nextLine().trim() + " ").split(" ");
        return userCommand;
    }

    public static String readArgument (String name) {
        Console.print(name + ": ");
        String userArgument = scanner.nextLine().trim();
        return userArgument;
    }
}
