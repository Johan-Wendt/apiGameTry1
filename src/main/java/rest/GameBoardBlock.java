package rest;

public abstract class GameBoardBlock extends VisibleObject {
	
	public GameBoardBlock(byte xPos, byte yPos) {
		super(xPos, yPos);
		super.setObjectType(Constants.GAME_BOARD);
		
	}

}
