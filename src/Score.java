
public class Score {
    private int score = 0;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void updateScoreCatchPokemon(boolean caughtPokemon) {
        if (caughtPokemon) {
            score += 1000;
        } else {
            score += 200;
        }
    }

    public void updateScoreWonBattle(boolean wonBattle) {
        if (wonBattle) {
            score += 2000;
        } else {
            score += 500;
        }
    }

    public void displayScores() {
        System.out.printf("You obtained %d score this round!\n", score);
    }
}
