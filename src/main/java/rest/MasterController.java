package rest;

import java.nio.ByteBuffer;

import com.google.common.primitives.Bytes;

public class MasterController implements Constants{
	private GamePlan gamePlan = new GamePlan();
	private BonusController bonusController = new BonusController(gamePlan);
	private SnakeController snakeController;
	private GameLoop gameLoop;
	private MyWebSocketHandler socket;
	
	public MasterController(MyWebSocketHandler socket) {

		this.socket = socket;
		
		snakeController = new SnakeController(this);
		
		//Just contemporary
		snakeController.createPlayer();
		snakeController.createPlayerAI();
		gameLoop = new GameLoop(this);
		gameLoop.runGameLoop();
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
		int n = bonusPositions.length;
		int k = 0;
		while(k < n) {
			k++;
		}
		
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
	public void sendPositions() {
		ByteBuffer buf = ByteBuffer.wrap(buildPostitions());
		socket.updatePlayer(buf);
	}
	public void shoot(Weapons weapon) {
		
	}

}
