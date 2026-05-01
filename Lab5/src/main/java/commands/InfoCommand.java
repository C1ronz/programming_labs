package commands;

import exceptions.ScriptRecursionException;
import managers.CollectionManager;
import util.Console;

/**
 * Команда "info". Выводит информацию о коллекции.
 * @author C1ronz
 */
public class InfoCommand extends AbstractCommand {

    CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager){
        super("info", "вывести информацию о коллекции", 0);
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду.
     */
    @Override
    public void executeInternal(String[] args){
        Console.println(collectionManager.toString());
    }

}