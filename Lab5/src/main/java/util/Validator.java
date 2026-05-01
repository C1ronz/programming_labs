package util;
import exceptions.ValidationException;
import models.*;

import java.time.ZonedDateTime;

/**
 * Класс, содержащий всю валидацию для моделей.
 */
public final class Validator {

    public static long validateId(long id) {
        if (id <= 0) throw new ValidationException("id должен быть больше 0");
        if (!Dragon.isIdUnique(id)) throw new ValidationException("id: " + id + " уже использован. Id должен быть уникальным");
        return id;
    }

    public static String validateDragonName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new ValidationException("name не может быть null или пустым");
        return name.trim();
    }

    public static String validatePersonName(String name) {
        if (name == null || name.trim().isEmpty())
            return null;
        return name.trim();
    }

    public static int validateX(int x) {
        if (x <= -600) throw new ValidationException("x должен быть больше -600");
        return x;
    }

    public static Long validateY(Long y) {
        if (y == null) throw new ValidationException("y не может быть null");
        if (y > 885) throw new ValidationException("y не может быть больше 885");
        return y;
    }

    public static long validateAge(long age) {
        if (age <= 0) throw new ValidationException("age должен быть больше 0");
        return age;
    }

    public static boolean validateSpeaking (Boolean speaking){
        return speaking;
    }

    public static ZonedDateTime validateCreationDate(ZonedDateTime date) {
        if (date == null) throw new ValidationException("creationDate не может быть null");
        return date;
    }

    public static DragonCharacter validateCharacter(String value) {
        if (value == null || value.trim().isEmpty()) return null;
        try {
            return DragonCharacter.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Неверное значение character. Возможные: " + DragonCharacter.getValues());
        }
    }

    public static Color validateColor(Color value) {
        return value;
    }

    public static DragonCharacter validateCharacter(DragonCharacter character) {
        if (character == null) throw new ValidationException("character не может быть null");
        return character;
    }


    public static Long validateHeight(Long height) {
        if (height == null) return null;
        if (height <= 0) throw new ValidationException("height должен быть больше 0");
        return height;
    }

    public static Color validateHairColor(Color hairColor) {
        if (hairColor == null) throw new ValidationException("hairColor не может быть null");
        return hairColor;
    }

    public static Color validateEyeHColor(Color eyeColor) {
        if (eyeColor == null) throw new ValidationException("eyeColor не может быть null");
        return eyeColor;
    }

    public static Country validateNationality(Country nationality) {
        if (nationality == null) throw new ValidationException("nationality не может быть null");
        return nationality;
    }

    public static Coordinates validateCoordinates(Coordinates coordinates) {
        if (coordinates == null) throw new ValidationException("coordinates не может быть null");
        return new Coordinates(validateX(coordinates.getX()), validateY(coordinates.getY()));
    }

    public static Person validatePerson (Person person){
        return new Person(
                validateDragonName(person.getName()),
                validateHeight(person.getHeight()),
                validateEyeHColor(person.getEyeColor()),
                validateHairColor(person.getHairColor()),
                validateNationality(person.getNationality())
        );
    }

    public static Dragon validateDragon (Dragon dragon){
        return new Dragon(
                validateId(dragon.getId()),
                validateDragonName(dragon.getName()),
                validateCoordinates(dragon.getCoordinates()),
                validateCreationDate(dragon.getCreationDate()),
                validateAge(dragon.getAge()),
                dragon.getSpeaking(),
                validateColor(dragon.getColor()),
                validateCharacter(dragon.getCharacter()),
                validatePerson(dragon.getKiller())
        );
    }
}
