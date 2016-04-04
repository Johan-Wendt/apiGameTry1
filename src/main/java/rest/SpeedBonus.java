package rest;

public class SpeedBonus extends Bonus implements Constants{

	public SpeedBonus(GamePlan gamePlan, byte xPos, byte yPos) {
		super(gamePlan, xPos, yPos);
		super.setBonusNumber(SPEED_BONUS);
	}

}
