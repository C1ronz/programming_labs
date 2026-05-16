package util;

import exceptions.ValidationException;
import models.*;
import util.validation.CoordinatesValidator;
import util.validation.DragonValidator;
import util.validation.PersonValidator;
import util.validation.Validator;

import java.time.ZonedDateTime;

/**
 * Класс для генерации дракона из пользовательских аргументов
 * @author C1ronz
 */
public class InteractiveDragonBuilder {

    /**
     * Парсит строки в boolean.
     * @throws ValidationException выбрасывает ошибку если не введен не верный boolean
     */
    public static boolean parseBoolean (String speaking){
        switch (speaking){
            case "да", "Да", "yes", "Yes": return true;
            case "нет", "Нет", "No", "Not", "no", "not": return false;
            default: throw new ValidationException("speaking может быть <да> или <нет>");
        }
    }

    public static String parseString (String string){
        return string.trim();
    }

    /**
     * Создаёт дракона.
     */
    public static Dragon buildDragon (){
        long id = Dragon.generateId();
        String name = ConsoleDragonReader.getValidatedInput("Введите имя дракона", InteractiveDragonBuilder::parseString, DragonValidator::validateName);
        Coordinates coordinates = buildCoordinates();
        long age = ConsoleDragonReader.getValidatedInput("Введите возраст дракона", Long::parseLong, DragonValidator::validateAge);
        boolean speaking = ConsoleDragonReader.getValidatedInput("Может ли дракон говорить - да/нет", InteractiveDragonBuilder::parseBoolean, DragonValidator::validateSpeaking);
        Color color = ConsoleDragonReader.getValidatedInput("Введите цвет дракона из "+Color.getValues(), Color::parseColor, DragonValidator::validateColor);
        DragonCharacter character = ConsoleDragonReader.getValidatedInput("Введите характер дракона из "+DragonCharacter.getValues(), DragonCharacter::parseCharacter, DragonValidator::validateCharacter);
        Person killer = buildPerson();
        return new Dragon(id, name, coordinates, ZonedDateTime.now(), age, speaking,color, character,killer);
    }

    /**
     * Создаёт координаты.
     */
    public static Coordinates buildCoordinates (){
        int x = ConsoleDragonReader.getValidatedInput("Введите x", Integer::parseInt, CoordinatesValidator::validateX);
        Long y = ConsoleDragonReader.getValidatedInput("Введите y", Long::parseLong, CoordinatesValidator::validateY);
        return new Coordinates(x,y);
    }

    /**
     * Создаёт человека.
     */
    public static Person buildPerson (){
        String name = ConsoleDragonReader.getValidatedInput("Введите имя убийцы или enter если убийцы нет", InteractiveDragonBuilder::parseString, PersonValidator::validateNameFromConsole);
        if (name == null){
            return null;
        }
        Long height = ConsoleDragonReader.getValidatedInput("Введите рост "+ name, Long::parseLong, PersonValidator::validateHeight);
        Color eyeColor = ConsoleDragonReader.getValidatedInput("Введите цвет глаз "+ name + " из " + Color.getValues(), Color::parseColor, PersonValidator::validateEyeHColor);
        Color hairColor = ConsoleDragonReader.getValidatedInput("Введите цвет волос "+ name + " из " + Color.getValues(), Color::parseColor, PersonValidator::validateHairColor);
        Country nationality = ConsoleDragonReader.getValidatedInput("Введите страну рождения "+ name + " из " + Country.getValues(), Country::parseCountry, PersonValidator::validateNationality);
        return new Person(name, height, eyeColor,hairColor,nationality);
    }
}
