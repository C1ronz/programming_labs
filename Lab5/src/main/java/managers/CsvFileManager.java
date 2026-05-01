package managers;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import models.*;
import util.Validator;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CsvFileManager {

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

    public void writeToFile (TreeMap<Long, Dragon> collection, String fileName) {

        File csvFile = new File(fileName);

        List<Dragon> list = new ArrayList<>(collection.values());

        try (StringWriter stringWriter = new StringWriter();
             SequenceWriter sequenceWriter = mapper.writer(schema).writeValues(stringWriter)) {

            FileOutputStream fos = new FileOutputStream((csvFile));
            sequenceWriter.writeAll(list);
            String csvString = stringWriter.toString();
            byte[] bytes = csvString.getBytes();
            fos.write(bytes);
            System.out.println("Данные успешно записаны в файл " + csvFile.getName());
        }
        catch (IOException e){
            Console.println(e.toString());
        }
    }

    public ArrayList<Dragon> readFromFile (String fileName) {

        File csvFile = new File(fileName);
        ArrayList<Dragon> dragons = new ArrayList<> ();

        try (Reader reader = new InputStreamReader(new FileInputStream(csvFile))){
            MappingIterator<Dragon> iterator = mapper.readerFor(Dragon.class)
                    .with(schema)
                    .readValues(reader);

            while (iterator.hasNextValue()) {
                Dragon dragon = iterator.nextValue();
                Console.println(dragon.toString());
                if (dragon != null ) {
                    dragons.add(Validator.validateDragon(dragon));
                }
            }
        } catch (FileNotFoundException e){
            Console.println(e+"");
        } catch (IOException e) {
            Console.println(e+"");
        }
        return dragons;
    }
}
