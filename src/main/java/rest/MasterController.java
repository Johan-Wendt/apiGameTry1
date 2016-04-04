package rest;

public class MasterController implements Constants{
	private GamePlan gamePlan;
	private BonusController bonusController;
	
	public MasterController(GamePlan gamePlan, BonusController bonusController) {
		this.gamePlan = gamePlan;
		this.bonusController = bonusController;
	}
	
	public byte[] craschCheck(byte xPos,byte yPos) {
		byte[] plan = gamePlan.isInBoundaries(xPos, yPos);
		if(plan[0] != -1) {
			return plan;
 		}
		byte[] bonus = bonusController.getBonus(xPos, yPos);
		if(bonus[0] != -1) {
			return bonus;
 		}
		byte[] noDice = {-1};
		return noDice;
	}
	

}
