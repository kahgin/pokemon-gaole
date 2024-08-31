
public class PokeBall {
    private String name;
    private double catchRate;

    public PokeBall() {
        name = "Pokeball";
        catchRate = 1;
    }

    public PokeBall(String name, double catchRate) {
        this.name = name;
        this.catchRate = catchRate;
    }

    public double getCatchRate() {
        return catchRate;
    }

    public void setCatchRate(double catchRate) {
        this.catchRate = catchRate;
    }

    @Override
    public String toString() {
        return name;
    }
}
