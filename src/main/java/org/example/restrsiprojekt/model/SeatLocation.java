package org.example.restrsiprojekt.model;

public class SeatLocation {

    private Integer x;
    private Integer y;

    public SeatLocation(Integer x, Integer y){
        if(x > 10 || y > 10){
            throw new RuntimeException("No such seat location");
        }
        this.x = x;
        this.y = y;
    }

    public SeatLocation() {
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
