package rest;

public class SpeedBonus extends Bonus implements Constants{

	public SpeedBonus(GamePlan gamePlan, byte xPos, byte yPos) {
		super(xPos, yPos);
		super.setBonusNumber(SPEED_BONUS);
	}

}
