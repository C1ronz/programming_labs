package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;
import exceptions.MustNotNullException;
import exceptions.WrongArgument;

import java.time.ZonedDateTime;

/**
 * Класс человека.
 * @author C1ronz
 */
public class Person implements Comparable<Person> {
    @CsvBindByName(column = "person_name")
    private String name; //Поле не может быть null, Строка не может быть пустой
    @CsvBindByName(column = "person_height")
    private Long height; //Поле может быть null, Значение поля должно быть больше 0
    @CsvBindByName(column = "person_eyeColor")
    private Color eyeColor; //Поле может быть null
    @CsvBindByName(column = "person_hairColor")
    private Color hairColor; //Поле не может быть null
    @CsvBindByName(column = "person_nationality")
    private Country nationality; //Поле не может быть null

    public Person (String name, Long height, Color eyeColor, Color hairColor, Country nationality)
    {
        this.name = name;
        this.height = height;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
    }

    public Person(){}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public Long getHeight() {return height;}
    public void setHeight(Long height) {this.height = height;}

    public Color getEyeColor() {return eyeColor;}
    public void setEyeColor(Color eyeColor) {this.eyeColor = eyeColor;}

    public Color getHairColor() {return hairColor;}
    public void setHairColor(Color hairColor) {this.hairColor = hairColor;}

    public Country getNationality() {return nationality;}
    public void setNationality(Country nationality) {this.nationality = nationality;}


    @Override
    public int compareTo(Person other) {
        return this.name.compareTo(other.name);
    }


    @Override
    public String toString (){
        return String.format("name:%s, height:%s, eyeColor:%s, hairColor:%s, nationality:%s",
                name == null ? " " : name,
                height == null ? " " :  height,
                eyeColor == null ? " " :  eyeColor.toString(),
                hairColor == null ? " " : hairColor.toString(),
                nationality == null ? " " :  nationality.toString());
    }

}
