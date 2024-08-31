
abstract class MiracleItem { // abstract class - cannot be used to create objects (can only access through subclass)
	abstract String use();
    abstract String getName();
}

class AttackCapsule extends MiracleItem {
    @Override
    String use() {
        return "You used the Attack Capsule! Attack power boosted!";
    }
    
    String getName() {
        return "Attack Capsule";
    }
}

class DefenseCapsule extends MiracleItem {
    @Override
    String use() {
        return "You used the Defense Capsule! Defense power boosted!";
    }

    String getName() {
        return "Defense Capsule";
    }
}

class PokeBallPower extends MiracleItem {
    @Override
    String use() {
        return "You used the PokeBall Power! PokeBall catch rate increased!";
    }

    String getName() {
        return "PokeBall Power";
    }
}
