package commands;

import managers.CollectionManager;
import util.Console;
import models.Dragon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Команда "remove_greater_key". Удалёет все элементы коллекции, ключ которых превышает заданный.
 * @author C1ronz
 */
public class RemoveGreaterKey extends Command {

    private final CollectionManager collectionManager;

    public RemoveGreaterKey(CollectionManager collectionManager){
        super("remove_greater_key", "удалить из коллекции все элементы, ключ которых превышает заданный (формат: команда ключ)", 1);
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду.
     */
    @Override
    public void executeInternal(String[] args) {
        try {
            Long ArgKey = Long.valueOf(args[0]);
            int count = 0;
            TreeMap<Long,Dragon> dragons = collectionManager.getDragons();
            List<Long> keysToRemove = new ArrayList<>();
            for (Map.Entry<Long,Dragon> entry : dragons.entrySet()) {
                if (entry.getKey() > ArgKey) {
                    keysToRemove.add(entry.getKey());
                }
            }

            for (Long key : keysToRemove){
                collectionManager.removeByKey(key);
                count += 1;
            }
            Console.println("Удалено " + count + " элементов.");
        }
        catch (NumberFormatException e){
            Console.printErr("Команда remove_greater_key принимает в качестве аргумента одно число. Повторите попытку.");
        }
    }
}


