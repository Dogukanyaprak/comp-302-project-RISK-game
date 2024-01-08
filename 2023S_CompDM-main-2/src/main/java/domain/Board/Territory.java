package src.main.java.domain.Board;

import src.main.java.domain.Army;
import src.main.java.domain.Observer;
import src.main.java.domain.Player;

import java.io.Serializable;
import java.util.ArrayList;


public class Territory implements Serializable {
    private final ArrayList<Observer> observers = new ArrayList<>();
    private final String name;
    private final Army army = new Army();
    private final ArrayList<Territory> neighbours = new ArrayList<Territory>();
    private Player owner;
    private boolean attackable;
    private boolean visible;

    public Territory(String name) {
        this.name = name;

        attackable = true;
        visible = true;
    }

    public void addNeighbour(Territory newNeighbour) {
        if (!isNeighbour(newNeighbour)) {
            neighbours.add(newNeighbour);
        }
    }

    public void deleteNeighbour(Territory oldNeighbour) {
        if (!isNeighbour(oldNeighbour)) {
            neighbours.remove(oldNeighbour);
        }

    }

    public boolean isNeighbour(Territory territory) {
        return neighbours.contains(territory);
    }

    public void addArmy(String type, int amount) {
        army.addArmy(type, amount);
        notifyAllObservers();
    }

    //error handler can be needed for controlling if there is negative number of army for any type
    public void deleteArmy(String type, int amount) {
        army.deleteArmy(type, amount);
        notifyAllObservers();
    }


    public void toggleVisibility() {
        visible = !visible;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player newOwner) {

        newOwner.addTerritory(this);

        if (null != owner) {
            owner.removeTerritory(this);
        }

        owner = newOwner;

    }

    public String getName() {
        return name;
    }

    public ArrayList<Territory> getNeighbours() {
        return neighbours;
    }

    public boolean isAttackable() {
        if (!visible) {
            return false;
        }
        return attackable;
    }

    public void setAttackable(boolean attackable) {
        this.attackable = attackable;
    }


    public boolean isVisible() {
        return visible;
    }

    public Army getArmy() {
        return army;
    }


    public void subscribe(Observer cellPanel) {
        observers.add(cellPanel);
    }

    public void notifyAllObservers() {
        for (Observer obs : observers) {
            obs.update(this);
        }
    }
}
