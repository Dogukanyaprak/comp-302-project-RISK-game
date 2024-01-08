package src.main.java.database;

public class MongoAdapter implements DatabaseAdapter{
    private MongoDbAdp mongo;
    @Override
    public boolean saveGame(GameState state) {
        // connect to Mongo
        // mongo.saveGame(state);
        return false;
    }

    @Override
    public GameState loadGame() {
        //mongo.loadGame();
        return null;
    }
}
