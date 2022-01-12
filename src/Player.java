import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public abstract class Player {
	String name;
	protected String ROLE;
	int votesLeft;//by default its one
	int votesAgainstMe;//number of votes against this player
	boolean mayor;
	boolean dead;

	
	abstract boolean win(Map<String, Integer> players);

	//By default a player must write a name in the players array. If the name is correct 
	// this player canVote decrements by one
	public String vote(ArrayList<Player> players){
		boolean found = false;
		String vote = "";
		
		Scanner keyboardInput = new Scanner(System.in);
		do {
			System.out.print("\nPlease vote a player : ");
			vote = keyboardInput.nextLine();
			keyboardInput = new Scanner(System.in);
			
			for(Player p : players)
				found = (p.name.equals(vote) || found == true) ? true : false;
			
		}while(!found);
		
		return vote;
	}

	//Action a player takes, this depends on the players role
//	abstract String action();


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Name = "+this.name+" | Role = "+ this.ROLE +
				" | votesLeft = "+ this.votesLeft+" | Mayor = "+ this.mayor+
				"| votesAganstMe = "+ this.votesAgainstMe+" | Dead = "+this.dead+"\n";
	}
	
	//reset the number of times this player was voted
	public void resetVote() {
		this.votesAgainstMe = 0;
	}
	
	public void incrementVote() {
		this.votesAgainstMe++;
	}
	
	public void killMe() {
		this.dead = true;
	}
	
	public void upgradeToMayor() {
		this.mayor = true;
	}
	
	abstract String nightAction(ArrayList<Player> playerList);
	
	abstract boolean isGroupNightAction();
	public String getRole() {
		return this.ROLE.toString();
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isDead() {
		return this.dead;
	}
}
