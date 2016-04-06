package rest;

public abstract class VisibleObject {
	private byte xPos;
	private byte yPos;
	private byte objectNumber;
	private byte objectTypeNumber;

	public VisibleObject() {
	}

	public VisibleObject(byte xPos, byte yPos) {
		this.xPos = xPos;
		this.yPos = yPos;

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

	public byte getObjectNumber() {
		return objectNumber;
	}

	public void setObjectNumber(byte number) {
		this.objectNumber = number;
	}


	public byte[] getPosition() {
		byte[] result = {xPos, yPos};
		return result;
	}

	public byte getObjectTypeNumber() {
		return objectTypeNumber;
	}

	public void setObjectTypeNumber(byte type) {
		this.objectTypeNumber = type;
	}
	public boolean isInPosition(byte x, byte y) {

		return ((x == xPos) && (y == yPos));
	}

}
