package managers;

import exceptions.ElementNotFound;
import models.*;

import java.time.ZonedDateTime;
import java.util.*;

/**
 * Управляет коллекцией.
 * @author C1ronz
 */
public class CollectionManager {
    private TreeMap <Long, Dragon> dragons = new TreeMap<>();
    private ZonedDateTime inizializeTime;
    private FileManager fileManager;

    private Long nextKey = 1L;

    public CollectionManager (FileManager fileManager){

        this.fileManager = fileManager;
        this.inizializeTime = ZonedDateTime.now();
    }

    /**
     * Добвляет элемент в коллекцию.
     */
    public void add (Long key, Dragon dragon) {
        dragons.put(key,dragon);
        Dragon.addId(dragon.getId());
    }

    /**
     * @return Уникальный ключ
     */
    public long generateKey (){
        while (!isKeyUnique(nextKey)){
            nextKey++;
        }
        return nextKey;
    }

    /**
     * @return Уникальный ли ключ?
     */
    public boolean isKeyUnique (Long key){
        return !dragons.containsKey(key);
    }


    /**
     * @param filePath Путь к файлу
     * Сохраняет коллекцию в файл
     */
    public void saveTo(String filePath){
        fileManager.writeToFile(dragons,filePath);
    }

    /**
     * @param filePath Путь к файлу
     * Читает коллекцию из файла
     */
    public void readFrom(String filePath){
         List<Dragon> dragonList = fileManager.readFromFile(filePath);
        for (Dragon dragon : dragonList){
            add(generateKey(),dragon);
        }
    }

    /**
     * @return коллекция.
     */
    public TreeMap <Long, Dragon> getDragons(){
        return dragons;
    }

    /**
     * Отчищает коллекцию
     */
    public void clear (){
        dragons.clear();
    }


    /**
     * Находит ключ элемента коллекции по id.
     * @trows ElementNotFound
     */
    public Long getKeyById (long id){
        for (Map.Entry<Long,Dragon> entry : dragons.entrySet()){
            if (entry.getValue().getId() == id){
                return entry.getKey();
            }
        }
        throw new ElementNotFound("Дракона с id " + id + " нет в коллекции");
    }

    /**
     * Удаляет элемент коллекции по ключю.
     */
    public void removeByKey (Long key){
        try {
            dragons.remove(key);
        }
        catch (NullPointerException e) {
            throw new ElementNotFound("Элемента с ключом " + key + " нет в коллекции");
        }
    }

    /**
     * Заменяет элемент коллекции на новый по ключю.
     */
    public void updateByKey (Long key, Dragon dragon){
        try {
            dragons.replace(key, dragon);
        }
        catch (NullPointerException e) {
            throw new ElementNotFound("Элемента с ключом " + key + " нет в коллекции");
        }
    }

    @Override
    public String toString (){
        return "Тип: TreeMap <Long, Dragon> \n" +
                "Дата инициализации: " + inizializeTime.toString() + "\n" +
                "Кол-во элементов: " + dragons.size() + "\n" +
                "Занятые ключи: " + dragons.keySet() + "\n" +
                "Занятые id дракона: " + Dragon.getUsedDragonId().toString();
    }

}
