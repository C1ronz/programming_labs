package commands;

import managers.CollectionManager;
import managers.CommandManager;
import util.Console;
import models.Dragon;
import util.InteractiveDragonBuilder;

import java.util.Map;
import java.util.TreeMap;

/**
 * Команда "remove_greater. Удалёет все элементы коллекции превышающие заданный.
 * @author C1ronz
 */
public class RemoveGreater extends AbstractCommand {

    CollectionManager collectionManager;

    public RemoveGreater(CollectionManager collectionManager){
        super("remove_greater", "удалить из коллекции все элементы, превышающие заданный", 0);
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду.
     */
    @Override
    public void executeInternal(String[] args) {
        int count = 0;
        Dragon mainDragon = InteractiveDragonBuilder.buildDragon();
        TreeMap<Long,Dragon> dragons = collectionManager.getDragons();
        for (Map.Entry<Long,Dragon> entry : dragons.entrySet()) {
            Dragon dragon = entry.getValue();
            if (dragon.compareTo(mainDragon) < 0) {
                collectionManager.removeByKey(entry.getKey());
                count += 1;
            }
        }
        Console.println("Удалено " + count + " элементов.");
    }
}

