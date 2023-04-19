package utility;

import java.security.InvalidAlgorithmParameterException;
import java.util.Random;

import action.BaseAction;
import action.FindMageMaster;
import action.FindSwordMaster;
import action.IsRobbed;
import action.WinLottery;
import customExecption.InvalidValueExecption;
import monster.BaseMonster;
import player.BasePlayer;

public class Utility {
	/**
	 * Return true if player win against monster, otherwise false
	 * @param p1 player
	 * @param m1 monster
	 * @return
	 */
	public static boolean fightAgainst(BasePlayer p1, BaseMonster m1) {
//		TODO: implements this function
		return true;
	}
	
	/**
	 * Random number in range [min, max] (both inclusive)
	 * @param min
	 * @param max
	 * @return a number in range [min, max] 
	 */
	public static int randomInteger(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}
	
	public static BaseAction genRandomAction(BasePlayer p1) {
//		TODO: add action for monster and boss
		int rand = randomInteger(0, 3);
		BaseAction randAction = new WinLottery(p1);
		try {
			if (rand == 0) {
				randAction = new FindMageMaster(p1);
			} else if (rand == 1) {
				randAction = new FindSwordMaster(p1);
			} else if (rand == 2) {
				randAction = new IsRobbed(p1);
			} else if (rand == 3) {
				randAction = new WinLottery(p1);
			} else {
				throw new InvalidValueExecption(
						String.format("Invalid random Action number: %d", rand));
			}			
		} catch (InvalidValueExecption err) {
			System.out.println(err);
			System.out.println(err.getMessage());
		}
		return randAction;
	}
}
