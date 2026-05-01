package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvRecurse;
import exceptions.MustNotNullException;
import exceptions.WrongArgument;
import util.ZonedDateTimeConverter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;


public class Dragon implements Comparable<Dragon>{

    @CsvBindByName(column = "id")
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @CsvBindByName(column = "name")
    private String name; //Поле не может быть null, Строка не может быть пустой
    @CsvRecurse
    private Coordinates coordinates; //Поле не может быть null
    @CsvCustomBindByName(column = "creationDate", converter = ZonedDateTimeConverter.class)
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @CsvBindByName(column = "age")
    private long age; //Значение поля должно быть больше 0
    @CsvBindByName(column = "speaking")
    private boolean speaking;
    @CsvBindByName(column = "color")
    private Color color; //Поле может быть null
    @CsvBindByName(column = "character")
    private DragonCharacter character; //Поле может быть null
    @CsvRecurse
    private Person killer; //Поле может быть null

    private static long nextId = 1;
    private static Set<Long> usedDragonId = new HashSet<>();


    public Dragon (long id, String name, Coordinates coordinates, java.time.ZonedDateTime creationDate, long age, boolean speaking, Color color, DragonCharacter character, Person killer){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.age = age;
        this.speaking = speaking;
        this.color = color;
        this.character = character;
        this.killer = killer;
        this.creationDate = creationDate;
    }

    public Dragon (){}

    public static long generateId (){
        while (!isIdUnique(nextId)){
            nextId++;
        }
        return nextId;
    }

    public static void removeId (Long id){
        if (!isIdUnique(id)) {
            usedDragonId.remove(id);
        }
    }

    public static void addId (Long id){
        usedDragonId.add(id);
    }

    public static boolean isIdUnique (long id){
        return !usedDragonId.contains(nextId);
    }

    public static Set<Long> getUsedDragonId(){
        return usedDragonId;
    }


    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }


    public Person getKiller() {
        return killer;
    }

    public void setKiller(Person killer) {
        this.killer = killer;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public ZonedDateTime getCreationDate() { return creationDate; }
    public void setCreationDate(ZonedDateTime creationDate) { this.creationDate = creationDate; }

    public Long getAge() { return age; }
    public void setAge(Long age) { this.age = age; }

    public boolean getSpeaking() { return speaking; }
    public void setSpeaking(boolean speaking) { this.speaking = speaking; }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }

    public DragonCharacter getCharacter() { return character; }
    public void setCharacter(DragonCharacter character) { this.character = character; }


    @Override
    public int compareTo (Dragon other){
        return Long.compare(this.id, other.id);
    }

    @Override
    public String toString (){
        return String.format("id:%d, name:%s, coordinates(%s), creationDate:%s, age:%d, speaking:%b, color:%s, character:%s, killer(%s);",
                id,
                name == null ? " " : name,
                coordinates == null ? " " : coordinates.toString(),
                creationDate == null ? " " : creationDate.toString(),
                age,
                speaking,
                color == null ? " " : color.toString(),
                character == null ? " " : character.toString(),
                killer == null ? " " : killer.toString());
    }
}
