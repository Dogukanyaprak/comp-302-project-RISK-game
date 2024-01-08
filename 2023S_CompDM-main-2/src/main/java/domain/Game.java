package src.main.java.domain;


import src.main.java.domain.Board.Map;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Game instance;
    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<Player> alivePlayers = new ArrayList<>();
    private final Map map;
    private int numberOfPlayersAlive = 0;
    private int playerIndex;

    private Game() {
        map = Map.getMap();
    }

    public static Game getInstance() {
        if (null == instance) {
            instance = new Game();
        }
        return instance;
    }

    public static void setInstance(Game instance) {
        Game.instance = instance;
    }

    public void initialize() {

        ArmyDeck.initialize();
        TerritoryDeck.init();

        int initialArmy = (10 - players.size()) * 5;

        for (Player player : players) {

            player.getArmy().addArmy("Infantry", initialArmy);

        }

        //System.out.println(ArmyDeck.sysCards);

    }

    public void killPlayer(Player dead) {
        dead.setAlive(false);
        alivePlayers.remove(dead);
    }

    public int getNumberOfPlayers() {
        return players.size();
    }


    public int getNumberOfPlayersAlive() {
        return alivePlayers.size();
    }


    public Map getMap() {
        return map;
    }

    public Player getCurrentPlayer() {
        return alivePlayers.get(playerIndex);
    }

    public void endPlayerTurn() {
        playerIndex = (playerIndex + 1) % alivePlayers.size();
    }

    public int getTotalArmy() {
        return ((10 - players.size()) * 5) * players.size();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<String> playerNames) {

        String[] colors = {"Blue", "Yellow", "Orange", "Green", "Red", "Pink"};

        for (int i = 0; i < playerNames.size(); i++) {

            Player player = new Player(playerNames.get(i), colors[i]);
            players.add(player);
            alivePlayers.add(player);

        }

        System.out.println("Game is setted with " + playerNames.size() + " players");
    }

    public ArrayList<Player> getAlivePlayers() {
        return alivePlayers;
    }

}
