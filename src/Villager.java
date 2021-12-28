
public class Villager extends Player{

	//If there the array doesn't contains a Werewolf
	@Override
	boolean win(Player[] players) {
		for(int i = 0; i < players.length; i++)
			if(!players[i].dead && players[i].role.equals("Werewolf") )
				return false;
		return true;
	}

	//Villagers are useless so...
	@Override
	String action() {
		return "My life is useless";
	}

}
