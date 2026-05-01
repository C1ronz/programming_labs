import commands.*;
import exceptions.ValidationException;
import managers.CollectionManager;
import managers.CommandManager;
import managers.Console;
import managers.CsvFileManager;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        CsvFileManager csvFileManager = new CsvFileManager();
        CollectionManager collectionManager = new CollectionManager(csvFileManager, "data.csv");
        Scanner scanner = new Scanner(System.in);
        Console console = new Console();

        CommandManager commandManager = new CommandManager(collectionManager, console);

        commandManager.addCommands(
                new HelpCommand(commandManager),
                new InfoCommand(collectionManager),
                new ShowCommand(collectionManager),
                new InsertCommand(collectionManager,commandManager),
                new RemoveKeyCommand(collectionManager),
                new ClearCommand(collectionManager),
                new SaveCommand(collectionManager),
                new ExitCommand(commandManager),
                new HistoryCommand(commandManager)
        );

        try {
            collectionManager.read();
        }
        catch (ValidationException e){
            Console.printErr("Ошибка при чтении файла. " + e);
        }

        Console.println(collectionManager.getDragons().toString());


        commandManager.run();
        Console.println("hi test");

    }
}