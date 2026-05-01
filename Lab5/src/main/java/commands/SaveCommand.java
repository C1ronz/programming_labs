package commands;

import managers.CollectionManager;

public class SaveCommand extends AbstractCommand {

    CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager){
        super("save", "сохранить коллекцию в файл", 0);
        this.collectionManager = collectionManager;
    }

    @Override
    public void executeInternal(String[] args){
        collectionManager.save();
    }

}
