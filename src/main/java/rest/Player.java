package rest;

import java.util.ArrayList;

public class Player implements Constants {
	private byte xPos;
	private byte yPos;
	private int slowness = 20;
	private int slowCounter = 0;
	private int currentDirection = 1;
	private byte playerNumber = 1;
	private Tail tail;

	public Player() {
		xPos = 20;
		yPos = 30;
		Tail tailsTail = new Tail();
		tail = new Tail(tailsTail);
	}

	public void move() {
		if (slowCounter % slowness == 0) {
			tail.move(xPos, yPos);
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

	public byte getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(byte playerNumber) {
		this.playerNumber = playerNumber;
	}
	public byte[] getAllPositions() {
		int size = (tail == null) ? 3 : tail.getTailSize(3);
		byte[] result = new byte[size];
		int n = 0;
		result[n] = playerNumber;
		result[n + 1] = xPos;
		result[n + 2] = yPos;
		n += 3;
		if(tail != null) {
			tail.getTailPositions(result, n);
		}
		return result;
	}

}
