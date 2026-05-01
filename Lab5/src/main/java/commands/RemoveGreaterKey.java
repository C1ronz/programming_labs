package commands;

import managers.CollectionManager;
import util.Console;
import models.Dragon;

import java.util.Map;
import java.util.TreeMap;

/**
 * Команда "remove_greater_key". Удалёет все элементы коллекции, ключ которых превышает заданный.
 * @author C1ronz
 */
public class RemoveGreaterKey extends AbstractCommand {

    CollectionManager collectionManager;

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
            Long key = Long.valueOf(args[0]);
            int count = 0;
            TreeMap<Long,Dragon> dragons = collectionManager.getDragons();
            for (Map.Entry<Long,Dragon> entry : dragons.entrySet()) {
                if (entry.getKey() > key) {
                    collectionManager.removeByKey(entry.getKey());
                    count += 1;
                }
            }
            Console.println("Удалено " + count + " элементов.");
        }
        catch (NumberFormatException e){
            Console.printErr("Команда remove_greater_key принимает в качестве аргумента одно число. Повторите попытку.");
        }
    }
}


