package rest;

public class Player extends Snake {
	private byte overlappedTurn = 0;

	public Player(byte playerNumber) {
		super(playerNumber);
		
	}
	
	public boolean turnIsAllowed(byte direction) {
		byte currentDirection = super.getMovingDirection();
		if ((currentDirection == Constants.MOVE_UP && direction == Constants.MOVE_DOWN)
				|| (currentDirection == Constants.MOVE_DOWN && direction == Constants.MOVE_UP)
				|| (currentDirection == Constants.MOVE_RIGHT && direction == Constants.MOVE_LEFT)
				|| (currentDirection == Constants.MOVE_LEFT && direction == Constants.MOVE_RIGHT)
				|| (!super.isMayTurn())) {
			return false;
		}
		return true;
	}
    public void turn(byte direction) {
    	super.turn(direction);
    	if(super.isMayTurn() == false) {
    		overlappedTurn = direction;
    	}
    	
	}
    public void checkObjectSpecificActions(MasterController masterController) {
    	checkOverlappedTurn();
    }
    public void checkOverlappedTurn() {
		if(overlappedTurn != 0 && turnIsAllowed((byte) overlappedTurn)) {
			super.setCurrentDirection(overlappedTurn);
			overlappedTurn = 0;
		}
	}
}