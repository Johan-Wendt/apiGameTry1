package rest;

import java.util.ArrayList;

public class BonusController extends Controller implements Constants {
	private double chance = 0.01;
	private ArrayList<Bonus> bonuses = new ArrayList<>();
	private GamePlan gamePlan;

	public BonusController(GamePlan gamePlan) {
		this.gamePlan = gamePlan;
	}

	public void BonusRound() {
		if (Math.random() < chance) {
			byte xPos = (byte) (Math.random() * GamePlan.GAME_WIDTH);
			byte yPos = (byte) (Math.random() * GamePlan.GAME_HEIGHT);
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
			result = new byte[0];
		}
		return result;
	}
	public byte[] getBonus(byte x, byte y) {
		byte[] result = new byte[2];
		for(Bonus bonus: bonuses) {
			if(bonus.isInPosition(x, y)) {
				result[0] = BONUS;
				result[1] = SPEED_BONUS;
				return result;
			}
		}
		result[0] = -1;
		return result;
	}

}
