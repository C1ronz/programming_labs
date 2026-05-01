package util;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Класс для корректной работы OpenCSV c ZonedDateTime.
 * @author C1ronz
 */
public class ZonedDateTimeConverter extends AbstractBeanField<ZonedDateTime, String> {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ISO_ZONED_DATE_TIME;

    @Override
    protected ZonedDateTime convert(String value)
            throws CsvDataTypeMismatchException {
        try {
            String cleanValue = value.replaceAll("\\[.*?\\]$", "");
            return ZonedDateTime.parse(cleanValue, FORMATTER);
        } catch (Exception e) {
            throw new CsvDataTypeMismatchException(
                    "Не удалось преобразовать: " + value);
        }
    }
}
