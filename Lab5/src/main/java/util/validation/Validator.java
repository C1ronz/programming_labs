package util.validation;
import exceptions.ValidationException;
import models.*;


/**
 * Класс содержащий всю валидацию программы.
 * При обнаружении некорректных данных выбрасывает ValidationException.
 * @author C1ronz
 */
public final class Validator {

    /**
     * Валидирует и создает валидный объект Coordinates.
     *
     * @param coordinates проверяемые координаты
     * @return новый объект Coordinates с валидными полями
     * @throws ValidationException если coordinates == null или поля не проходят валидацию
     */
    public static Coordinates validateCoordinates(Coordinates coordinates) {
        return CoordinatesValidator.validate(coordinates);
    }

    /**
     * Валидирует и создает валидный объект Person (убийцу дракона).
     *
     * @param person проверяемый человек
     * @return новый объект Person с валидными полями
     * @throws ValidationException если поля не проходят валидацию
     */
    public static Person validatePerson (Person person){
        return PersonValidator.validate(person);
    }

    /**
     * Валидирует и создает валидный объект Dragon.
     *
     * @param dragon проверяемый дракон
     * @return новый объект Dragon с валидными полями
     * @throws ValidationException если dragon == null или любое поле не проходит валидацию
     */
    public static Dragon validateDragon (Dragon dragon){
        return DragonValidator.validate(dragon);
    }

}
