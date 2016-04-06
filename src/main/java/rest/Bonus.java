package rest;

public abstract class Bonus extends ImmovableObject{
	private byte bonusNumber;
	public Bonus(byte xPos, byte yPos) {
		super(xPos, yPos);
	}
	public byte getBonusNumber() {
		return bonusNumber;
	}
	public void setBonusNumber(byte bonusNumber) {
		this.bonusNumber = bonusNumber;
	}
	

	

}
