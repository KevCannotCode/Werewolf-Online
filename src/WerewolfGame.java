import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class WerewolfGame {
	ArrayList <Player> players;
	Map<String,Integer> count;
	private boolean win;
	int voted;//The index of the player that got voted
	String actions;//A string that stores all actions players took

	public WerewolfGame() {
		super();
		this.players = new ArrayList<>();
		this.count = new HashMap<>();
	}
	public void start() {
		int numberOfPlayers = selectPlayerNumber();
		selectRoles(numberOfPlayers);
		addPlayers(numberOfPlayers);
	}

	//helpers

	//Get the number of players
	private int selectPlayerNumber() {
		int playerNum = -1;
		//Scanner keyboardInput = null;
		do {
			try {
				Scanner keyboardInput = new Scanner(System.in);
				System.out.print("How many players do you want: ");
				playerNum = keyboardInput.nextInt();
				//keyboardInput.reset();

			}catch(InputMismatchException e) {
				System.out.println("Please enter a number greater than zero");
				playerNum = -1;
			}

		}while(playerNum <= 0);

		//keyboardInput.close();
		return playerNum;
	}

	//Get initiate the count map with the appropriate roles and number of roles
	//Loop through the roles and add it to the count map if the number 
	//entered by the user is more than one
	private void selectRoles(int maxNumOfPlayers) {
		int num = -1;
		//	Scanner keyboardInput = new Scanner(System.in);
		while(true) {
			for(Roles r : Roles.values()) {				
				do {
					try {
						Scanner keyboardInput = new Scanner(System.in);
						System.out.println("How many "+r.toString()+" do you want? : ");
						num = keyboardInput.nextInt();

					}catch(InputMismatchException e) {
						System.out.println("Please enter a positive number lower than the maximum number of players");
					}
				}while(!(num > 0 && num <= maxNumOfPlayers) && maxNumOfPlayers > 0);

				//add roles to count
				if(count.containsKey(r.toString()))
					count.put(r.toString(), count.get(r.toString())+1 );
				else	
					count.put(r.toString(), Integer.valueOf(num));

				//add the roles to the players array
				for (int i = 0; i < num; i++) {
					Player p = new Player() {

						@Override
						boolean win(Map<String, Integer> players) {
							// TODO Auto-generated method stub
							return false;
						}

						@Override
						String action() {
							// TODO Auto-generated method stub
							return null;
						}
					};
					p.ROLE = r.toString();
					players.add(p);
				}

				//update the remaining players to assign
				maxNumOfPlayers -= num;

				if(maxNumOfPlayers <= 0) {
					//keyboardInput.close();
					return;
				}
			}
		}
		//keyboardInput.close();
	}

	//Set add players to the array and give them a role
	private void addPlayers(int maxNumber) {
		String playerName = "";
		int counter = 0;
		//Scanner keyboardInput = null;
		while(counter < maxNumber){
			Scanner keyboardInput = new Scanner(System.in);
			System.out.printf("Player %d",counter+1 );
			System.out.print("\nPlease write the name of your player : ");
			playerName = keyboardInput.nextLine();


			boolean playerCreated = false;
			do {
				Random rand = new Random();
				int randIndex = rand.nextInt(maxNumber);
				if(players.get(randIndex).name == null) {
					createPlayer(randIndex,playerName, players.get(randIndex).ROLE);
					playerCreated = true;
				}
			}while(!playerCreated);

			counter++;
		}

		//keyboardInput.close();
		//return playerNum;
	}

	//update this method whenever there is a new ROLE
	private void createPlayer(int index, String name, String role) {
		if(role.equals(Roles.VILLAGER.toString()) ) 
			players.set(index, new Villager(name));

		else if(role.equals(Roles.WEREWOLF.toString()) ) 
			players.set(index, new Werewolf(name));
		else 
			throw new IllegalStateException("Tried to create a new player with an invalid role");
	}

	public boolean win() {
		return win;
	}

	public void nightRound() {

	}

	public void dayRound() {

	}

	public void display() {

	}

	public void message() {

	}

	public void kill() {

	}
}
