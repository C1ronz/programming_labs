import commands.*;
import exceptions.ValidationException;
import managers.CollectionManager;
import managers.CommandManager;
import util.Console;
import managers.FileManager;
import util.Runner;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        String filePath = System.getenv("LAB5_PATH");

        FileManager fileManager = new FileManager();
        CollectionManager collectionManager = new CollectionManager(fileManager);
        Scanner scanner = new Scanner(System.in);
        Console console = new Console();

        Runner runner = new Runner(filePath);

        CommandManager commandManager = new CommandManager();

        commandManager.addCommands(
                new HelpCommand(commandManager),
                new InfoCommand(collectionManager),
                new ShowCommand(collectionManager),
                new InsertCommand(collectionManager,commandManager),
                new RemoveKeyCommand(collectionManager),
                new ClearCommand(collectionManager),
                new SaveCommand(collectionManager,runner),
                new ExitCommand(runner),
                new HistoryCommand(commandManager),
                new ExecuteScriptCommand(runner)
        );

        runner.setCommandManager(commandManager);

        try {
            collectionManager.readFrom(filePath);
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

        runner.runInteractive();

    }
}