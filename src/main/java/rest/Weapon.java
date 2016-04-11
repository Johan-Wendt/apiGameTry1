package rest;

public class Weapon extends Bonus{
	private Weapons weapon;

	public Weapon(byte xPos, byte yPos, Weapons weapon) {
		super(xPos, yPos);
		super.setObjectType(Constants.WEPAPON);
		super.setObjectType(weapon.getObjectSubType());
	}
	

}
