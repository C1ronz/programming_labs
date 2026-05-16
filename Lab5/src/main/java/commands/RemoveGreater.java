package commands;

import managers.CollectionManager;
import util.Console;
import models.Dragon;
import util.InteractiveDragonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Команда "remove_greater. Удалёет все элементы коллекции превышающие заданный.
 * @author C1ronz
 */
public class RemoveGreater extends Command {

    private final CollectionManager collectionManager;

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
        List<Long> keysToRemove = new ArrayList<>();
        for (Map.Entry<Long,Dragon> entry : dragons.entrySet()) {
            Dragon dragon = entry.getValue();
            if (dragon.compareTo(mainDragon) < 0) {
                keysToRemove.add(entry.getKey());
            }
        }
        for (Long key : keysToRemove){
            collectionManager.removeByKey(key);
            count += 1;
        }

        Console.println("Удалено " + count + " элементов.");
    }
}

