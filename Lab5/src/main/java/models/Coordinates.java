package models;


import com.opencsv.bean.CsvBindByName;


/**
 * Класс координат.
 * @author C1ronz
 */
public class Coordinates implements Comparable<Coordinates> {
    @CsvBindByName(column = "x")
    private int x; //Значение поля должно быть больше -600
    @CsvBindByName(column = "y")
    private Long y;//Максимальное значение поля: 885, Поле не может быть null

    public Coordinates (int x, Long y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates (){}

    public int getX() {return x;}
    public void setX(int x) {this.x = x;}

    public Long getY() {return y;}
    public void setY(Long y) {this.y = y;}

    @Override
    public int compareTo(Coordinates other) {
        return Long.compare(this.x + this.y, other.x + other.y);
    }

    @Override
    public String toString (){
        return String.format("x:%d, y:%s", x, y == null ? " " : y.toString());
    }

}
