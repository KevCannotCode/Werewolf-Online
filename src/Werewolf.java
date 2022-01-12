import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Werewolf extends Player{

	public Werewolf(String name) {
		this.name = name;
		this.votesLeft = 1;
		this.mayor = false;
		this.ROLE = Roles.WEREWOLF.toString();
		this.dead = false;
	}

	//Werewolves win when they out-number the other players
	@Override
	boolean win(Map<String, Integer> roleCount) {
		Iterator<Map.Entry<String,Integer>> iterator = roleCount.entrySet().iterator();
		int numberOfWerewolves = roleCount.get(Roles.WEREWOLF.toString());
		
		while(iterator.hasNext()) {
			Map.Entry<String, Integer> next = iterator.next();
			String role = next.getKey();
			int count = next.getValue();
			
			if((!role.equals(Roles.WEREWOLF.toString()) ) && count > numberOfWerewolves) 
				return false;
		}

		return true;
	}


	@Override
	public String nightAction(ArrayList<Player> playerList){
		HashMap<String, Integer> victim = new HashMap<>();
		String killReport = "The werewolves killed ...";
		String victimName = "";
		do{
			victim = new HashMap<>();
			for(Player player: playerList) {
				if(player.getRole().equals(this.ROLE)) {
					//cast it to player to use the vote to select the victim
					victimName = player.vote(playerList);
					if (!victim.containsKey(victimName))
						victim.put(victimName, 1);
					else 
						victim.put(victimName, victim.get(victimName)+1);
				}
			}

			if(victim.size()!= 1)
				System.out.println("Please pick select the same victim");

		}while(victim.size() != 1);

		for(Player p: playerList)
			if(p.name.equals(victimName)) {
				killReport += p.name+" | ";
				p.dead = true;
			}
		return killReport;
	}

	public boolean isGroupNightAction() {
		return true;
	}

}
