package managers;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import models.*;
import util.Console;
import util.CsvConverter;
import util.Validator;

import java.io.*;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Работает с csv файлами
 * @author C1ronz
 */
public class FileManager {

    private static final CsvMapper mapper = new CsvMapper();
    private static final CsvSchema schema = mapper.schemaFor(Dragon.class)
            .withHeader()
            .withColumnSeparator(',')
            .withQuoteChar('"')
            .withEscapeChar('\\');

    static {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(com.fasterxml.jackson.databind.DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        mapper.enable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
    }


    /**
     * Записывает коллекцию в файл.
     * @param dragons коллекция.
     * @param fileName путь к файлу.
     */
    public void writeToFile (TreeMap<Long, Dragon> dragons, String fileName) {

        List<Dragon> list = new ArrayList<>(dragons.values());
        try {
            File csvFile = new File(fileName);
            String csvString = CsvConverter.objectsToCsvString(list);
            FileOutputStream fos = new FileOutputStream((csvFile));
            byte[] bytes = csvString.getBytes();
            fos.write(bytes);
            System.out.println("Данные успешно записаны в файл " + csvFile.getName());
        }
        catch (FileNotFoundException e){
            Console.printErr("Файл " + fileName + " не найден");
        }
        catch (AccessDeniedException e){
            Console.printErr("Не достаточно прав для доступа к файлу " + fileName);
        }
        catch (Exception e) {
            Console.printErr("Не удалось конверировать данные");
        }
    }

    /**
     * Читает коллекцию из файла.
     * @return list, содержащий элементы коллекции
     * @param fileName путь к файлу.
     */
    public List<Dragon> readFromFile (String fileName) {

        File csvFile = new File(fileName);

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(csvFile)))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String csvString = sb.toString();
            List<Dragon> list = CsvConverter.csvStringToObjects(csvString);
            List<Dragon> finalList = new ArrayList<>();
            for (Dragon dragon: list){
                dragon.setKiller(personCheck(dragon.getKiller()));
                finalList.add(Validator.validateDragon(dragon));
            }
            return finalList;

        }
        catch (FileNotFoundException e){
            Console.printErr("Файл " + fileName + " не найден");
        }
        catch (AccessDeniedException e){
            Console.printErr("Не достаточно прав для доступа к файлу " + fileName);
        }
        catch (Exception e) {
            Console.printErr("Не удалось конверировать данные");
        }
        return null;
    }

    /**
     * Проверяет person, если все его поля null - возвращает null, иначе Person.
     */
    private Person personCheck (Person person){
        if (person.getName() == null && person.getHeight() == null && person.getHairColor() == null && person.getEyeColor() == null & person.getNationality() == null){
            return null;
        }
        return person;
    }
}
