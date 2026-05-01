package commands;

import managers.CollectionManager;
import util.Runner;

public class SaveCommand extends AbstractCommand {

    CollectionManager collectionManager;
    Runner runner;

    public SaveCommand(CollectionManager collectionManager, Runner runner){
        super("save", "сохранить коллекцию в файл", 0);
        this.collectionManager = collectionManager;
        this.runner = runner;
    }

    @Override
    public void executeInternal(String[] args){
        collectionManager.saveTo(runner.getFilePath());
    }

}
