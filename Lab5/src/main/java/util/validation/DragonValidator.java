package util.validation;

import exceptions.ValidationException;
import models.Color;
import models.Dragon;
import models.DragonCharacter;

import java.time.ZonedDateTime;

public class DragonValidator {

    /**
     * Валидирует и создает валидный объект Dragon.
     *
     * @param dragon проверяемый дракон
     * @return новый объект Dragon с валидными полями
     * @throws ValidationException если dragon == null или любое поле не проходит валидацию
     */
    public static Dragon validate (Dragon dragon){
        return new Dragon(
                validateId(dragon.getId()),
                validateName(dragon.getName()),
                Validator.validateCoordinates(dragon.getCoordinates()),
                validateCreationDate(dragon.getCreationDate()),
                validateAge(dragon.getAge()),
                dragon.getSpeaking(),
                validateColor(dragon.getColor()),
                validateCharacter(dragon.getCharacter()),
                Validator.validatePerson(dragon.getKiller())
        );
    }


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
    public static String validateName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new ValidationException("name не может быть null или пустым");
        return name.trim();
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
}
