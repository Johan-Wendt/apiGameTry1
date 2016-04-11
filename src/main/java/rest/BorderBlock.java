package rest;

public class BorderBlock extends GameBoardBlock{

	public BorderBlock(byte xPos, byte yPos) {
		super(xPos, yPos);
		super.setObjectSubType(Constants.OUT_OF_BORDERS);
	}

	@Override
	public void handleCrashedInto(MovingObject crasher) {
		// Say bang??
	}

}
