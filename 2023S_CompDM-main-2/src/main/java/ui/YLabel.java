package src.main.java.ui;

import src.main.java.domain.Board.Territory;
import src.main.java.domain.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class YLabel extends JLabel implements Observer {
    private static final ArrayList<YLabel> labels = new ArrayList<>();
    private MyArea area;
    private String ID;

    public YLabel(Territory ter, MyArea area) {

        this.area = area;
        this.ID = ter.getName();

        if (ter.isVisible()) {
            if (ter.getOwner() == null) {
                area.setColor(Color.orange.darker());
            } else {
                area.setColor(chooseColor(ter.getOwner().getColor()));
            }

        } else {
            area.setColor(Color.GRAY);
        }

        this.setText(ter.getName());


        String labelText = "<html>" + ter.getName() + "<br>" + ter.getArmy().toString() + "</html>";
        this.setText(labelText);
        ter.subscribe(this);


        setArea(area);
        setFont(getFont().deriveFont(Font.BOLD, 12));
        setForeground(Color.BLACK);
        setName(area.toString());
        setPreferredSize(new Dimension(70, 70));

        Point center = new Point((int) area.getBounds2D().getCenterX(), (int) area.getBounds2D().getCenterY());
        setBounds(center.x - 25, center.y - 30, 70, 70);

        labels.add(this);

    }

    public static ArrayList<YLabel> getLabels() {
        return labels;
    }

    public static YLabel getLabelByName(String name) {
        for (YLabel label : labels) {
            if (label.getName() == name) {
                return label;
            }
        }
        return null;
    }

    public static void changePanelAdapters(MouseAdapter toAdd) {
        for (JLabel label : labels) {
            MouseListener[] currentListener = label.getMouseListeners();
            label.removeMouseListener(currentListener[0]);
            label.addMouseListener(toAdd);
        }
    }

    public MyArea getArea() {
        return area;
    }

    public void setArea(MyArea area) {
        this.area = area;
    }

    public String getID() {
        return ID;
    }

    public void update(Territory territory) {
        String labelText = "<html>" + territory.getName() + "<br>" + territory.getArmy().toString() + "</html>";
        this.setText(labelText);
        area.setColor(chooseColor(territory.getOwner().getColor()));

        /*
         * setBackground(chooseColor(territory.getOwner().color)); ((JLabel)
         * this.getComponent(1)).setText(territory.getArmy().toString());
         */
    }

    private Color chooseColor(String color) {
        Color c;
        if (color.equals("Blue")) {
            c = Color.CYAN;
        } else if (color.equals("Red")) {
            c = Color.RED;
        } else if (color.equals("Yellow")) {
            c = Color.YELLOW;
        } else if (color.equals("Orange")) {
            c = Color.ORANGE;
        } else if (color.equals("Green")) {
            c = Color.GREEN;
        } else if (color.equals("Pink")) {
            c = Color.PINK;
        } else {
            c = null;
        }
        return c;
    }
}
