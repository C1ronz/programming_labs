package commands;

import managers.CollectionManager;
import models.Person;
import util.Console;
import models.Dragon;
import java.util.*;

/**
 * Команда "print_field_descending_killer". Выводит в порядке убывания значения поля killer для всех элементов.
 * @author C1ronz
 */
public class PrintFieldDescendingKiller extends AbstractCommand {

    CollectionManager collectionManager;

    public PrintFieldDescendingKiller(CollectionManager collectionManager){
        super("print_field_descending_killer", "вывести значения поля killer всех элементов в порядке убывания", 0);
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду.
     */
    @Override
    public void executeInternal(String[] args) {
        TreeMap<Long, Dragon> dragons = collectionManager.getDragons();
        List<Person> killers = new ArrayList<>();
        for (Map.Entry<Long, Dragon> entry : dragons.entrySet()) {
            Person killer = entry.getValue().getKiller();
            if (killer != null){
                killers.add(killer);
            }
        }
        killers.sort(Collections.reverseOrder());
        for (Person killer : killers){
            Console.println(killer.toString());
        }
    }
}



