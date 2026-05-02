package models;

import exceptions.ValidationException;

/**
 * Перечисление цветов.
 * @author C1ronz
 */
public enum Color {
    GREEN,
    BLACK,
    YELLOW,
    ORANGE,
    WHITE;

    /**
     * @return Строка со всеми цветами через запятую.
     */
    public static String getValues (){
        Color[] values = values();
        String[] names = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            names[i] = values[i].name();
        }
        return String.join(", ", names);
    }

    /**
     * Парсит цвет.
     * @param value цвет в виде строки.
     * @throws ValidationException выкидывается, если такого цвета нет в enum.
     */
    public static Color parseColor(String value) {
        if (value == null || value.trim().isEmpty()) return null;
        try {
            return Color.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Неверное значение color. Возможные: " + Color.getValues());
        }
    }
}
