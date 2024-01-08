package src.main.java.ui;

import src.main.java.Controller.DraftController;
import src.main.java.Controller.InitializingArmyController;
import src.main.java.Controller.MapRecreationController;
import src.main.java.domain.Board.Map;
import src.main.java.domain.Game;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PhaseMouseAdapterFactory {

    private static final Game game = Game.getInstance();
    private static GameFrame gameFrame;

    private PhaseMouseAdapterFactory() {
    }

    public static void setGameFrame(GameFrame gameFrame) {
        PhaseMouseAdapterFactory.gameFrame = gameFrame;
    }

    public static MouseAdapter getInitArmyAdapter() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                JLabel source = (JLabel) e.getSource();
                String terName = source.getName();

                InitializingArmyController.placeArmy(terName);
                gameFrame.updateTerritoryStatus(terName);
                gameFrame.refresh();
                gameFrame.updateTurnText(game.getCurrentPlayer());
            }
        };
    }

    public static MouseAdapter getMapRecreationAdapter() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel source = (JLabel) e.getSource();
                String cellId = source.getName();
                MapRecreationController.toggleTerritory(cellId);
                gameFrame.updateTerritoryStatus(cellId);
                gameFrame.refresh();
            }
        };
    }

    public static MouseAdapter getDraftAdapter() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                JLabel source = (JLabel) e.getSource();
                String terName = source.getName();
                System.out.println(terName);
                System.out.println(Map.getMap().getTerritory(terName).getOwner());
                DraftController.placeSingleArmy(terName, gameFrame.getArmyBoxVal());
                System.out.println(Map.getMap().getTerritory(terName).getArmy().getTroop("Infantry"));
                gameFrame.updateTurnText(game.getCurrentPlayer());

            }
        };
    }

    public static MouseAdapter getAttackAdapter() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                YLabel source = (YLabel) e.getSource();
                gameFrame.setLastClickedCellId(source.getID()); // Store last clicked cell ID
            }
        };
    }


}
