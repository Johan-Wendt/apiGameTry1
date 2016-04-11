package rest;

public class GameBoardController extends VisibleController{
	private boolean firstBatchSent = false;
	public GameBoardController() {
		super();
		super.setTypesControlled(Constants.GAME_BOARD);
		loadGameStartBoundaries();
		super.setSendChangesOnly(true);
	}
	public void addBorderBlock(byte xPos, byte yPos) {
		super.getControlledObjects().add(new BorderBlock(xPos, yPos));
	}


	public void loadGameStartBoundaries() {
		int n = 0;
		byte xPos = 0;
		byte yPos = 0;
	//	System.out.println("first");
		while(xPos <= Constants.GAME_WIDTH) {
			addBorderBlock(xPos, yPos);
		//	System.out.println("x = " + xPos);
		//	System.out.println("y = " + yPos);
			xPos ++;
			n++;
		}
		xPos = 0;
		yPos = Constants.GAME_HEIGHT;
	//	System.out.println("second");
		while(xPos <= Constants.GAME_WIDTH) {
			addBorderBlock(xPos, yPos);
	//		System.out.println("x = " + xPos);
	//		System.out.println("y = " + yPos);
			xPos ++;
			n++;
		}
		xPos = 0;
		yPos = 1;
	//	System.out.println("third");
		while(yPos <= Constants.GAME_HEIGHT) {
			addBorderBlock(xPos, yPos);
		//	System.out.println("x = " + xPos);
		//  System.out.println("y = " + yPos);
			yPos ++;
			n++;
		}
		xPos = Constants.GAME_WIDTH;
		yPos = 1;
	//	System.out.println("fourth");
		while(yPos <= Constants.GAME_HEIGHT - 1) {
			addBorderBlock(xPos, yPos);
	//		System.out.println("x = " + xPos);
		//	System.out.println("y = " + yPos);
			yPos ++;
			n++;
		}
	}

}
