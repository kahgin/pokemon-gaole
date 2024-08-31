import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {

	// Initialising all Pokemons in the game
	FireType chimchar = new FireType("Chimchar", 81, 49, 38);
    FireType charmeleon = new FireType("Charmeleon", 100, 72, 53);
    FireType blaziken = new FireType("Blaziken", 137, 122, 73);
	FireType infernape = new FireType("Infernape", 123, 135, 98);
    WaterType piplup = new WaterType("Piplup", 88, 51, 45);
    WaterType poliwhirl = new WaterType("Poliwhirl", 104, 46, 58);
    WaterType blastoise = new WaterType("Blastoise", 133, 86, 101);
    WaterType greninja = new WaterType("Greninja", 158, 120, 86);
    GrassType rowlet = new GrassType("Rowlet", 90, 42, 35);
    GrassType bayleef = new GrassType("Bayleef", 104, 58, 74);
    GrassType torterra = new GrassType("Torterra", 155, 80, 100);
	GrassType venusaur = new GrassType("Venusaur", 159, 109, 89);
	ElectricType shinx = new ElectricType("Shinx", 76, 42, 33);
	ElectricType luxio = new ElectricType("Luxio", 107, 74, 69);
	ElectricType luxray = new ElectricType("Luxray", 136, 103, 74);
	ElectricType pikachu = new ElectricType("Pikachu", 123, 130, 78);
	GroundType sandshrew = new GroundType("Sandshrew", 70, 45, 58);
	GroundType nidorina = new GroundType("Nidorina", 118, 60, 65);
	GroundType sandslash = new GroundType("Sandslash", 143, 90, 100);
	GroundType garchomp = new GroundType("Garchomp", 154, 136, 105);
    Pokemon[] grade1 = {chimchar, piplup, rowlet, shinx, sandshrew};
    Pokemon[] grade2 = {charmeleon, poliwhirl, bayleef, luxio, nidorina};
    Pokemon[] grade3 = {blaziken, blastoise, torterra, luxray, sandslash};
    Pokemon[] grade4 = {infernape, greninja, venusaur, pikachu, garchomp};

	// Initialising all Pokeballs in the game
	PokeBall pokeball = new PokeBall("Poke Ball", 1);
	PokeBall greatball = new PokeBall("Great Ball", 1.5);
	PokeBall ultraball = new PokeBall("Ultra Ball", 2);
	PokeBall masterball = new PokeBall("Master Ball", 100);
	PokeBall[] ballList = {pokeball, greatball, ultraball, masterball};

	// Initialising all players with their list of Pokemons
	Player p1 = new Player(1, new ArrayList<>(Arrays.asList(greninja, pikachu, garchomp)));
	Player p2 = new Player(2, new ArrayList<>(Arrays.asList(chimchar)));
	Player p3 = new Player(3);
	Player p4 = new Player(4, new ArrayList<>(Arrays.asList(rowlet, bayleef, blastoise, pikachu)));
	Player p5 = new Player(5, new ArrayList<>(Arrays.asList(charmeleon, luxray)));
	Player p6 = new Player(6, new ArrayList<>(Arrays.asList(greninja, infernape, sandslash)));
	Player[] playerList = {p1, p2, p3, p4, p5, p6};

	private Player player; // The player playing the game when it is running
    public static ArrayList<Pokemon> pool = new ArrayList<Pokemon>(); // Pokemon pool (The Pokemons that may appear in battle)

    Random rand = new Random();
    Scanner input = new Scanner(System.in);
    Battle battle = new Battle();
	GaoleMedal gaolemedal = new GaoleMedal();
	Database DB = new Database();
	Score score = new Score();

	// To heal all Pokemons again if game is looped
	private void healAllPokemons() {
		for (Pokemon i : grade1) {
			i.fullheal();
		}

		for (Pokemon i : grade2) {
			i.fullheal();
		}

		for (Pokemon i : grade3) {
			i.fullheal();
		}

		for (Pokemon i : grade4) {
			i.fullheal();
		}
	}

	// Setting all Pokemons' levels - Grade1 = Level 30 ; Grade2 = Level 40 ; Grade3 = Level 50 ; Grade4 = Level 60
	private void setPokemonLevel() {
		for (Pokemon i : grade1) {
			i.setLevel(30);
		}

		for (Pokemon i : grade2) {
			i.setLevel(40);
		}

		for (Pokemon i : grade3) {
			i.setLevel(50);
		}

		for (Pokemon i : grade4) {
			i.setLevel(60);
		}
	}

	// Display 3 random Pokemons from Grade 1-3 each, let user choose 1, add to their pokemonlist and determine the Pokemon pool
	// If Grade1 Pokemon is chosen -> Pool = Grade1 + Grade2
	// If Grade2 Pokemon is chosen -> Pool = Grade1 + Grade2 + Grade3
	// If Grade3 Pokemon is chosen -> Pool = Grade2 + Grade3 + Grade4
	private void setPokemonPool() {
        int i1 = rand.nextInt(grade1.length);
        int i2 = rand.nextInt(grade2.length);
        int i3 = rand.nextInt(grade3.length);
        System.out.printf("[1] %s\n", grade1[i1].getName());
        System.out.printf("[2] %s\n", grade2[i2].getName());
        System.out.printf("[3] %s\n", grade3[i3].getName());

		while (true) {
			System.out.print("Choose a Pokémon!: ");
			int choice = input.nextInt();
			if (choice == 1) {
				System.out.printf("You have chosen %s!\n", grade1[i1].getName());
				player.getPokemonsOwned().add(grade1[i1]);
				for (Pokemon i : grade1) {
					pool.add(i);
				}

				for (Pokemon i : grade2) {
					pool.add(i);
				}
				break;
			}

			else if (choice == 2) {
				System.out.printf("You have chosen %s!\n", grade2[i2].getName());
				player.getPokemonsOwned().add(grade2[i2]);
				for (Pokemon i : grade1) {
					pool.add(i);
				}

				for (Pokemon i : grade2) {
					pool.add(i);
				}

				for (Pokemon i : grade3) {
					pool.add(i);
				}
				break;
			}

			else if (choice == 3) {
				System.out.printf("You have chosen %s!\n", grade3[i3].getName());
				player.getPokemonsOwned().add(grade3[i3]);
				for (Pokemon i : grade2) {
					pool.add(i);
				}

				for (Pokemon i : grade3) {
					pool.add(i);
				}

				for (Pokemon i : grade4) {
					pool.add(i);
				}
				break;
			}

			else {
				System.out.println("Invalid choice!");
			}
		}
    }

	// Generate a random Pokeball and throw at one of the wild pokemons to catch it
	private void catchPokemon() {
		double catchRate;
		Pokemon toAdd = null;
		int level = 30;
		int i = rand.nextInt(ballList.length);
		System.out.printf("A %s is generated!\n", ballList[i]);
		catchRate = ballList[i].getCatchRate();
		System.out.printf("[1] %s\n[2] %s\n", battle.getWild1().getName(), battle.getWild2().getName());

		while (true) {
			System.out.print("Throw on which Pokemon?: ");
			int choice = input.nextInt();
			if (choice == 1) {
				toAdd = battle.getWild1();
				level = battle.getWild1().getLevel();
				break;
			} else if (choice == 2) {
				toAdd = battle.getWild2();
				level = battle.getWild2().getLevel();
				break;
			} else {
				System.out.println("Invalid option!");
			}
		}
		
		switch (level) {
			case 30:
				catchRate *= 60; // Grade1: Pokeball 60%, GreatBall 90%, UltraBall 100%
				break;
			case 40:
				catchRate *= 45; // Grade2: Pokeball 45%, GreatBall 67.5%, UltraBall 90%
				break;
			case 50:
				catchRate *= 30; // Grade3: Pokeball 30%, GreatBall 45%, UltraBall 60%
				break;
			case 60:
				catchRate *= 20; // Grade4: Pokeball 20%, Greatball 30%, UltraBall 40%
				break;
		}

		if (player.getMiracleItem() != null) {
			if (player.getMiracleItem().getName().equals("PokeBall Power")) { // if player has PokeBall Power Miracle Item
			System.out.println(player.getMiracleItem().use());
			catchRate *= 1.2;
			}
		}

		int randomNo = rand.nextInt(1,101);
		if (randomNo <= catchRate) {
			System.out.printf("You have successfully caught %s!\n", toAdd.getName());
			player.getPokemonsOwned().add(toAdd);
			score.updateScoreCatchPokemon(true);
		}

		else {
			System.out.println("Oh no! The Pokemon escaped!");
			gaolemedal.setPokemonRanAway(toAdd);
			score.updateScoreCatchPokemon(false);
		}
	}
	
	// Game flow
	public void startGame() {
		setPokemonLevel();
		DB.loadDB();

		System.out.println("--------------------------------");
		System.out.println("   Welcome to Pokémon Ga-Olé!   ");
		System.out.println("    \"Battle and Catch\" mode    ");
		System.out.println("--------------------------------\n");

		while (player == null) {
			System.out.print("Please type in your playerID: ");
			int id = input.nextInt();
			for (Player i : playerList) {
				if (i.getPlayerID() == id) {
					player = i;
					break;
				}
			}

			if (player == null) {
				System.out.println("PlayerID not found!");
			}
		}
		
		while (true) {
			healAllPokemons(); // reset all pokemon's HP
			pool.clear(); // reset pool
			
			System.out.println("\033[1mCatch Time!\033[0m");
			setPokemonPool();

			if (player.getMiracleItem() != null) {
				if (player.getMiracleItem().getName().equals("Attack Capsule")) { // if player has Attack Capsule Miracle Item
					System.out.println(player.getMiracleItem().use());
					for (Pokemon i : player.getPokemonsOwned()) {
						i.setAtt((int)(i.getAtt()*1.2));
					}
				} else if (player.getMiracleItem().getName().equals("Defense Capsule")) {  // if player has Defense Capsule Miracle Item
					System.out.println(player.getMiracleItem().use());
					for (Pokemon i : player.getPokemonsOwned()) {
						i.setDef((int)(i.getDef()*1.2));
					}
				}
			}
			
			System.out.println("\n\033[1mDepart For Battle!\033[0m");
			battle.startBattle(player.getPokemonsOwned());

			if (battle.getResults().equals("win")) {
				score.updateScoreWonBattle(true);
				System.out.println("\033[1mCatch Pokémon!\033[0m"); // only go to catch pokemon if you won - skip if you lost battle
				catchPokemon();			
			} else if (battle.getResults().equals("lose")) {
				score.updateScoreWonBattle(false);
			}

			System.out.println("\n\033[1mCheck Your Results!\033[0m");
			score.displayScores();
			DB.addScores(player.getPlayerID(), score.getScore());

			System.out.println("\n\033[1mGa-Olé Medals\033[0m");
			gaolemedal.earnGaoleMedal(); // If Pokemon caught - skip ; If Pokemon escaped - Get Gaole Medals
			DB.addGaoleMedals(player.getPlayerID(), gaolemedal.getGaoleMedals()); // add to database
			gaolemedal.setGaoleMedals(0); // reset gaole medals count in GaoleMedal class

			boolean getmiracleitem = DB.checkMiracleItem(player.getPlayerID()); // check if eligible to get miracle item
			if (getmiracleitem) {
				MiracleItem miracleitem = gaolemedal.MiracleItemChance(); // generate random miracle item
				player.setMiracleItem(miracleitem);
				System.out.printf("You received a %s!\n", miracleitem.getName());
			}

			System.out.println("\nPlay again? Enter [0] to exit game and any other keys to continue.");
			String playAgain = input.next();
			if (playAgain.equals("0")) {
				break;
			} else {
				score.setScore(0);
				continue;
			}
		}

		System.out.println("\nYour player details: ");
		DB.displayPlayerInfo(player.getPlayerID());
		System.out.println();
		DB.displayTop5scores();
		DB.storeDB();
	}
}