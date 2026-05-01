package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import exceptions.MustNotNullException;
import exceptions.WrongArgument;

import java.time.ZonedDateTime;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long height; //Поле может быть null, Значение поля должно быть больше 0
    private Color eyeColor; //Поле может быть null
    private Color hairColor; //Поле не может быть null
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

    @JsonProperty("name")
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @JsonProperty("height")
    public Long getHeight() {return height;}
    public void setHeight(Long height) {this.height = height;}

    @JsonProperty("eyeColor")
    public Color getEyeColor() {return eyeColor;}
    public void setEyeColor(Color eyeColor) {this.eyeColor = eyeColor;}

    @JsonProperty("hairColor")
    public Color getHairColor() {return hairColor;}
    public void setHairColor(Color hairColor) {this.hairColor = hairColor;}

    @JsonProperty("nationality")
    public Country getNationality() {return nationality;}
    public void setNationality(Country nationality) {this.nationality = nationality;}

    @Override
    public String toString (){
        return String.format("name:%s, height:%d, eyeColor:%s, hairColor:%s, nationality:%s", name, height, eyeColor.toString(), hairColor.toString(), nationality.toString());
    }

}
