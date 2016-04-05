package rest;

public class MasterController implements Constants{
	private GamePlan gamePlan;
	private BonusController bonusController;
	private SnakeController snakeController;
	
	public MasterController(GamePlan gamePlan, BonusController bonusController, SnakeController snakeController) {
		this.gamePlan = gamePlan;
		this.bonusController = bonusController;
		this.snakeController = snakeController;
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
		byte[] play = snakeController.checkPlayerCrash(xPos, yPos);
		if(play[0] != -1) {
			return play;
 		}
		byte[] noDice = {-1};
		return noDice;
	}
	public void gameRound() {
		snakeController.playerRound();
		bonusController.bonusRound();
	}
	

}
