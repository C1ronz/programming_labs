package commands;

import managers.CollectionManager;
import util.Console;

public class InfoCommand extends AbstractCommand {

    CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager){
        super("info", "вывести информацию о коллекции", 0);
        this.collectionManager = collectionManager;
    }

    @Override
    public void executeInternal(String[] args){
        Console.println(collectionManager.toString());
    }

}