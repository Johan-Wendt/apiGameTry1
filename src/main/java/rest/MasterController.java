package rest;

import com.google.common.primitives.Bytes;

public class MasterController implements Constants{
	private GamePlan gamePlan;
	private BonusController bonusController;
	private SnakeController snakeController;
	private GameLoop gameLoop;
	
	public MasterController(GamePlan gamePlan, BonusController bonusController, GameLoop gameLoop) {
		this.gamePlan = gamePlan;
		this.bonusController = bonusController;
		this.gameLoop = gameLoop;
		
		snakeController = new SnakeController(this);
		
		//Just contemporary
		snakeController.createPlayer();
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
	public byte[] buildPostitions() {
		
		byte[] concatenater = { -1 };
		
		//This one is pre-concat. The rest should be changed to follow this model
		byte[] playerPositions = snakeController.getPLayerPostitions(); 
															
		byte[] bonusPositions = bonusController.getAllPositions();
		
		byte[] gamePlanChanges = gamePlan.getChangeBoundaries();

		byte[] result = Bytes.concat(playerPositions, bonusPositions, concatenater, gamePlanChanges, concatenater);

		return result;
	}
	public void handleInput(int[] input) {

		if (input[1] == 1) {
			snakeController.turnPlayer(input[0], (byte) input[2]);
		}
		if (input[1] == 2) {
			gameLoop.pause();
		}
	}

}
