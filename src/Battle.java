import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Battle {
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private Pokemon wild1;
    private Pokemon wild2;
    private String results;
    Random rand = new Random();
    Scanner input = new Scanner(System.in);

    private ArrayList<Pokemon> pool = Game.pool;

    public Pokemon getWild1() {
        return wild1;
    }

    public Pokemon getWild2() {
        return wild2;
    }

    public String getResults() {
        return results;
    }

    // Battle flow - assign results as "win" or "lose"
    public void startBattle(ArrayList<Pokemon> playerPokemons) {
        displayWildPokemons();
        choosePokemons(playerPokemons);
        while (true) {
            String attacker = battleSequence();
            attack(attacker);

            if (wild1.getHp() == 0 && wild2.getHp() == 0) {
                System.out.println("Both wild pokemons have fainted!");
                System.out.println("You win!\n");
                results = "win";
                break;
            } else if (pokemon1.getHp() == 0 && pokemon2.getHp() == 0) {
                System.out.println("Your pokemons have fainted!");
                System.out.println("You lose!\n");
                results = "lose";
                break;
            }
        }
    }

    // Display 2 random wild Pokemons from pool
    private void displayWildPokemons() {
        int i1 = rand.nextInt(Game.pool.size());
		int i2;
        do {
			i2 = rand.nextInt(pool.size());
		} while (i1 == i2); // to prevent the possibility of getting the same wild Pokemon as the first one
        System.out.printf("A wild %s and %s have appeared!\n", pool.get(i1).getName(), pool.get(i2).getName());
        wild1 = pool.get(i1);
        wild2 = pool.get(i2);
    }

    // Choose 2 Pokemons from player Pokemons list (or rent a Pokemon)
    private void choosePokemons(ArrayList<Pokemon> playerPokemons) {
        while (true) {
            try {
                System.out.println("You currently have these Pokemons!: ");

                for (int i = 0; i < playerPokemons.size(); i++) {
                System.out.printf("[%d] %s\n", i+1, playerPokemons.get(i).getName());
                }

                // If playerPokemons has only 1 Pokemon - Rent a random one from the pool
                if (playerPokemons.size() < 2) {
                    System.out.println("You don't have enough Pokemons! You need to rent a Pokemon!");
                    int rent = rand.nextInt(pool.size());
                    System.out.printf("You have randomly rented a %s!\n", pool.get(rent).getName());
                    System.out.printf("You sent out %s & %s!\n", playerPokemons.get(0).getName(), pool.get(rent).getName());
                    pokemon1 = playerPokemons.get(0);
                    pokemon2 = pool.get(rent);
                }

                // Else choose 2 Pokemons from playerPokemons to send out
                else {
                    System.out.print("Choose your first Pokemon!: ");
                    int choice1 = input.nextInt();
                    System.out.print("Choose your second Pokemon!: ");
                    int choice2 = input.nextInt();
                    System.out.printf("You sent out %s & %s!\n", playerPokemons.get(choice1-1).getName(), playerPokemons.get(choice2-1).getName());
                    pokemon1 = playerPokemons.get(choice1-1);
                    pokemon2 = playerPokemons.get(choice2-1);
                }
            }

            catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid options!");
                continue;
            }

            catch (InputMismatchException e) {
                System.out.println("Please enter numbers only!");
                input.nextLine();
                continue;
            }
            
            break;
        }
        
    }

    // Determine who attacks first
    // 1. Computer generates a hidden number from 1-100
    // 2. Computer generates another random number to represent enemy's input
    // 3. User input a random number
    // 4. Whoever's random number is closer to the hidden number will attack first
    private String battleSequence() {
        System.out.println("\nA hidden number from 1-100 is generated. Whoever's input number is closer to the hidden number will attack!");
        int hiddenNo = rand.nextInt(1, 101);
        System.out.print("Input a random number from 1-100!: ");
        int userNo = input.nextInt();
        String attacker; // to return either "enemy" or "user" for whoever is attacking

        while (true) {
            int enemyNo = rand.nextInt(1, 101);
            int enemyDiff = Math.abs(hiddenNo - enemyNo);
            int userDiff = Math.abs(hiddenNo - userNo);

            if (enemyDiff == userDiff) {
                continue; // reloop & generate a new enemy's number if userDiff & enemyDiff are the same
            }

            System.out.printf("The hidden number was %d!\n", hiddenNo);
            System.out.printf("Your number: %d  Enemy's number: %d\n", userNo, enemyNo);

            if (enemyDiff < userDiff) {
                System.out.println("Enemy's number is closer to the hidden number!");
                System.out.println("Enemy will attack!\n");
                attacker = "enemy";
                break;
            }

            else {
                System.out.println("Your number is closer to the hidden number!");
                System.out.println("You will attack!\n");
                attacker = "user";
                break;
            }
        }
        return attacker;
    }

    // Calculate damage
    private int calculateDamage(Pokemon attacker, Pokemon defender) {
        int att = attacker.getAtt();
        int def = defender.getDef();
        int level = attacker.getLevel();
        double effectiveness = defender.effectiveness(attacker.getType());       

        double damage = ((0.4 * level + 2) * att / def + 2) * effectiveness * rand.nextDouble(0.85, 1.00);
        return (int)damage;
    }

    private void attack(String attacker) {
        if (attacker.equals("user")) {
            attack(pokemon1, pokemon2, wild1, wild2);
        } else if (attacker.equals("enemy")) {
            attack(wild1, wild2, pokemon1, pokemon2);
        }
    }

    // Attacking
    private void attack(Pokemon att1, Pokemon att2, Pokemon def1, Pokemon def2) {
        System.out.printf("%s: %d HP ; %s: %d HP\n", def1.getName(), def1.getHp(), def2.getName(), def2.getHp());
        if (att1.getHp() > 0 && att2.getHp() > 0) {
            System.out.printf("%s & %s attacked!\n", att1.getName(), att2.getName());
            dealAttackDamage(att1, def1);
            dealAttackDamage(att2, def1);
            dealAttackDamage(att1, def2);
            dealAttackDamage(att2, def2);
        } else if (att1.getHp() == 0) {
            System.out.printf("%s attacked!\n", att2.getName());
            dealAttackDamage(att2, def1);
            dealAttackDamage(att2, def2);
        } else if (att2.getHp() == 0) {
            System.out.printf("%s attacked!\n", att1.getName());
            dealAttackDamage(att1, def1);
            dealAttackDamage(att1, def2);
        }
        
        System.out.printf("%s: %d HP ; %s: %d HP\n", def1.getName(), def1.getHp(), def2.getName(), def2.getHp());
    }

    // Deal damage to pokemon - Convert HP to 0 if it goes below 0
    private void dealAttackDamage(Pokemon attacker, Pokemon defender) {
        int damage = calculateDamage(attacker, defender);
        defender.takeDamage(damage);
        if (defender.getHp() <= 0) {
            defender.setHp(0);
        };
    }
}