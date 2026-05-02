package commands;

import exceptions.ElementNotFound;
import managers.CollectionManager;
import util.Console;
import models.Dragon;
import util.InteractiveDragonBuilder;

/**
 * Команда "update". Запрашивает у пользователя и обновляет элемент в коллекции по id.
 * @author C1ronz
 */
public class UpdateCommand extends AbstractCommand {

    private final CollectionManager collectionManager;

    public UpdateCommand(CollectionManager collectionManager){
        super("update", "обновить элемент по заданному id (формат: команда id)", 1);
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду.
     */
    @Override
    public void executeInternal(String[] args) {
        try {
            long id = Long.parseLong(args[0]);
            Dragon dragon = InteractiveDragonBuilder.buildDragon();
            collectionManager.updateByKey(collectionManager.getKeyById(id), dragon);
            Console.println("Элемент успешно обновлён");
        }
        catch (NumberFormatException e){
            Console.printErr("Команда update принимает в качестве аргумента одно число. Повторите попытку.");
        }
        catch (ElementNotFound e){
            Console.printErr("Не удалось найти элемент. Повторите попытку.  Доступные id:" + Dragon.getUsedDragonId());
        }
    }
}
