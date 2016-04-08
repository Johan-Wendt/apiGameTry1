package rest;

public class PlayerAI extends Snake {

	public PlayerAI(MasterController masterController, byte playerNumber) {
		super(playerNumber);
	}

	@Override
	public boolean turnIsAllowed(byte direction) {
		return true;
	}

}
