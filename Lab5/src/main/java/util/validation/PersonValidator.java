package util.validation;

import exceptions.ValidationException;
import models.Color;
import models.Country;
import models.Person;

public class PersonValidator {

    /**
     * Валидирует и создает валидный объект Person (убийцу дракона).
     *
     * @param person проверяемый человек
     * @return новый объект Person с валидными полями
     * @throws ValidationException если поля не проходят валидацию
     */
    public static Person validate (Person person){
        if (person == null) {return null;}
        return new Person(
                validateName(person.getName()),
                validateHeight(person.getHeight()),
                validateEyeHColor(person.getEyeColor()),
                validateHairColor(person.getHairColor()),
                validateNationality(person.getNationality())
        );
    }


    /**
     * Валидирует имя человека.
     *
     * @param name проверяемое имя
     * @return обрезанное по краям валидное имя
     * @throws ValidationException если name == null или состоит из пробелов
     */
    public static String validateName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new ValidationException("name не может быть null или пустым");
        return name.trim();
    }

    /**
     * Валидирует имя человека введенное с консоли.
     *
     * @param name проверяемое имя
     * @return обрезанное по краям валидное имя
     */
    public static String validateNameFromConsole(String name) {
        if (name == null || name.trim().isEmpty())
            return null;
        return name.trim();
    }

    /**
     * Валидирует рост человека.
     *
     * @param height проверяемый рост
     * @return валидный рост или null, если рост не указан
     * @throws ValidationException если height &lt;= 0
     */
    public static Long validateHeight(Long height) {
        if (height == null) return null;
        if (height <= 0) throw new ValidationException("height должен быть больше 0");
        return height;
    }

    /**
     * Валидирует цвет волос человека.
     *
     * @param hairColor проверяемый цвет волос
     * @return валидный цвет волос
     * @throws ValidationException если hairColor == null
     */
    public static Color validateHairColor(Color hairColor) {
        if (hairColor == null) throw new ValidationException("hairColor не может быть null");
        return hairColor;
    }

    /**
     * Валидирует цвет глаз человека.
     *
     * @param eyeColor проверяемый цвет глаз
     * @return валидный цвет глаз
     * @throws ValidationException если eyeColor == null
     */
    public static Color validateEyeHColor(Color eyeColor) {
        if (eyeColor == null) throw new ValidationException("eyeColor не может быть null");
        return eyeColor;
    }

    /**
     * Валидирует национальность человека.
     *
     * @param nationality проверяемая национальность
     * @return валидная национальность
     * @throws ValidationException если nationality == null
     */
    public static Country validateNationality(Country nationality) {
        if (nationality == null) throw new ValidationException("nationality не может быть null");
        return nationality;
    }
}
