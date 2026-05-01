package util;

import exceptions.ValidationException;
import models.*;

import java.time.ZonedDateTime;


public class InteractiveDragonBuilder {

    public static boolean parseBoolean (String speaking){
        switch (speaking){
            case "да", "Да", "yes", "Yes": return true;
            case "нет", "Нет", "No", "Not", "no", "not": return false;
            default: throw new ValidationException("speaking может быть <да> или <нет>");
        }
    }


    public static Dragon buildDragon (){
        long id = InputArguments.getValidatedInput("Введите id", s -> Long.parseLong(s), Validator::validateId);
        String name = InputArguments.getValidatedInput("Введите имя дракона", Validator::validateDragonName);
        Coordinates coordinates = buildCoordinates();
        long age = InputArguments.getValidatedInput("Введите возраст дракона", s -> Long.parseLong(s), Validator::validateAge);
        boolean speaking = InputArguments.getValidatedInput("Может ли дракон говорить - да/нет", s -> InteractiveDragonBuilder.parseBoolean(s), Validator::validateSpeaking);
        Color color = InputArguments.getValidatedInput("Введите цвет дракона из "+Color.getValues(), s -> Color.parseColor(s), Validator::validateColor);
        DragonCharacter character = InputArguments.getValidatedInput("Введите характер дракона из "+DragonCharacter.getValues(), s -> DragonCharacter.parseCharacter(s), Validator::validateCharacter);
        Person killer = buildPerson();
        return new Dragon(id, name, coordinates, ZonedDateTime.now(), age, speaking,color, character,killer);
    }

    public static Coordinates buildCoordinates (){
        int x = InputArguments.getValidatedInput("Введите x", s -> Integer.parseInt(s), Validator::validateX);
        Long y = InputArguments.getValidatedInput("Введите y", s -> Long.parseLong(s), Validator::validateY);
        return new Coordinates(x,y);
    }

    public static Person buildPerson (){
        String name = InputArguments.getValidatedInput("Введите имя убийцы или enter если убийцы нет", Validator::validatePersonName);
        if (name == null || name.isEmpty()){
            return null;
        }
        Long height = InputArguments.getValidatedInput("Введите рост "+ name,s -> Long.parseLong(s), Validator::validateHeight);
        Color eyeColor = InputArguments.getValidatedInput("Введите цвет глаз "+ name + " из " + Color.getValues(), s-> Color.parseColor(s), Validator::validateEyeHColor);
        Color hairColor = InputArguments.getValidatedInput("Введите цвет волос "+ name + " из " + Color.getValues(), s-> Color.parseColor(s), Validator::validateHairColor);
        Country nationality = InputArguments.getValidatedInput("Введите страну рождения "+ name + " из " + Country.getValues(), s-> Country.parseCountry(s), Validator::validateNationality);
        return new Person(name, height, eyeColor,hairColor,nationality);
    }

}
