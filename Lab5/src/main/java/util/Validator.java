package util;
import exceptions.ValidationException;
import models.*;

import java.time.ZonedDateTime;

/**
 * Класс содержащий всю валидацию программы.
 * При обнаружении некорректных данных выбрасывает ValidationException.
 * @author C1ronz
 */
public final class Validator {

    /**
     * Валидирует id дракона.
     *
     * @param id проверяемый идентификатор
     * @return валидный идентификатор (без изменений)
     * @throws ValidationException если id &lt;= 0 или id не уникален
     */
    public static long validateId(long id) {
        if (id <= 0) throw new ValidationException("id должен быть больше 0");
        if (!Dragon.isIdUnique(id)) throw new ValidationException("id: " + id + " уже использован. Id должен быть уникальным");
        return id;
    }

    /**
     * Валидирует имя дракона.
     *
     * @param name проверяемое имя
     * @return обрезанное по краям валидное имя
     * @throws ValidationException если name == null или состоит из пробелов
     */
    public static String validateDragonName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new ValidationException("name не может быть null или пустым");
        return name.trim();
    }

    /**
     * Валидирует имя человека (убийцы дракона).
     *
     * @param name проверяемое имя
     * @return обрезанное имя или null, если имя не указано
     */
    public static String validatePersonName(String name) {
        if (name == null || name.trim().isEmpty())
            return null;
        return name.trim();
    }

    /**
     * Валидирует координату X.
     *
     * @param x проверяемая координата
     * @return валидная координата X
     * @throws ValidationException если x &lt;= -600
     */
    public static int validateX(int x) {
        if (x <= -600) throw new ValidationException("x должен быть больше -600");
        return x;
    }

    /**
     * Валидирует координату Y.
     *
     * @param y проверяемая координата
     * @return валидная координата Y
     * @throws ValidationException если y == null или y > 885
     */
    public static Long validateY(Long y) {
        if (y == null) throw new ValidationException("y не может быть null");
        if (y > 885) throw new ValidationException("y не может быть больше 885");
        return y;
    }

    /**
     * Валидирует возраст дракона.
     *
     * @param age проверяемый возраст
     * @return валидный возраст
     * @throws ValidationException если age &lt;= 0
     */
    public static long validateAge(long age) {
        if (age <= 0) throw new ValidationException("age должен быть больше 0");
        return age;
    }

    /**
     * Валидирует признак умения говорить.
     *
     * @param speaking проверяемый признак
     * @return переданное значение без изменений
     */
    public static boolean validateSpeaking (Boolean speaking){
        return speaking;
    }

    /**
     * Валидирует дату создания дракона.
     *
     * @param date проверяемая дата
     * @return валидная дата
     * @throws ValidationException если date == null
     */
    public static ZonedDateTime validateCreationDate(ZonedDateTime date) {
        if (date == null) throw new ValidationException("creationDate не может быть null");
        return date;
    }

    /**
     * Валидирует строковое представление характера дракона.
     *
     * @param value строковое значение характера
     * @return соответствующий enum DragonCharacter или null, если значение не указано
     * @throws ValidationException если значение не соответствует ни одному из допустимых
     */
    public static DragonCharacter validateCharacter(String value) {
        if (value == null || value.trim().isEmpty()) return null;
        try {
            return DragonCharacter.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Неверное значение character. Возможные: " + DragonCharacter.getValues());
        }
    }

    /**
     * Валидирует цвет дракона.
     *
     * @param value проверяемый цвет
     * @return переданный цвет без изменений
     */
    public static Color validateColor(Color value) {
        return value;
    }

    /**
     * Валидирует характер дракона (объект enum).
     *
     * @param character проверяемый характер
     * @return валидный характер
     * @throws ValidationException если character == null
     */
    public static DragonCharacter validateCharacter(DragonCharacter character) {
        if (character == null) throw new ValidationException("character не может быть null");
        return character;
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

    /**
     * Валидирует и создает валидный объект Coordinates.
     *
     * @param coordinates проверяемые координаты
     * @return новый объект Coordinates с валидными полями
     * @throws ValidationException если coordinates == null или поля не проходят валидацию
     */
    public static Coordinates validateCoordinates(Coordinates coordinates) {
        if (coordinates == null) throw new ValidationException("coordinates не может быть null");
        return new Coordinates(validateX(coordinates.getX()), validateY(coordinates.getY()));
    }

    /**
     * Валидирует и создает валидный объект Person (убийцу дракона).
     *
     * @param person проверяемый человек
     * @return новый объект Person с валидными полями
     * @throws ValidationException если поля не проходят валидацию
     */
    public static Person validatePerson (Person person){
        if (person == null) {return null;}
        return new Person(
                validateDragonName(person.getName()),
                validateHeight(person.getHeight()),
                validateEyeHColor(person.getEyeColor()),
                validateHairColor(person.getHairColor()),
                validateNationality(person.getNationality())
        );
    }

    /**
     * Валидирует и создает валидный объект Dragon.
     *
     * @param dragon проверяемый дракон
     * @return новый объект Dragon с валидными полями
     * @throws ValidationException если dragon == null или любое поле не проходит валидацию
     */
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
