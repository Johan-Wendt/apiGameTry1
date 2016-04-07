package rest;

import java.util.ArrayList;

import com.google.common.primitives.Bytes;

public class SnakeController {
	private ArrayList<Snake> players = new ArrayList<>();
	private MasterController masterController;
	private byte numberOfcreatedPlayers = 0;
	public static final int MAX_NUMBER_OF_SNAKES = 4;

	public SnakeController(MasterController masterController) {
		this.masterController = masterController;
		
	}

	public void createPlayer() {
		if (numberOfcreatedPlayers < MAX_NUMBER_OF_SNAKES) {
			players.add(new Player((byte) (numberOfcreatedPlayers + 1)));
			numberOfcreatedPlayers++;
		}
	}

	public void createPlayerAI() {
		if (numberOfcreatedPlayers < MAX_NUMBER_OF_SNAKES) {
			players.add(new PlayerAI(masterController, (byte) (numberOfcreatedPlayers + 1)));
			numberOfcreatedPlayers++;
		}
	}
	public byte[] checkPlayerCrash(byte xPosition, byte yPosition) {
		//First byte stands for crash with player, second for player number of the crasher.
		//returns first byte -1 if no crash
		byte[] result = new byte[2];
		for(Snake player: players) {
			byte [] positions = player.getAllPositionsCrasch();
			//for(Snake playerInner: players) {
				int n = 0;
				while(n < (positions.length) / 2) {
					System.out.println("x Tested = " + positions[2 * n]);
					System.out.println("y Tested = " + positions[2 * n + 1]);
					if(xPosition == positions[2 * n] && yPosition == positions[2 * n + 1]) {
						System.out.println("Bang");

						result[0] = Constants.PLAYER;
						result[1] = player.getObjectNumber();
						return result;
					}
					n ++;
				}
		//	}
			
		}
		result[0] = -1;
		return result;
	}
	public void playerRound() {
		for(Snake player: players) {
			player.move(masterController);
		}
	}
	public byte[] getPLayerPostitions() {
		byte[] result = new byte[0];
		byte[] concatenator = {-1};
		for(Snake player: players) {
			result = Bytes.concat(result, player.getAllPositionsSend(), concatenator);
		}
		return result;
	}
	public void turnPlayer(int playerNumber, byte direction) {
		players.get(playerNumber - 1).turn(direction);
	}

}
