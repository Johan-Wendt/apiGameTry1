package rest;


public class Player extends MovingObject implements Constants {

	public Player(MasterController masterController, byte playerNumber) {
		super(masterController);
		super.setxPos((byte) 10);
		super.setyPos((byte) 10);
		Tail tailsTail = new Tail();
		super.setTail(new Tail(tailsTail));
		super.setObjectTypeNumber(PLAYER);
		super.setObjectNumber(playerNumber);
	}

	public void handleCrash(byte[] crashInfo) {
		
		restart();
	}
	private void restart() {
		super.setxPos((byte) 10);
		super.setyPos((byte) 10);
	}
}