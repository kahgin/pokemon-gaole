import java.util.ArrayList;

public class Player {
    private ArrayList<Pokemon> pokemonsOwned = new ArrayList<Pokemon>();
    private int playerID;
    private MiracleItem miracleItem = null;

    public Player(int playerID) {
        this.playerID = playerID;
    }

    public Player(int playerID, ArrayList<Pokemon> pokemonsOwned) {
        this.playerID = playerID;
        this.pokemonsOwned = pokemonsOwned;
    }

    public ArrayList<Pokemon> getPokemonsOwned() {
        return pokemonsOwned;
    }

    public void setPokemonsOwned(ArrayList<Pokemon> pokemonsOwned) {
        this.pokemonsOwned = pokemonsOwned;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public MiracleItem getMiracleItem() {
        return miracleItem;
    }

    public void setMiracleItem(MiracleItem miracleItem) {
        this.miracleItem = miracleItem;
    }
}