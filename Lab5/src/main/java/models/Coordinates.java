package models;


import exceptions.WrongArgument;

public class Coordinates {
    private int x; //Значение поля должно быть больше -600
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
    public String toString (){
        return "x:" + x + ", y:" + y.toString();
    }

}
