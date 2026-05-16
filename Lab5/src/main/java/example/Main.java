package example;

import commands.*;
import exceptions.ValidationException;
import managers.CollectionManager;
import managers.CommandManager;
import util.Console;
import managers.FileManager;
import util.Runner;

public class Main {
    public static void main(String[] args) {

        String filePath = System.getenv("LAB5_PATH");
        filePath = "files/test.csv";

        FileManager fileManager = new FileManager();
        CollectionManager collectionManager = new CollectionManager(fileManager);
        Runner runner = new Runner(filePath);
        CommandManager commandManager = new CommandManager();

        commandManager.addCommands(
                new HelpCommand(commandManager),
                new InfoCommand(collectionManager),
                new ShowCommand(collectionManager),
                new InsertCommand(collectionManager),
                new UpdateCommand(collectionManager),
                new RemoveKeyCommand(collectionManager),
                new ClearCommand(collectionManager),
                new SaveCommand(collectionManager,runner),
                new ExecuteScriptCommand(runner),
                new ExitCommand(runner),
                new RemoveGreater(collectionManager),
                new HistoryCommand(commandManager),
                new RemoveGreaterKey(collectionManager),
                new RemoveAnyByCharacter(collectionManager),
                new GroupCountingByCharacter(collectionManager),
                new PrintFieldDescendingKiller(collectionManager)
        );

        runner.setCommandManager(commandManager);

        try {
            collectionManager.readFrom(filePath);
        }
        catch (ValidationException e){
            Console.printErr("Ошибка при чтении файла. " + e.getMessage());
        }

//        Person killer = new Person("don", 56L, Color.GREEN, Color.BLACK, Country.ITALY);
//        Coordinates coordinates = new Coordinates(5,12L);
//        Dragon dragon = new Dragon(5,"vi", coordinates, ZonedDateTime.now(), 56, true, Color.BLACK, DragonCharacter.GOOD, killer);
//
//        collectionManager.add(5L, Validator.validateDragon(dragon);

        runner.runInteractive();

    }
}