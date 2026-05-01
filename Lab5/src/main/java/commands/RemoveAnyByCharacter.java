package commands;

import exceptions.ValidationException;
import managers.CollectionManager;
import models.DragonCharacter;
import util.Console;
import models.Dragon;
import java.util.Map;
import java.util.TreeMap;

/**
 * Команда "remove_any_by_character". Удалёет первый найденный элемент с совпадающим значением поля character.
 * @author C1ronz
 */
public class RemoveAnyByCharacter extends AbstractCommand {

    CollectionManager collectionManager;

    public RemoveAnyByCharacter(CollectionManager collectionManager){
        super("remove_any_by_character", "удалить из коллекции все один элементы с заданным характером (формат: команда характер), варианты:" + DragonCharacter.getValues(), 1);
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду.
     */
    @Override
    public void executeInternal(String[] args) {
        try {
            DragonCharacter character = DragonCharacter.parseCharacter(args[0]);
            TreeMap<Long, Dragon> dragons = collectionManager.getDragons();
            for (Map.Entry<Long, Dragon> entry : dragons.entrySet()) {
                Dragon dragon = entry.getValue();
                if (dragon.getCharacter().equals(character)) {
                    collectionManager.removeByKey(entry.getKey());
                    Console.println(dragon.getName() + " был удалён из коллекции");
                    break;
                }
            }
        }
        catch (ValidationException e){
            Console.printErr(e.getMessage());
        }
    }
}


