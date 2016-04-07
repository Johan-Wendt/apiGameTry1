package rest;

public abstract class Snake extends MovingObject {
	private Players player;

	public Snake(byte playerNumber) {
		super();
		Tail tailsTail = new Tail();
		super.setTail(new Tail(tailsTail));
		setPlayer(playerNumber);
		restart();
		super.setObjectTypeNumber(Constants.PLAYER);
		super.setObjectNumber(playerNumber);

	}

	public void handleCrash(byte[] crashInfo) {
		if (crashInfo.length > 1) {
			byte category = crashInfo[0];
			byte happening = crashInfo[0];

			switch (category) {
			case Constants.BOUNDARIES:
				restart();
				break;
			case Constants.PLAYER:
				restart();
				break;
			case Constants.BONUS:
				super.makeLonger();
				super.setMayTurn(false);
				break;
			}

		}
	}

	private void restart() {
		super.getTail().hideTails();
		super.setxPos(player.getStartingPositionX());
		super.setyPos(player.getStartingPositionY());
		super.setCurrentDirection(player.getStartingDirection());
	}

	private void setPlayer(byte playerNumber) {
		switch (playerNumber) {
		case Constants.PLAYER_ONE:
			player = Players.PlayerOne;
			break;
		case Constants.PLAYER_TWO:
			player = Players.PlayerTwo;
			break;
		case Constants.PLAYER_THREE:
			player = Players.PlayerThree;
			break;
		case Constants.PLAYER_FOUR:
			player = Players.PlayerFour;
			break;
		}
	}

}
