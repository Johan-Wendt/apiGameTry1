package rest;

public class Player implements Constants {
	private byte xPos;
	private byte yPos;
	private int slowness = 20;
	private int slowCounter = 0;
	private int currentDirection = 2;

	public Player() {
		xPos = 20;
		yPos = 30;
	}

	public void move() {
		if (slowCounter % slowness == 0) {
			switch (currentDirection) {
			case moveUp:
				yPos--;
				break;
			case moveRight:
				xPos++;
				break;
			case moveDown:
				yPos++;
				break;
			case moveLeft:
				xPos--;
				break;
			}
		}
		slowCounter++;
	}

	public byte getxPos() {
		return xPos;
	}

	public void setxPos(byte xPos) {
		this.xPos = xPos;
	}

	public byte getyPos() {
		return yPos;
	}

	public void setyPos(byte yPos) {
		this.yPos = yPos;
	}

	public int getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(int currentDirection) {
		this.currentDirection = currentDirection;
	}

}
