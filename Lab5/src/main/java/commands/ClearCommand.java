package commands;

import exceptions.WrongCommandPattern;
import managers.CollectionManager;

/**
 * Команда "clean". Удалёет все элементы коллекции.
 * @author C1ronz
 */
public class ClearCommand extends AbstractCommand {

    CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager){
        super("clear", "отчистить коллекцию",0);
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду.
     */
    @Override
    public void executeInternal(String[] args) {
        collectionManager.clear();
    }
}
