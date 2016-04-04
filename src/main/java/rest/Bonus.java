package rest;

public abstract class Bonus extends ImmovableObject{
	private byte bonusNumber;
	public Bonus(GamePlan gamePlan, byte xPos, byte yPos) {
		super(gamePlan, xPos, yPos);
	}
	public byte getBonusNumber() {
		return bonusNumber;
	}
	public void setBonusNumber(byte bonusNumber) {
		this.bonusNumber = bonusNumber;
	}
	

	

}
