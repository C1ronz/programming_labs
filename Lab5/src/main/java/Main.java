import commands.*;
import exceptions.ValidationException;
import managers.CollectionManager;
import managers.CommandManager;
import managers.Console;
import managers.FileManager;
import models.*;

import java.time.ZonedDateTime;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        FileManager fileManager = new FileManager();
        CollectionManager collectionManager = new CollectionManager(fileManager, System.getenv("LAB5_PATH"));
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

//        Person killer = new Person("don", 56L, Color.GREEN, Color.BLACK, Country.ITALY);
//        Coordinates coordinates = new Coordinates(5,12L);
//        Dragon dragon = new Dragon(5,"vi", coordinates, ZonedDateTime.now(), 56, true, Color.BLACK, DragonCharacter.GOOD, killer);
//
//        collectionManager.add(5L, dragon);

        commandManager.run();

    }
}