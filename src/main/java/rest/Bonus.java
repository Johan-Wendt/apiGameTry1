package rest;

public abstract class Bonus extends ImmovableObject {
	private Bonuses bonus;
	

	public Bonus(byte xPos, byte yPos) {
		super(xPos, yPos);
		super.setObjectType(Constants.BONUS);
	}


	@Override
	public void handleCrashedInto(MovingObject crasher) {
		super.setToBeRemoved();

	}
	
	/*
	 * public class SpeedBonus extends Bonus {

	public SpeedBonus(byte xPos, byte yPos) {
		super(xPos, yPos);
		super.setObjectType(Constants.BONUS);
		super.setObjectSubType(Constants.SPEED_BONUS);
	}

}
	 */


}
