package rest;

public class Bullet extends MovingObject{
	
	public Bullet(byte xPos, byte yPos) {
		super(xPos, yPos);
	}

	@Override
	public boolean turnIsAllowed(byte direction) {
		// TODO if homing make turn allowed???
		return false;
	}

	@Override
	public void checkObjectSpecificActions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleCrash(byte[] crashInfo) {
		// TODO Auto-generated method stub
		
	}

}
