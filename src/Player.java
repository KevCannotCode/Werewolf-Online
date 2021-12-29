import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public abstract class Player {
	String name;
	String ROLE;
	int canVote;//by default its one
	boolean mayor;
	boolean dead;

	abstract boolean win(Map<String, Integer> players);

	//By default a player must write a name in the players array. If the name is correct 
	// this player canVote decrements by one
	public String vote(ArrayList<Player> players){

		String vote = "";
		
		Scanner keyboardInput = new Scanner(System.in);
		do {
			System.out.print("\nPlease vote a player : ");
			vote = keyboardInput.nextLine();
			keyboardInput = new Scanner(System.in);
		}while(!players.contains());

		return vote;
	}

	//Action a player takes, this depends on the players role
	abstract String action();


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Name = "+this.name+" | Role = "+ this.ROLE +
				" | Canvote = "+ this.canVote+" | Mayor = "+ this.mayor+
				" | Dead = "+this.dead+"\n";
	}
}
