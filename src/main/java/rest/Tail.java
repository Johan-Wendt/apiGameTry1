package rest;

import java.util.ArrayList;

public class Tail {
	private Tail tail;
	private byte xPos;
	private byte yPos;
	private byte owner;

	public Tail(byte owner) {
		this.owner = owner;
	}
	
	public Tail(int length, byte owner) {
		this.owner = owner;
		if(length > 1) {
			tail = new Tail(length - 1, this.owner);
		}
	}

	public Tail(Tail tail, byte owner) {
		this.owner = owner;
		this.tail = tail;
	}

	public void move(byte xPosNew, byte yPosNew) {
		if(tail != null) {
			tail.move(xPos, yPos);
		}
		xPos = xPosNew;
		yPos = yPosNew;
		
	}
	public byte[] getTailPositions(byte[] loadList, int n) {
		//System.out.println("Tail list size = " + loadList.length);
		//System.out.println("Tail n = " + n);
		loadList[n] = xPos;
		loadList[n + 1] = yPos;
		n += 2;
		return (tail == null) ? loadList : tail.getTailPositions(loadList, n);
	}
	public byte[] getTailPositionsCrash(byte[] loadList, int n) {
		//System.out.println("Tail list size = " + loadList.length);
		//System.out.println("Tail n = " + n);
		loadList[n] = xPos;
		loadList[n + 1] = yPos;
		n += 2;
		return (tail == null) ? loadList : tail.getTailPositionsCrash(loadList, n);
	}
	public int getTailSize(int current) {
		current ++; 
		
		return (tail == null) ? current : tail.getTailSize(current);
	}
	public void addTail() {
		if(tail == null) {
			tail = new Tail(owner);
		}
		else {
			tail.addTail();
		}
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
	public void hideTails() {
		xPos = -1;
		yPos = -1;
		if(tail != null) {
			tail.hideTails();
		}
	}
	public void setLength(byte length) {
		if(length > 1) {
			if(tail == null) {
				tail = new Tail((length - 1), owner);
			}
			else {
				tail.setLength((byte) (length - 1));
			}
		}
		else {
			removeAllTails();
		}
	}
	public byte getLength(byte lenghtSoFar) {
		byte result = (byte) (lenghtSoFar + 1);
		if(tail != null) {
			return tail.getLength(result);
		}
		return result;
	}
	public void removeAllTails() {
		if(tail != null) {
		    tail.removeAllTails();
		    tail = null;
		}
	}

	public byte[] getLastTailPosition() {
		if(tail == null) {
			byte[] result = {xPos, yPos};
			return result;
		}
		return tail.getLastTailPosition();
	}

}
