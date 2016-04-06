package rest;

public class Player extends Snake {

	public Player(MasterController masterController, byte playerNumber) {
		super(masterController, playerNumber);
	}

	@Override
	public void setObjectType() {
		super.setObjectTypeNumber(ObjectTypes.PLAYER);
		
	}

	@Override
	public void setObjectNumber() {
		// TODO Auto-generated method stub
		
	}
}