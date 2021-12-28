
public class Werewolf extends Player {
	//If the array only contains a Werewolf then the player wins
	@Override
	boolean win(Player[] players) {
		for(int i = 0; i < players.length; i++)
			if(!players[i].dead && !players[i].role.equals("Werewolf") )
				return false;
		return true;
	}

	//Villagers are useless so...
	@Override
	String action() {
		return "GRRRRRR miam miam";
	}
}
