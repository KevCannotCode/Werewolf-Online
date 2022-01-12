import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TESTING {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String s = Roles.VILLAGER.toString();
		//System.out.println(s);

		WerewolfGame w = new WerewolfGame();
		w.start();
		System.out.println(w.roleCount.toString());
		System.out.println(w.playerList.toString());

		w.nightRound();
		System.out.println(w.roleCount.toString());
		System.out.println(w.playerList.toString());

	}

}
