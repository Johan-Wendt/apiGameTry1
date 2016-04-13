package rest;

public class PlayerAI extends Snake {

	public PlayerAI(byte playerNumber) {
		super(playerNumber);
	}

	@Override
	public boolean turnIsAllowed(byte direction) {
		return true;
	}
	public void checkObjectSpecificActions(MasterController masterController) {
    }

}
