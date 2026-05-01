package managers;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import models.*;
import util.CsvConverter;
import util.Validator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class FileManager {

    private static final CsvMapper mapper = new CsvMapper();
    private static final CsvSchema schema = mapper.schemaFor(Dragon.class)
            .withHeader()
            .withColumnSeparator(',')
            .withQuoteChar('"')
            .withEscapeChar('\\');

    static {
        // Важно для корректной работы с ZonedDateTime
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(com.fasterxml.jackson.databind.DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        mapper.enable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
    }

    public void writeToFile (TreeMap<Long, Dragon> dragons, String fileName) {

        File csvFile = new File(fileName);

        List<Dragon> list = new ArrayList<>(dragons.values());
        try {
            String csvString = CsvConverter.objectsToCsvString(list);
            FileOutputStream fos = new FileOutputStream((csvFile));
            byte[] bytes = csvString.getBytes();
            fos.write(bytes);
            System.out.println("Данные успешно записаны в файл " + csvFile.getName());
        }
        catch (Exception e) {}

    }

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
            ;
            for (Dragon dragon: list){
                dragon.setKiller(personCheck(dragon.getKiller()));
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Person personCheck (Person person){
        if (person.getName() == null && person.getHeight() == null && person.getHairColor() == null && person.getEyeColor() == null & person.getNationality() == null){
            return null;
        }
        return person;
    }
}
