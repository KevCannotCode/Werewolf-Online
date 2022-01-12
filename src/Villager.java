import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Villager extends Player{

	public Villager(String name) {
		this.name = name;
		this.votesLeft = 1;
		this.mayor = false;
		this.ROLE = Roles.VILLAGER.toString();
		this.dead = false;
	}
	//Villagers win when all evil players are out of the game
	@Override
	boolean win(Map<String, Integer> roleCount) {
		Iterator<Map.Entry<String,Integer>> iterator = roleCount.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry<String, Integer> next = iterator.next();
			String role = next.getKey();
			int count = next.getValue();
			
			if(Roles.isEvil(role) && count > 0)
				return false;
		}

		return true;
	}

	//Villagers are useless so...
	@Override
	public String nightAction(ArrayList<Player> p) {
		return "";
	}
	@Override
	boolean isGroupNightAction() {
		return false;
	}

}
