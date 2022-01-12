
public class GameManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//start of the game
		int roundCount = 1;
		System.out.println("Welcome in WEREWOLF!");
		WerewolfGame game = new WerewolfGame();
		game.start();
		while(!game.gameOver()){
			//night round 
			System.out.println("--------------------------------------------");
			System.out.println("Round "+roundCount);
			System.out.println("The night is falling\n");
			game.nightRound();
			//day round
			System.out.println("--------------------------------------------");
			System.out.println("The sun is rising!\n");
			System.out.println(game.getReport());
			System.out.println("It's time to vote\n");
			game.dayRound();
			roundCount++;
		}
		System.out.println("--------------------------------------------");
		System.out.println("GAME OVER!");
		System.out.println(game.gameResult());
	}

}
