package rest;

import java.nio.ByteBuffer;

import com.google.common.primitives.Bytes;

public class MasterController implements Constants{
	private GameBoardController gameBoardController = new GameBoardController();
	private BonusController bonusController = new BonusController();
	private WeaponController weaponController;
	private SnakeController snakeController;
	private GameLoop gameLoop;
	private HumanTouch socket;
	
	public MasterController(HumanTouch socket) {

		this.socket = socket;
		
		snakeController = new SnakeController(this);
		weaponController = new WeaponController(this);
		
		//Just contemporary
		snakeController.createPlayer();
		snakeController.createPlayerAI();
		gameLoop = new GameLoop(this);
		gameLoop.runGameLoop();
	}
	
	public VisibleObject craschCheck(byte xPos,byte yPos, MovingObject movingObject) {
		VisibleObject plan = gameBoardController.checkCrash(xPos, yPos, movingObject);
		if(plan != null) {
			return plan;
 		}
		VisibleObject bonus = bonusController.checkCrash(xPos, yPos, movingObject);
		if(bonus != null) {
			return bonus;
 		}
		VisibleObject play = snakeController.checkCrash(xPos, yPos, movingObject);
		if(play != null) {
			return play;
 		}
		VisibleObject proj = weaponController.checkCrash(xPos, yPos, movingObject);
		if(proj != null) {
			return proj;
 		}
		//byte[] noDice = {-1};
		return null;
	}
	public void gameRound() {
		weaponController.act(this);
		snakeController.act(this);
		bonusController.act(this);
	}
	public byte[] buildPostitions() {

		byte[] playerPositions = snakeController.getAllPositions(); 
		byte [] projectilePositions = weaponController.getAllPositions();
															
		byte[] bonusPositions = bonusController.getAllPositions();
	//	}
		
		byte[] gamePlanChanges = gameBoardController.getAllPositions();

		byte[] result = Bytes.concat(playerPositions, projectilePositions, bonusPositions, gamePlanChanges);
		//byte[] result = Bytes.concat(playerPositions, projectilePositions, bonusPositions);

		return result;
	}
	public void handleInput(int[] input) {
		//First byte is player number
		//Second byte is type of action
		     //1 is turn
		     //2 is pause
		     //3 is shoot
		     //4 is Change weapon
		//Third byte is additional info (like turn direction)

		if (input[1] == 1) {
			snakeController.turnPlayer(input[0], (byte) input[2]);
		}
		if (input[1] == 2) {
			gameLoop.pause();
		}
		if (input[1] == 3) {
			snakeController.shoot(weaponController, (byte) input[0]);
		}
		if (input[1] == 4) {
			snakeController.changeWeapon((byte) input[0]);
		}
	}
	public void sendPositions() {
		ByteBuffer buf = ByteBuffer.wrap(buildPostitions());
		socket.updatePlayer(buf);
	}
}
