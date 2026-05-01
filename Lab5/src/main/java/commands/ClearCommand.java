package commands;

import managers.CollectionManager;

public class ClearCommand extends AbstractCommand {

    CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager){
        super("clear", "отчистить коллекцию",0);
        this.collectionManager = collectionManager;
    }

    @Override
    public void executeInternal(String[] args) {
        collectionManager.clear();
    }
}
