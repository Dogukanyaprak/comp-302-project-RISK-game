package src.main.java.ui;


import java.awt.*;
import java.awt.geom.Area;
import java.io.Serializable;

public class MyArea extends Area implements Serializable {
    private final String name;
    private Color color;

    public MyArea(String name) {
        super();
        this.name = name;
    }

    public MyArea(Shape s, String name) {
        super(s);
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}	
