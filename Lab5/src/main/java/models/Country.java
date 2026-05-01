package models;

import exceptions.ValidationException;

public enum Country {
    ITALY,
    THAILAND,
    SOUTH_KOREA;

    public static String getValues() {
        Country[] values = values();
        String[] names = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            names[i] = values[i].name();
        }
        return String.join(", ", names);
    }

    public static Country parseCountry(String value) {
        if (value == null || value.trim().isEmpty()) return null;
        try {
            return Country.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Неверное значение country. Возможные: " + Color.getValues());
        }
    }
}
