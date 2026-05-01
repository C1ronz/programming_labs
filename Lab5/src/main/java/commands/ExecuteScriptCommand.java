package commands;

import exceptions.ElementNotFound;
import managers.CollectionManager;
import managers.CommandManager;
import managers.Console;
import models.Dragon;
import util.InteractiveDragonBuilder;

public class ExecuteScriptCommand extends AbstractCommand {

    CollectionManager collectionManager;
    CommandManager commandManager;

    public ExecuteScriptCommand(CollectionManager collectionManager, CommandManager commandManager){
        super("update", "обновить элемент по заданному id (формат: команда id)", 1);
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }

    @Override
    public void executeInternal(String[] args) {
        if (args.length == 1){
            try {
                long id = Long.parseLong(args[0]);
                if (Dragon.isIdUnique(id)) Console.printErr("Дракона с данным id нет в коллекции. Повторите попытку:");
                else {
                    Dragon dragon = InteractiveDragonBuilder.buildDragon();
                    collectionManager.updateByKey(collectionManager.getKeyById(id), dragon);
                    Console.println("Коллекция успешно добавлена");
                }
            }
            catch (NumberFormatException e){
                Console.printErr("Команда update принимает в качестве аргумента 1 число. Повторите попытку: ");
            }
            catch (ElementNotFound e){
                Console.printErr("Не удалось найти элемент. Повторите попытку: ");
            }
        }
        else {
            Console.printErr("Команда update принимает в качестве аргумента 1 число. Повторите попытку: ");
        }
    }

}
