package commands;

import managers.CollectionManager;
import util.Runner;

/**
 * Команда "save". Сохраняет коллекцию в файл.
 * @author C1ronz
 */
public class SaveCommand extends Command {

    private final CollectionManager collectionManager;
    private final Runner runner;

    public SaveCommand(CollectionManager collectionManager, Runner runner){
        super("save", "сохранить коллекцию в файл", 0);
        this.collectionManager = collectionManager;
        this.runner = runner;
    }

    /**
     * Выполняет команду.
     */
    @Override
    public void executeInternal(String[] args){
        collectionManager.saveTo(runner.getFilePath());
    }

}
