package commands;

import managers.CollectionManager;
import util.Console;

import java.util.Map;

public class ShowCommand extends AbstractCommand {

    CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager){
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении", 0);
        this.collectionManager = collectionManager;
    }

    @Override
    public void executeInternal(String[] args){
        for (Map.Entry pair : collectionManager.getDragons().entrySet()){
            Console.println("key:" + pair.getKey() + ", " + pair.getValue().toString());
        }
    }

}