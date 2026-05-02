package util;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import models.Dragon;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс конвертирующий List из драконов в csv строку и обратно.
 * @author C1ronz
 */
public class CsvConverter {

    /**
     * Конвертирует list в csv строку.
     */
    public static String objectsToCsvString(List<Dragon> dragons) throws Exception {
        StringWriter stringWriter = new StringWriter();

        StatefulBeanToCsv<Dragon> beanToCsv = new StatefulBeanToCsvBuilder<Dragon>(stringWriter)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(',')
                .withOrderedResults(true)
                .withThrowExceptions(true)
                .build();

        beanToCsv.write(dragons);
        return stringWriter.toString();
    }

    /**
     * Конвертирует csv строку в list.
     */
    public static List<Dragon> csvStringToObjects(String csvContent) throws Exception {
        StringReader stringReader = new StringReader(csvContent);
        CsvToBean<Dragon> csvToBean = new CsvToBeanBuilder<Dragon>(stringReader)
                .withType(Dragon.class)
                .withSeparator(',')
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                .withThrowExceptions(true)
                .build();

        return csvToBean.parse();
    }
}
