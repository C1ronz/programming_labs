package util;

import exceptions.ValidationException;
import models.*;

import java.time.ZonedDateTime;

/**
 * Класс для генерации дракона из пользовательских аргументов
 * @author C1ronz
 */
public class InteractiveDragonBuilder {

    /**
     * Парсит строки в boolean.
     * @throws ValidationException
     */
    public static boolean parseBoolean (String speaking){
        switch (speaking){
            case "да", "Да", "yes", "Yes": return true;
            case "нет", "Нет", "No", "Not", "no", "not": return false;
            default: throw new ValidationException("speaking может быть <да> или <нет>");
        }
    }

    /**
     * Создаёт дракона.
     */
    public static Dragon buildDragon (){
        long id = Dragon.generateId();
        String name = ConsoleDragonReader.getValidatedInput("Введите имя дракона", Validator::validateDragonName);
        Coordinates coordinates = buildCoordinates();
        long age = ConsoleDragonReader.getValidatedInput("Введите возраст дракона", s -> Long.parseLong(s), Validator::validateAge);
        boolean speaking = ConsoleDragonReader.getValidatedInput("Может ли дракон говорить - да/нет", s -> InteractiveDragonBuilder.parseBoolean(s), Validator::validateSpeaking);
        Color color = ConsoleDragonReader.getValidatedInput("Введите цвет дракона из "+Color.getValues(), s -> Color.parseColor(s), Validator::validateColor);
        DragonCharacter character = ConsoleDragonReader.getValidatedInput("Введите характер дракона из "+DragonCharacter.getValues(), s -> DragonCharacter.parseCharacter(s), Validator::validateCharacter);
        Person killer = buildPerson();
        return new Dragon(id, name, coordinates, ZonedDateTime.now(), age, speaking,color, character,killer);
    }

    /**
     * Создаёт координаты.
     */
    public static Coordinates buildCoordinates (){
        int x = ConsoleDragonReader.getValidatedInput("Введите x", s -> Integer.parseInt(s), Validator::validateX);
        Long y = ConsoleDragonReader.getValidatedInput("Введите y", s -> Long.parseLong(s), Validator::validateY);
        return new Coordinates(x,y);
    }

    /**
     * Создаёт человека.
     */
    public static Person buildPerson (){
        String name = ConsoleDragonReader.getValidatedInput("Введите имя убийцы или enter если убийцы нет", Validator::validatePersonName);
        if (name == null || name.isEmpty()){
            return null;
        }
        Long height = ConsoleDragonReader.getValidatedInput("Введите рост "+ name, s -> Long.parseLong(s), Validator::validateHeight);
        Color eyeColor = ConsoleDragonReader.getValidatedInput("Введите цвет глаз "+ name + " из " + Color.getValues(), s-> Color.parseColor(s), Validator::validateEyeHColor);
        Color hairColor = ConsoleDragonReader.getValidatedInput("Введите цвет волос "+ name + " из " + Color.getValues(), s-> Color.parseColor(s), Validator::validateHairColor);
        Country nationality = ConsoleDragonReader.getValidatedInput("Введите страну рождения "+ name + " из " + Country.getValues(), s-> Country.parseCountry(s), Validator::validateNationality);
        return new Person(name, height, eyeColor,hairColor,nationality);
    }
}
