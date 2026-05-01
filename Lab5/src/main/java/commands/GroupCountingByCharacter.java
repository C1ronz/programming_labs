package commands;

import exceptions.ScriptRecursionException;
import exceptions.ValidationException;
import managers.CollectionManager;
import managers.CommandManager;
import models.DragonCharacter;
import util.Console;
import models.Dragon;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Команда "group_counting_by_character". Группирует элементы коллекции по значению поля character.
 * @author C1ronz
 */
public class GroupCountingByCharacter extends AbstractCommand {

    CollectionManager collectionManager;

    public GroupCountingByCharacter(CollectionManager collectionManager){
        super("group_counting_by_character", "сгруппировать элементы коллекции по значению поля character, вывести количество элементов в каждой группе", 0);
        this.collectionManager = collectionManager;
    }
    /**
     * Выполняет команду.
     */
    @Override
    public void executeInternal(String[] args) {
        Map<DragonCharacter,Long> groupedDragons = new HashMap<>();
        TreeMap<Long, Dragon> dragons = collectionManager.getDragons();
        for (Map.Entry<Long, Dragon> entry : dragons.entrySet()) {
            DragonCharacter character = entry.getValue().getCharacter();
            if (groupedDragons.containsKey(character)) {
                groupedDragons.replace(character, groupedDragons.get(character)+1);
                }
            else{
                groupedDragons.put(character,1L);
            }
        }

        for (Map.Entry<DragonCharacter, Long> entry : groupedDragons.entrySet()){
            Console.println(entry.getKey().toString() + ": " + entry.getValue());
        }
    }
}



