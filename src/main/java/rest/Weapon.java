package rest;

public class Weapon extends Bonus{
	private Weapons weapon;

	public Weapon(byte xPos, byte yPos, Weapons weapon) {
		super(xPos, yPos);
		super.setObjectSubType(weapon.getObjectSubType());
	}
	

}
