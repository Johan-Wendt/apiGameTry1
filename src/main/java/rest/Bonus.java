package rest;

public class Bonus extends ActingObject {
	private Bonuses bonus;
	

	public Bonus(byte xPos, byte yPos, Bonuses bonus) {
		super(xPos, yPos);
		this.bonus = bonus;
		super.setObjectType(Constants.BONUS);
		super.setObjectSubType(bonus.getObjectSubType());
	}
	@Override
	public void act(MasterController masterController) {
		
	}


	@Override
	public void handleCrashedInto(MovingObject crasher) {
		super.setToBeRemoved();

	}
	public Bonuses getBonus() {
		return bonus;
	}
	public void setBonus(Bonuses bonus) {
		this.bonus = bonus;
	}

}
