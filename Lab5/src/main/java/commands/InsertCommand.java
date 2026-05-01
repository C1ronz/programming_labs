package commands;

import managers.CollectionManager;
import managers.CommandManager;
import util.Console;
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
        try {
            Long key = Long.valueOf(args[0]);
            if (!collectionManager.isKeyUnique(key)) Console.printErr("Введеный ключ уже используется. Повторите попытку. Использующиеся ключи:" + collectionManager.getDragons().keySet());
            else {
                Dragon dragon = InteractiveDragonBuilder.buildDragon();
                collectionManager.add(key, dragon);
                Console.println("Коллекция успешно добавлена");
            }
        }
        catch (NumberFormatException e){
            Console.printErr("Команда insert принимает в качестве аргумента одно число. Повторите попытку: ");
        }
    }
}
