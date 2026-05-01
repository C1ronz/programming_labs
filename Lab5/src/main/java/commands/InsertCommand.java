package commands;

import managers.CollectionManager;
import managers.CommandManager;
import managers.Console;
import models.Dragon;
import util.InteractiveDragonBuilder;

public class InsertCommand extends AbstractCommand {

    CollectionManager collectionManager;
    CommandManager commandManager;

    public InsertCommand(CollectionManager collectionManager, CommandManager commandManager){
        super("insert", "добавить новый элемент с заданным ключом (формат: команда ключ)", 1);
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }

    @Override
    public void executeInternal(String[] args) {
        if (args.length == 1){
            try {
                Long key = Long.valueOf(args[0]);
                if (!collectionManager.isKeyUnique(key)) Console.printErr("Введеный ключ уже используется. Повторите попытку:");
                else {
                    Dragon dragon = InteractiveDragonBuilder.buildDragon();
                    collectionManager.add(key, dragon);
                    Console.println("Коллекция успешно добавлена");
                }
            }
            catch (NumberFormatException e){
                Console.printErr("Команда insert принимает в качестве аргумента 1 число. Повторите попытку: ");
            }
        }
        else {
            Console.printErr("Команда insert принимает в качестве аргумента 1 число. Повторите попытку: ");
        }
    }

}
