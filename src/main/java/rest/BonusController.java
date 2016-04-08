package rest;

import java.util.ArrayList;
import java.util.Iterator;

public class BonusController extends Controller implements Constants {
	private double chance = 0.01;
	private ArrayList<Bonus> bonuses = new ArrayList<>();
	private GamePlan gamePlan;

	public BonusController(GamePlan gamePlan) {
		this.gamePlan = gamePlan;
	}

	public void bonusRound() {
		if (Math.random() < chance) {
			byte xPos = (byte) (Math.random() * (GamePlan.GAME_WIDTH - 2) + 1);
			byte yPos = (byte) (Math.random() * (GamePlan.GAME_HEIGHT - 2) + 1);
			bonuses.add(new SpeedBonus(gamePlan, xPos, yPos));
		}
	}

	@Override
	public byte[] getAllPositions() {
		byte[] result;
		int nrBonuses = bonuses.size();
		if (nrBonuses > 0) {
			result = new byte[bonuses.size() * 3 + 1];
			result[0] = BONUS;
			int n = 1;
			for (Bonus bonus : bonuses) {
				result[n] = bonus.getBonusNumber();
				result[n + 1] = bonus.getxPos();
				result[n + 2] = bonus.getyPos();
				n += 3;
			}
		} else {
			result = new byte[1];
			result[0] = -1;
		}
		return result;
	}
	public byte[] getBonusCheck(byte x, byte y) {
		byte[] result = new byte[2];
		Iterator<Bonus> itr = bonuses.iterator();
		while(itr.hasNext()) {
			Bonus bonus = itr.next();
			if(bonus.isInPosition(x, y)) {
				result[0] = BONUS;
				result[1] = SPEED_BONUS;
				itr.remove();
				return result;
			}
		}

		result[0] = -1;
		return result;
	}

}
