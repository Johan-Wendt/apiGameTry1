package rest;

public abstract class Snake extends MovingObject{
	

	public Snake(MasterController masterController, byte playerNumber) {
		super(masterController);
		
		super.setxPos((byte) 10);
		super.setyPos((byte) 10);
		Tail tailsTail = new Tail();
		super.setTail(new Tail(tailsTail));
		super.setObjectTypeNumber(Constants.PLAYER);
		super.setObjectNumber(playerNumber);

	}
	public void handleCrash(byte[] crashInfo) {
		if (crashInfo.length > 1) {
			byte category = crashInfo[0];
			byte happening = crashInfo[0];

			switch (category) {
			case Constants.GAME_BOARD:
				restart();
				break;
			case Constants.PLAYER:
				restart();
				break;
			case Constants.BONUS:
				super.makeLonger();
				break;
			}

		}
	}

	private void restart() {
		super.setxPos((byte) 10);
		super.setyPos((byte) 10);
	}

}
