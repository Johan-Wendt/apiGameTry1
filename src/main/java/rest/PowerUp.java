package rest;

public class PowerUp extends Bonus{
	private Bonuses bonus;
	

	public PowerUp(byte xPos, byte yPos, Bonuses bonus) {
		super(xPos, yPos);
		this.bonus = bonus; 
		super.setObjectSubType(bonus.getObjectSubType());
	}


}
