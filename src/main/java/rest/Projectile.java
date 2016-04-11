package rest;

public abstract class Projectile extends MovingObject {
	private Players owner;
	public Projectile(byte xPos, byte yPos, byte direction, byte speed) {
		super(xPos, yPos, direction, speed);
		super.setObjectType(Constants.PROJECTILES);
	}
	public Projectile(byte xPos, byte yPos, byte direction, byte speed, Players owner) {
		super(xPos, yPos, direction, speed);
		super.setObjectType(Constants.PROJECTILES);
	}
	public Players getOwner() {
		return owner;
	}
	public void setOwner(Players owner) {
		this.owner = owner;
	}

}
