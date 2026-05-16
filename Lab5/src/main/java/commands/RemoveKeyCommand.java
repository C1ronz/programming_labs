package commands;

import exceptions.ElementNotFound;
import managers.CollectionManager;
import util.Console;

/**
 * Команда "remove_key". Удалёет элемент коллекции по ключю.
 * @author C1ronz
 */
public class RemoveKeyCommand extends Command {

    private final CollectionManager collectionManager;


    public RemoveKeyCommand(CollectionManager collectionManager){
        super("remove_key", "удалить элемент по заданному ключю (формат: команда ключ)", 1);
        this.collectionManager = collectionManager;

    }

    /**
     * Выполняет команду.
     */
    @Override
    public void executeInternal(String[] args) {
        try {
            Long key = Long.valueOf(args[0]);
            if (collectionManager.isKeyUnique(key)) Console.printErr("Элемента с данным ключом нет в коллекции. Повторите попытку:");
            else {
                collectionManager.removeByKey(key);
                Console.println("Элемент успешно удалён");
            }
        }
        catch (NumberFormatException e){
            Console.printErr("Команда remove принимает в качестве аргумента одно число. Повторите попытку.");
        }
        catch (ElementNotFound e){
            Console.printErr("Не удалось найти элемент. Повторите попытку.  Доступные ключи:" + collectionManager.getDragons().keySet());
        }
    }
}
