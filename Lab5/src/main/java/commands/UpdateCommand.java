package commands;

import exceptions.ElementNotFound;
import managers.CollectionManager;
import managers.CommandManager;
import util.Console;
import models.Dragon;
import util.InteractiveDragonBuilder;

public class UpdateCommand extends AbstractCommand {

    CollectionManager collectionManager;
    CommandManager commandManager;

    public UpdateCommand(CollectionManager collectionManager, CommandManager commandManager){
        super("update", "обновить элемент по заданному id (формат: команда id)", 1);
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }

    @Override
    public void executeInternal(String[] args) {
        try {
            long id = Long.parseLong(args[0]);
            Dragon dragon = InteractiveDragonBuilder.buildDragon();
            collectionManager.updateByKey(collectionManager.getKeyById(id), dragon);
            Console.println("Коллекция успешно добавлена");
        }
        catch (NumberFormatException e){
            Console.printErr("Команда update принимает в качестве аргумента одно число. Повторите попытку.");
        }
        catch (ElementNotFound e){
            Console.printErr("Не удалось найти элемент. Повторите попытку.  Доступные id:" + Dragon.getUsedDragonId());
        }
    }
}
