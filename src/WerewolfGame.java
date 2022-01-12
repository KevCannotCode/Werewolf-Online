import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class WerewolfGame {
	ArrayList <Player> playerList;
	Map<String,Integer> roleCount;
	Player playerVoted;//The index of the player that got voted
	String actionReport;//A string that stores all actions players took

	public WerewolfGame() {
		super();
		this.playerList = new ArrayList<>();
		this.roleCount = new HashMap<>();
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
				}while(!(num >= 0 && num <= maxNumOfPlayers) && maxNumOfPlayers > 0);

				//if player picks at least one player then add other wise just go tot he next role
				if(num > 0) {
					//add roles to count
					if(roleCount.containsKey(r.toString()))
						roleCount.put(r.toString(), roleCount.get(r.toString())+1 );
					else	
						roleCount.put(r.toString(), Integer.valueOf(num));

					//add the roles to the players array
					for (int i = 0; i < num; i++) {
						Player p = new Player() {

							@Override
							boolean win(Map<String, Integer> players) {
								// TODO Auto-generated method stub
								return false;
							}

							@Override
							String nightAction(ArrayList<Player> playerList) {
								// TODO Auto-generated method stub
								return null;
							}

							@Override
							boolean isGroupNightAction() {
								// TODO Auto-generated method stub
								return false;
							}
						};
						p.ROLE = r.toString();
						//to make sure the map and the array have the roles in the same order
						playerList.add(0,p);
					}

					//update the remaining players to assign
					maxNumOfPlayers -= num;

					if(maxNumOfPlayers <= 0) {
						//keyboardInput.close();
						return;
					}
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
				if(playerList.get(randIndex).name == null) {
					createPlayer(randIndex,playerName, playerList.get(randIndex).ROLE);
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
			playerList.set(index, new Villager(name));

		else if(role.equals(Roles.WEREWOLF.toString()) ) 
			playerList.set(index, new Werewolf(name));
		else 
			throw new IllegalStateException("Tried to create a new player with an invalid role");
	}

	public boolean gameOver() {
		for(Player p: this.playerList)
			if(p.win(this.roleCount))
				return true;
		return false;
	}

	public void nightRound() {
		int nextPlayerIndex = 0;
		this.actionReport = "Last night here is what happened\n";
		for(String role : roleCount.keySet()) {
			for(int i = 0; i < roleCount.get(role); i++) {
				System.out.println("It's the "+role+"'s turn...");
				nextPlayerIndex = i <= 0 ? nextPlayerIndex : ++nextPlayerIndex;
				
				this.actionReport += playerList.get(nextPlayerIndex).nightAction(playerList);

				if(this.playerList.get(nextPlayerIndex).isGroupNightAction()) {
					//skip the other players with the same role
					nextPlayerIndex += Math.abs(roleCount.get(role)-nextPlayerIndex);//-2 we start counting by zero and the first iteration already happened
					break;
				}
			}


		}
		//end of round apply kill everybody
		for(int i = 0; i < playerList.size(); i++) {
			Player p = playerList.get(i);
			if(p.dead) {
				playerList.remove(p);
				roleCount.put(p.ROLE, roleCount.get(p.ROLE)-1);
				i--;
			}
		}
		//TODO
		//should this really happen?
		resetVotes();
	}


	public void dayRound() {
		Player voted = vote();
		if(voted != null) {
			System.out.println("Villagers killed "+voted.name+". "+voted.name+" was a "+ voted.ROLE);
			//kill the player
			voted.killMe();
			//adjust the role counter and the array of players
			this.playerList.remove(voted);
			this.roleCount.put(voted.ROLE, roleCount.get(voted.ROLE)-1);
		}
		else 
			System.out.println("Nobody was voted");
		resetVotes();
	}

	//asks each players to vote someone. Each player's vote counter is incremented accordingly
	// return null if two or more players have the highest number of vote
	// return  null if all players have the same number of votes
	// otherwise return the player with the most amount of votes 
	public Player vote() {
		for(Player player : playerList) {
			System.out.println(player.name+" is voting...");
			String votedPlayer = player.vote(playerList);
			//increment the count of player who got voted
			playerList.forEach(p -> {
				if( p.name.equals(votedPlayer))
					p.incrementVote();
			});
		}

		//since its sorted we know the first element is the greatest so we can return it
		ArrayList<Player> tempList = new ArrayList<>();
		playerList.forEach( p -> {tempList.add(p);});
		selectionSort(tempList);

		Player mostVoted = tempList.get(0);
		for(int i = 0; i < tempList.size()-1; i++)
			//if there is any person who has the same number of votes then you have a no vote
			if(mostVoted.votesAgainstMe <=  tempList.get(i+1).votesAgainstMe)
				return null;

		return mostVoted;

	}

	private void selectionSort(ArrayList<Player> playerTempList){  
		for (int i = 0; i < playerTempList.size() - 1; i++)  
		{  
			int index = i;  
			for (int j = i + 1; j < playerTempList.size(); j++){  
				if (playerTempList.get(index).votesAgainstMe < playerTempList.get(j).votesAgainstMe){  
					index = j;//searching for biggest index  
				}  
			}  
			Player mostVoted = playerTempList.get(index);   
			playerTempList.set(index, playerTempList.get(i));  
			playerTempList.set(i, mostVoted);  
		}  
	}  

	private void resetVotes() {
		//reset the count of player who got voted
		playerList.forEach(p -> {p.resetVote();});
	}

	public String getReport() {
		return actionReport;
	}

	public String gameResult() {
		String role = "A ";
		String winners = "";
		for(Player p : this.playerList) 
		{
			role += p.win(roleCount) ? p.getRole()+" | " : "";
			winners += p.win(roleCount)? p.name+" | " : "";
		}
		return role +" have won. "+winners+" won the game";
	}
}
