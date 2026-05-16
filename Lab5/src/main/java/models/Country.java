package models;

import exceptions.ValidationException;

/**
 * Перечисление стран.
 * @author C1ronz
 */
public enum Country {
    ITALY,
    THAILAND,
    SOUTH_KOREA;

    /**
     * @return Строка со всеми странами через запятую.
     */
    public static String getValues() {
        Country[] values = values();
        String[] names = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            names[i] = values[i].name();
        }
        return String.join(", ", names);
    }

    /**
     * Парсит страну.
     * @param value страна в виде строки.
     * @throws ValidationException выкидывается, если такой страны нет в enum.
     */
    public static Country parseCountry(String value) {
        if (value == null || value.trim().isEmpty()) return null;
        try {
            return Country.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Неверное значение country. Возможные: " + Country.getValues());
        }
    }
}
