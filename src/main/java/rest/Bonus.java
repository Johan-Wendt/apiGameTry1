package rest;

public abstract class Bonus extends ActingObject {
	private Bonuses bonus;
	

	public Bonus(byte xPos, byte yPos) {
		super(xPos, yPos);
		super.setObjectType(Constants.BONUS);
	}
	@Override
	public void act(MasterController masterController) {
		
	}


	@Override
	public void handleCrashedInto(MovingObject crasher) {
		super.setToBeRemoved();

	}

}
