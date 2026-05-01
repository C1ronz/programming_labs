package commands;

import managers.CollectionManager;
import models.Dragon;
import util.Console;

import java.util.Map;

/**
 * Команда "show". Выводит все элементы коллекции в строковом представлении.
 * @author C1ronz
 */
public class ShowCommand extends AbstractCommand {

    CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager){
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении", 0);
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду.
     */
    @Override
    public void executeInternal(String[] args){
        for (Map.Entry<Long, Dragon> pair : collectionManager.getDragons().entrySet()){
            Console.println("key:" + pair.getKey() + ", " + pair.getValue().toString());
        }
    }

}