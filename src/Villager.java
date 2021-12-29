import java.util.HashMap;
import java.util.Map;

public class Villager extends Player{

	public Villager(String name) {
		this.name = name;
		this.canVote = 1;
		this.mayor = false;
		this.ROLE = Roles.VILLAGER.toString();
		this.dead = false;
	}
	//If there the array doesn't contains a Werewolf
	@Override
	boolean win(Map<String, Integer> players) {
		return (players.get(Roles.WEREWOLF.toString()) == 0) ? true : false;
	}

	//Villagers are useless so...
	@Override
	String action() {
		return "My life is useless";
	}

}
