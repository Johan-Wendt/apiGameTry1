package rest;

public abstract class Projectile extends MovingObject {
	public Projectile(byte xPos, byte yPos, byte speed) {
		super(xPos, yPos, speed);
		super.setObjectNumber(Constants.PROJECTILES);
	}

}
