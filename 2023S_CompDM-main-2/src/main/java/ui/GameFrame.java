package src.main.java.ui;


import src.main.java.Controller.*;
import src.main.java.domain.Board.Map;
import src.main.java.domain.Board.Territory;
import src.main.java.domain.Game;
import src.main.java.domain.Player;
import src.main.java.ui.Animations.Arrow;
import src.main.java.ui.Animations.ArrowAnimation;
import src.main.java.ui.Animations.DiceAnimation;
import src.main.java.ui.Animations.FadeImage;
import src.main.java.ui.Animations.FadeOutAnimation;
import src.main.java.ui.ImageUtils.ImageUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GameFrame extends JFrame {
    public static final int SIZE = 650;

    static String[] nameTerritory_on_ui = {"North West Territory", "Yakutsk", "Greenland", "Iceland", "Alaska", "Scandinavia",
            "Alberta", "Irkutsk", "Ontario", "Kamchatka",
            "Quebec", "Ural", "Serbia", "Great Britain", "Northern Europe",
            "Mongolia", "Western United States", "Japan", "Ukraine", "Afghanistan",
            "Eastern United States", "Southern Europe", "Western Europe", "China",
            "Central America", "Venezuela", "Egypt", "Siam", "Middle East", "India", "New Guinea", "North Africa",
            "Peru", "Indonesia", "Brazil", "Congo",
            "East Africa", "Madagascar", "Western Australia", "South Africa",
            "Eastern Australia",
            "Argentina"};
    private static GameFrame instance;
    private final MyArea area;
    private final ArrayList<MyArea> shapeList;
    private final JButton endDraftButton;
    private final JLabel turnText = new JLabel("Start placement by clicking your own territory", SwingConstants.CENTER);
    private final JButton setDefender;
    private final JButton attackButton;
    private final JLabel output = new JLabel();
    private final JButton tradeButton;
    private final JButton tradeTerritoryButton;
    private final DiceAnimation diceAnimation = new DiceAnimation();
    private final JLabel attackerText = new JLabel("Attacker dice");
    private final JLabel defenderText = new JLabel("Defender dice");
    private final JLabel dice1 = new JLabel();
    private final JLabel dice2 = new JLabel();
    FadeOutAnimation fadeOutAnimation = new FadeOutAnimation();
	FadeImage fadeImage = new FadeImage();
    private final JPanel playersPanel = new JPanel(new GridLayout(6, 1));
    private final JLayeredPane layeredPane;
    private final ArrayList<ArrowAnimation> arrowAnimations = new ArrayList<ArrowAnimation>();
    private JButton nextButton;
    private BufferedImage image;
    private JComboBox<String> armyBox;
    private JButton setAttacker;
    private JButton endAttack;
    private JButton endFortify;
    private JButton setSource;
    private JButton setTarget;
    private JButton fortifyButton;
    private JButton playChance;
    private String lastClickedCellId = null;

    private GameFrame() {

        Game game = Game.getInstance();
        PhaseMouseAdapterFactory.setGameFrame(this);

        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent ui = new JPanel(new BorderLayout());

        JPanel belowButtonPanel = new JPanel(new GridLayout(1, 3));
        JPanel upperButtonPanel = new JPanel(new GridLayout(2, 2));


        try {
            image = ImageIO.read(new File("./src/main/resources/map4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        area = ImageUtils.getOutline(Color.WHITE, image, 12);
        shapeList = ImageUtils.separateShapeIntoRegions(area, nameTerritory_on_ui);


        ui.setBorder(new EmptyBorder(4, 4, 4, 4));
        layeredPane = getLayeredPane();

        output.setLayout(null);

        for (MyArea shape : shapeList) {
            Territory ter = game.getMap().getTerritory(shape.toString());

            YLabel label = new YLabel(ter, shape);
            label.addMouseListener(PhaseMouseAdapterFactory.getMapRecreationAdapter());

            output.add(label);

        }

        output.setBounds(0, 70, 968, 648);
        layeredPane.add(output, 1);

        //Add players panel

        playersPanel.setBounds(970, 70, 100, 648);
        playersPanel.setBackground(Color.black);
        updatePlayerPanel();
        layeredPane.add(playersPanel);

        // Create the help button
        HelpButton helpButton = new HelpButton("Help");

        tradeButton = new JButton("Trade Army Cards");
        tradeButton.addActionListener(e -> {

            TradeFrame tradeFrame = new TradeFrame(this, game);
            tradeFrame.setVisible(true);
            TradeController.setFrame(tradeFrame);
            TradeController.tradeTypeAdder();
        });
        
        //trade Army bu
        tradeTerritoryButton = new JButton("Trade Territory Card");
        tradeTerritoryButton.addActionListener(e -> {
            TradeTerritoryCardFrame tradeFrame = new TradeTerritoryCardFrame(this, game);
            tradeFrame.setVisible(true);
        });

        JButton deleteTerritoryButton = new JButton("Delete Territory");
        deleteTerritoryButton.addActionListener(e -> {

            YLabel.changePanelAdapters(PhaseMouseAdapterFactory.getInitArmyAdapter());
            belowButtonPanel.removeAll();

            belowButtonPanel.add(nextButton);
            upperButtonPanel.add(turnText, BorderLayout.SOUTH);

            turnText.setVisible(true);

            belowButtonPanel.revalidate();
            belowButtonPanel.repaint();

        });

        endDraftButton = new JButton("End Draft");
        endDraftButton.addActionListener(e -> {
            YLabel.changePanelAdapters(PhaseMouseAdapterFactory.getAttackAdapter());
            belowButtonPanel.removeAll();

            belowButtonPanel.add(endAttack);
            belowButtonPanel.add(setAttacker);
            upperButtonPanel.add(armyBox);

            belowButtonPanel.revalidate();
            belowButtonPanel.repaint();

        });


        //A button to move from army placement to attack
        nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {

            if (InitializingArmyController.isAllArmiesPlaced()) {
                YLabel.changePanelAdapters(PhaseMouseAdapterFactory.getDraftAdapter());
                belowButtonPanel.removeAll();

                belowButtonPanel.add(endDraftButton);
                belowButtonPanel.add(tradeButton);
                belowButtonPanel.add(tradeTerritoryButton);
                upperButtonPanel.add(armyBox);

                belowButtonPanel.revalidate();
                belowButtonPanel.repaint();

            } else {
                JOptionPane.showMessageDialog(this, "Phase not completed!");
            }
        });

        //A button to attack
        attackButton = new JButton("Attack");
        attackButton.addActionListener(e -> {
            stopArrowAnimation();
            System.out.println("Attack");
            AttackController.attack();
            belowButtonPanel.remove(attackButton);
            belowButtonPanel.add(setAttacker);
            belowButtonPanel.revalidate();
            belowButtonPanel.repaint();
        });

        //Set defender
        setDefender = new JButton("Set Defender");
        setDefender.addActionListener(e -> {
            String defenderID = getLastClickedCellId();
            AttackController.setDefender(defenderID);
            stopArrowAnimation();

            System.out.println("Defender ID" + defenderID);


            belowButtonPanel.remove(setDefender);
            belowButtonPanel.add(attackButton);
            belowButtonPanel.revalidate();
            belowButtonPanel.repaint();
        });

        // set attacker
        setAttacker = new JButton("Set Attacker");
        setAttacker.addActionListener(e -> {
            stopArrowAnimation();
            String attackerID = getLastClickedCellId();
            AttackController.setAttacker(attackerID);
            startArrowAnimation(attackerID, AttackController.getAttackable());

            System.out.println("Attacker ID" + attackerID);
            belowButtonPanel.remove(setAttacker);
            belowButtonPanel.add(setDefender);
            belowButtonPanel.revalidate();
            belowButtonPanel.repaint();
        });

        // A button for ending attack phase and move fortify
        endAttack = new JButton("End Attack");
        endAttack.addActionListener(e -> {
            belowButtonPanel.removeAll();
            belowButtonPanel.add(endFortify);
            belowButtonPanel.add(setSource);
            belowButtonPanel.revalidate();
            belowButtonPanel.repaint();
        });

        // A button for ending fortify
        endFortify = new JButton("End Fortify");
        endFortify.addActionListener(e -> {
            belowButtonPanel.removeAll();
            belowButtonPanel.add(playChance);
            belowButtonPanel.revalidate();
            belowButtonPanel.repaint();
            game.endPlayerTurn();
            updateTurnText(game.getCurrentPlayer());
            ChanceCardController.getInstance().pickChance();
            JOptionPane.showMessageDialog(this, ChanceCardController.getInstance().getMessage());
            //DraftController.start();
        });

        playChance = new JButton("Play Chance");
        playChance.addActionListener(e -> {
            ChanceCardController.getInstance().start();
            belowButtonPanel.removeAll();
            belowButtonPanel.add(endDraftButton);
            belowButtonPanel.add(tradeButton);
            belowButtonPanel.add(tradeTerritoryButton);
            belowButtonPanel.revalidate();
            belowButtonPanel.repaint();
            YLabel.changePanelAdapters(PhaseMouseAdapterFactory.getDraftAdapter());

            updateTurnText(game.getCurrentPlayer());
            DraftController.start();
        });


        //Set a source territory for fortification
        setSource = new JButton("Set Source");
        setSource.addActionListener(e -> {
            String sourceID = getLastClickedCellId();
            FortifyController.setFromTerr(sourceID);
            belowButtonPanel.remove(setSource);
            belowButtonPanel.add(setTarget);
            belowButtonPanel.revalidate();
            belowButtonPanel.repaint();
        });
        //Set a target territory for fortification
        setTarget = new JButton("Set Target");
        setTarget.addActionListener(e -> {
            FortifyController.setToTerr(getLastClickedCellId());
            belowButtonPanel.remove(setTarget);
            belowButtonPanel.add(fortifyButton);
            belowButtonPanel.revalidate();
            belowButtonPanel.repaint();
        });
        //Fortify
        fortifyButton = new JButton("Fortify");
        fortifyButton.addActionListener(e -> {
            FortifyController.fortify();
            belowButtonPanel.remove(fortifyButton);
            belowButtonPanel.add(setSource);
            belowButtonPanel.revalidate();
            belowButtonPanel.repaint();
        });

        // Dropbox for attacker army selection
        String[] armyTypes = {"Infantry", "Cavalry", "Artillery"};
        armyBox = new JComboBox<>(armyTypes);
        JPanel armyTypePanel = new JPanel();
        armyTypePanel.add(armyBox);
        add(armyTypePanel, BorderLayout.NORTH);
        armyBox.addActionListener(e -> {
            String selectedArmyType = (String) armyBox.getSelectedItem();
            AttackController.setArmyType(selectedArmyType);
        });


        //Add dice animation elements
        dice1.setVisible(false);
        dice1.setBounds(10, 500, 0, 0);
        layeredPane.add(dice1, 0);


        dice2.setVisible(false);
        dice2.setBounds(10, 550, 0, 0);
        layeredPane.add(dice2, 0);

        attackerText.setVisible(false);
        attackerText.setBounds(50, 515, 0, 0);
        layeredPane.add(attackerText, 0);

        defenderText.setVisible(false);
        defenderText.setBounds(50, 565, 0, 0);
        layeredPane.add(defenderText, 0);
        
        layeredPane.add(fadeImage, 0);
        
        PauseButton pauseButton = new PauseButton();

        upperButtonPanel.add(helpButton);
        upperButtonPanel.add(pauseButton);

        belowButtonPanel.add(deleteTerritoryButton);

        add(ui);
        add(belowButtonPanel, BorderLayout.SOUTH);
        add(upperButtonPanel, BorderLayout.NORTH);

        refresh();
    }

    public static GameFrame getInstance() {
        if (null == instance) {
            instance = new GameFrame();
        }
        return instance;
    }
    

    
    public void startArrowAnimation(String attacker, ArrayList<String> neighbors) { //
        //Point attackerCenter = new Point(1, 1);
        Point neighborCenter;

        YLabel attackLabel = YLabel.getLabelByName(attacker);

        assert attackLabel != null;
        Point attackCenter = attackLabel.getLocation();

        attackCenter.setLocation(attackCenter.x, attackCenter.y + 70);

        SwingUtilities.convertPointToScreen(attackCenter, attackLabel);


        // attackerCenter = new Point((int) label.getX(), label.getY() + 70);
        /*
        for (YLabel label : YLabel.getLabels()) {
            if (label.getName().equals(attacker)) {
                Point p = label.getLocationOnScreen();
                SwingUtilities.convertPointToScreen(p, label);
                SwingUtilities.convertPointFromScreen(p, layeredPane);

                // attackerCenter = new Point((int) label.getX(), label.getY() + 70);
                attackerCenter = p;
            }
        }

         */

        for (String neighbor : neighbors) {

            YLabel label = YLabel.getLabelByName(neighbor);
            assert label != null;
            Point p = label.getLocation();
            p.setLocation(p.x, p.y + 70);
            SwingUtilities.convertPointToScreen(p, label);

            //neighborCenter = new Point((int) label.getX(), label.getY() + 70);
            ArrowAnimation animation = new ArrowAnimation();
            arrowAnimations.add(animation);
            Arrow arrow = new Arrow();
            layeredPane.add(arrow, 0);
            animation.animate(arrow, attackCenter.x, attackCenter.y, p.x, p.y);
            /*
            for (YLabel label : YLabel.getLabels()) {
                if (label.getName().equals(neighbor)) {

                    Point p = label.getLocationOnScreen();
                    SwingUtilities.convertPointToScreen(p, label);
                    SwingUtilities.convertPointFromScreen(p, layeredPane);

                    //neighborCenter = new Point((int) label.getX(), label.getY() + 70);
                    ArrowAnimation animation = new ArrowAnimation();
                    arrowAnimations.add(animation);
                    Arrow arrow = new Arrow();
                    layeredPane.add(arrow, 0);
                    animation.animate(arrow, attackerCenter.x, attackerCenter.y, p.x, p.y);

                }
            }

             */
        }
    }
    public void animateFadeOut(String territory, int val) {
    	YLabel terLabel = YLabel.getLabelByName(territory);
    	Point terCenter = terLabel.getLocation();

    	fadeOutAnimation.animate(fadeImage, (int) terCenter.x, (int) terCenter.y+70 , val);
    	

    }
    
    public void updatePlayerPanel() {
        playersPanel.removeAll(); // Clear the existing labels

        for (Player p : Game.getInstance().getPlayers()) {
            JLabel playerLabel = new JLabel();
            playerLabel.setForeground(chooseColor(p.getColor()));
            String labelText = "<html>" + p.getName() + "<br>" + "Territories: " + p.getTerritories().size() + "<br>" + p.isAlive() + "</html>";
            playerLabel.setText(labelText);
            playersPanel.add(playerLabel);
        }

        // Repaint the panel and its components
        playersPanel.revalidate();
        playersPanel.repaint();
    }


    public void stopArrowAnimation() {
        for (ArrowAnimation arrowAnimation : arrowAnimations) {
            arrowAnimation.stop();
        }
    }

    public String getLastClickedCellId() {
        return lastClickedCellId;
    }

    public void setLastClickedCellId(String lastClickedCellId) {
        this.lastClickedCellId = lastClickedCellId;
    }

    public String getArmyBoxVal() {
        return Objects.requireNonNull(armyBox.getSelectedItem()).toString();
    }

    public void updateTurnText(Player player) {
        turnText.setText(player.getName() + " has I/C/A " + player.getArmy().toString());
    }

    public void updateTerritoryStatus(String cellId) {
        // TODO Auto-generated method stub
        for (MyArea a : shapeList) {
            if (a.getName().equals(cellId)) {

                Territory t = Map.getMap().getTerritory(cellId);
                if (t.isVisible()) {
                    if (t.getOwner() == null) {
                        a.setColor(Color.ORANGE.darker());
                    } else {
                        a.setColor(chooseColor(t.getOwner().getColor()));
                    }
                } else {
                    a.setColor(Color.GRAY);
                }
                break;
            }
        }
        updatePlayerPanel();

    }


    public void animateDice(int attackerDice, int defenderDice) {
        diceAnimation.animate(dice1, attackerDice, attackerText, dice2, defenderDice, defenderText);
    }

    //Below that for the creation of the map from image
    public void refresh() {
        output.setIcon(new ImageIcon(getImage()));
    }

    private BufferedImage getImage() {
        BufferedImage bi = new BufferedImage(
                2 * SIZE, SIZE, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = bi.createGraphics();
        g.drawImage(image, 0, 0, output);
        g.setColor(Color.RED);
        g.draw(area);
        try {

            for (MyArea shape : shapeList) {

                g.setColor(shape.getColor());
                g.fill(shape);

            }
        } catch (Exception ignored) {
        }
        g.dispose();
        return bi;
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





