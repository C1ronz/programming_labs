package managers;

import exceptions.ElementNotFound;
import exceptions.WrongArgument;
import models.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;

public class CollectionManager {
    private TreeMap <Long, Dragon> dragons = new TreeMap<>();
    private ZonedDateTime inizializeTime;
    private CsvFileManager csvFileManager;
    private final String filePath;

    private Long nextKey = 1L;

    public CollectionManager (CsvFileManager csvFileManager, String filePath){

        this.csvFileManager = csvFileManager;
        this.inizializeTime = ZonedDateTime.now();
        this.filePath = filePath;
    }

    public void add (Long key, Dragon dragon) {
        dragons.put(key,dragon);
    }

    public long generateKey (){
        while (!isKeyUnique(nextKey)){
            nextKey++;
        }
        return nextKey;
    }

    public boolean isKeyUnique (Long key){
        return !dragons.containsKey(key);
    }


    public void save (){
        csvFileManager.writeToFile(dragons, filePath);
    }

    public void read (){
         ArrayList<Dragon> dragonArrayList = csvFileManager.readFromFile(filePath);
        for (Dragon dragon : dragonArrayList){
            add(generateKey(),dragon);
        }
    }

    public TreeMap <Long, Dragon> getDragons(){
        return dragons;
    }
    public void clear (){
        dragons.clear();
    }

    public Dragon getByKey (Long key){
        try {
            return dragons.get(key);
        }
        catch (NullPointerException e) {
            throw new ElementNotFound("Элемента с ключом " + key + " нет в коллекции");
        }
    }

    public Long getKeyById (long id){
        for (Map.Entry<Long,Dragon> entry : dragons.entrySet()){
            if (entry.getValue().getId() == id){
                return entry.getKey();
            }
        }
        throw new ElementNotFound("Дракона с id " + id + " нет в коллекции");
    }

    public void removeByKey (Long key){
        try {
            dragons.remove(key);
        }
        catch (NullPointerException e) {
            throw new ElementNotFound("Элемента с ключом " + key + " нет в коллекции");
        }
    }

    public List<Dragon> getGreaterElements (Dragon dragon){
        List<Dragon> result = new ArrayList<>();
        for (Map.Entry<Long,Dragon> entry : dragons.entrySet()){
            if (dragon.compareTo(entry.getValue()) < 0){
                result.add(entry.getValue());
            }
        }
        return result;
    }



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
