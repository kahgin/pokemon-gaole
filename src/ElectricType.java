
public class ElectricType extends Pokemon {
    public ElectricType() {
        super();
        setType("Electric");
    }

    public ElectricType(String name, int hp, int att, int def) {
        super(name, hp, att, def);
        setType("Electric");
    }

    public double effectiveness(String enemyType) {
        double effectiveness = 1;
        if (enemyType.equals("Ground")) {
            effectiveness = 2;
        }

        else if (enemyType.equals("Electric")) {
            effectiveness = 0.5;
        }

        return effectiveness;
    }
}
