package rest;

public class Bullet extends Projectile {
	
	public Bullet(byte xPos, byte yPos, byte speed) {
		super(xPos, yPos, speed);
		super.setObjectTypeNumber(Constants.BULLET);
	}

	@Override
	public boolean turnIsAllowed(byte direction) {
		// TODO if homing make turn allowed???
		return false;
	}

	@Override
	public void checkObjectSpecificActions(MasterController masterController) {
		// TODO  
		
	}

	@Override
	public void handleCrash(byte[] crashInfo) {
		// TODO Set a value that tells the master it has hit someone (if player) 
		// Controller checks for a positive answer ever turn (bulletController??)
		
	}

}
