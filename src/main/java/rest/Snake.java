package rest;

import com.google.common.primitives.Bytes;

public abstract class Snake extends MovingObject {
	private Players player;
	private boolean shot = false;
	private Weapons weapon;

	public Snake(byte playerNumber) {
		super();
		//Tail tailsTail = new Tail();
		//super.setTail(new Tail(tailsTail));
		setPlayer(playerNumber);
		restart();
		super.setObjectSubType(playerNumber);
		super.setObjectType(playerNumber);
		super.setSpeed(player.getStartingSpeed());
		super.setLength(player.getStartingLength());
		restart();

	}

	public void handleCrashingInto(byte[] crashInfo) {
		if (crashInfo.length > 1) {
			byte category = crashInfo[0];
			byte happening = crashInfo[0];

			switch (category) {
			case Constants.BOUNDARIES:
				deathPenalty();
				restart();
				break;
			case Constants.PLAYER:
				deathPenalty();
				restart();
				break;
			case Constants.BONUS:
				super.makeLonger();
				super.setMayTurn(false);
				break;
			}

		}
	}
	public void handleCrashedInto(MovingObject crasher) {
		
	}

	private void restart() {
		weapon = Weapons.PISTOL;
		if(super.getTail() != null) {
			super.getTail().hideTails();
		}
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
	private void deathPenalty() {
		super.setLength((byte) (super.getLength() - 2));
	}

	public boolean isShot() {
		return shot;
	}

	public void setShot(boolean shot) {
		this.shot = shot;
	}
	public void checkObjectSpecificActions(MasterController masterController) {

		if(shot) {
			masterController.shoot(weapon);

			
		}
    }

}
