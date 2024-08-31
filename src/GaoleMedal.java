import java.util.Random;

public class GaoleMedal {
    private int gaoleMedals = 0;
    private int goldenChips = 0;
    private int goldenSands = 0;
    private Random random = new Random();
    private Pokemon pokemonRanAway = null;

    public int getGaoleMedals() {
        return gaoleMedals;
    }

    public void setGaoleMedals(int gaoleMedals) {
        this.gaoleMedals = gaoleMedals;
    }

    public void setPokemonRanAway(Pokemon p) {
        pokemonRanAway = p;
    }

    // return golden chip, golden sand, and total gaole medal earned after each battle
    public void earnGaoleMedal() {
        if (pokemonRanAway == null) {
            System.out.println("You did not receive any Ga-olé medals this round.");
        } else {
            int level = pokemonRanAway.getLevel();
            if (level == 30 || level == 40) { // grade 1 & 2
                goldenSands++;
                gaoleMedals += 10;
            } else if (level == 50 || level == 60) { // grade 3 & 4
                goldenChips++;
                gaoleMedals += 30;
            }

            // Print summary
            System.out.println("Congratulations! You've obtained:");
            System.out.println(goldenChips + " golden chip(s) and " + goldenSands + " golden sand(s)\n");

            // reset values
            pokemonRanAway = null;
            goldenChips = 0;
            goldenSands = 0;
        }
    }

    // Miracle Item Chance!
	public MiracleItem MiracleItemChance() {
        switch (random.nextInt(3)) {
            case 0:
                return new AttackCapsule();
            case 1:
                return new DefenseCapsule();
            default:
                return new PokeBallPower();
        }
    }
}