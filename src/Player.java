
public abstract class Player {
	String name;
	String role;
	int canVote;//by default its one
	boolean mayor;
	boolean dead;
	
	abstract boolean win(Player[] players);
	
	//By default a player must write a name in the players array. If the name is correct 
	// this player canVote decrements by one
	public String vote(Player[] players){
		return null;
	}
	
	//Action a player takes, this depends on the players role
	abstract String action();

}
