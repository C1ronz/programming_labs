package util;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import models.Dragon;

import java.io.Console;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class CsvConverter {

    public static String objectsToCsvString(List<Dragon> dragons) throws Exception {
        StringWriter stringWriter = new StringWriter();

        StatefulBeanToCsv<Dragon> beanToCsv = new StatefulBeanToCsvBuilder<Dragon>(stringWriter)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)   // можно убрать кавычки
                .withSeparator(',')
                .withOrderedResults(true)                      // сохранять порядок полей
                .build();

        beanToCsv.write(dragons);
        return stringWriter.toString();
    }

    public static List<Dragon> csvStringToObjects(String csvContent) throws Exception {
        StringReader stringReader = new StringReader(csvContent);

        CsvToBean<Dragon> csvToBean = new CsvToBeanBuilder<Dragon>(stringReader)
                .withType(Dragon.class)
                .withSeparator(',')
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                // Как интерпретировать пустые поля как null:
                .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)   // ,,  → null
                // .withFieldAsNull(CSVReaderNullFieldIndicator.BOTH)           // "" и ,, → null
                .build();

        return csvToBean.parse();
    }
}
