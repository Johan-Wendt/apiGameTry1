package rest;

public abstract class VisibleObject {
	private byte xPos;
	private byte yPos;
	private byte objectNumber;
	private GamePlan gamePlan;

	public VisibleObject(GamePlan gamePlan) {
		this.gamePlan = gamePlan;

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

	public void setObjectNumber(byte objectNumber) {
		this.objectNumber = objectNumber;
	}

	public GamePlan getGamePlan() {
		return gamePlan;
	}

	public void setGamePlan(GamePlan gamePlan) {
		this.gamePlan = gamePlan;
	}
}
