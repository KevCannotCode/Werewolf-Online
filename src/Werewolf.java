import java.io.Serializable;
import java.util.Map;

public class Werewolf extends Player implements Serializable{
	
	public Werewolf(String name) {
		this.name = name;
		this.canVote = 1;
		this.mayor = false;
		this.ROLE = Roles.WEREWOLF.toString();
		this.dead = false;
	}
	
	//If the array only contains a Werewolf then the player wins
	@Override
	boolean win(Map<String, Integer> players) {
		return (players.get(Roles.WEREWOLF.toString()) > 0) ? true : false;
	}

	//Villagers are useless so...
	@Override
	String action() {
		return "GRRRRRR miam miam!";
	}
}
