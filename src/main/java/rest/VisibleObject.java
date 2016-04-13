package rest;

public abstract class VisibleObject {
	private byte xPos;
	private byte yPos;
	private byte objectType;
	private byte objectSubType;
	private boolean toBeRemoved = false;

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

	public byte getObjectType() {
		return objectType;
	}

	public void setObjectType(byte number) {
		this.objectType = number;
	}


	public byte[] getPosition() {
		byte[] result = {xPos, yPos};
		return result;
	}
	public byte[] getAllPositionsCrasch() {
		byte[] result = {xPos, yPos};
		return result;
	}
	public byte[] getAllPositionsSend() {
		byte[] result = {objectSubType, xPos, yPos};
		return result;
	}

	public byte getObjectSubType() {
		return objectSubType;
	}

	public void setObjectSubType(byte type) {
		this.objectSubType = type;
	}
	public boolean isInPosition(byte x, byte y) {

		return ((x == xPos) && (y == yPos));
	}
	public boolean isToBeRemoved() {
		return toBeRemoved;
	}

	public void setToBeRemoved() {
		toBeRemoved = true;
	}
	
	public byte getLength() {
		return 1;
	}

	public abstract void handleCrashedInto(MovingObject crasher);

}
