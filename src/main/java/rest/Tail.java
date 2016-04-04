package rest;

import java.util.ArrayList;

public class Tail {
	private Tail tail;
	private byte xPos;
	private byte yPos;

	public Tail() {
	}

	public Tail(Tail tail) {
		this.tail = tail;
	}

	public void move(byte xPosNew, byte yPosNew) {
		if(tail != null) {
			tail.move(xPos, yPos);
		}
		xPos = xPosNew;
		yPos = yPosNew;
		
	}
	public byte[] getTailPositions(byte[] loadList,int n) {
		loadList[n] = xPos;
		loadList[n + 1] = yPos;
		n += 2;
		return (tail == null) ? loadList : tail.getTailPositions(loadList, n);
	}
	public int getTailSize(int current) {
		current +=2; 
		return (tail == null) ? current : tail.getTailSize(current);
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

}
