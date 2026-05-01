package models;

import exceptions.ValidationException;

public enum DragonCharacter {
    CUNNING,
    WISE,
    GOOD,
    FICKLE;


    public static String getValues() {
        DragonCharacter[] values = values();
        String[] names = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            names[i] = values[i].name();
        }
        return String.join(", ", names);
    }

    public static DragonCharacter parseCharacter(String value) {
        if (value == null || value.trim().isEmpty()) return null;
        try {
            return DragonCharacter.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Неверное значение character. Возможные: " + Color.getValues());
        }
    }
}
