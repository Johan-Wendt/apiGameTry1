package rest;

public class SnakeController extends MovingController {
	// private ArrayList<Snake> players = new ArrayList<>();
	private MasterController masterController;
	private byte numberOfcreatedPlayers = 0;
	public static final int MAX_NUMBER_OF_SNAKES = 4;

	public SnakeController(MasterController masterController) {
		super(masterController);
		super.setTypesControlled(Constants.PLAYER);

	}

	public void createPlayer() {
		if (numberOfcreatedPlayers < MAX_NUMBER_OF_SNAKES) {
			super.getMovers().add(new Player((byte) (numberOfcreatedPlayers + 1)));
			numberOfcreatedPlayers++;
		}
	}

	public void createPlayerAI() {
		if (numberOfcreatedPlayers < MAX_NUMBER_OF_SNAKES) {
			super.getMovers().add(new PlayerAI((byte) (numberOfcreatedPlayers + 1)));
			numberOfcreatedPlayers++;
		}
	}

	/**
	 * public byte[] checkPlayerCrash(byte xPosition, byte yPosition,
	 * MovingObject crasher) { //First byte stands for crash with player, second
	 * for player number of the crasher. //returns first byte -1 if no crash
	 * byte[] result = new byte[2]; for(Snake player: players) { byte []
	 * positions = player.getAllPositionsCrasch(); //for(Snake playerInner:
	 * players) { int n = 0; while(n < (positions.length) / 2) { //
	 * System.out.println("x Tested = " + positions[2 * n]); //
	 * System.out.println("y Tested = " + positions[2 * n + 1]); if(xPosition ==
	 * positions[2 * n] && yPosition == positions[2 * n + 1]) {
	 * //System.out.println("Bang");
	 * 
	 * result[0] = player.getObjectType(); result[1] =
	 * player.getObjectSubType(); player.handleCrashedInto(crasher); return
	 * result; } n ++; } // }
	 * 
	 * } result[0] = -1; return result; }
	 * 
	 * public void playerRound() { for(Snake player: players) {
	 * player.move(masterController); } } /** public byte[] getAllPositions() {
	 * byte[] result = new byte[0]; byte[] concatenator = {-1}; for(Snake
	 * player: players) { result = Bytes.concat(result,
	 * player.getAllPositionsSend(), concatenator); } return result; }
	 **/
	public void turnPlayer(int playerNumber, byte direction) {
		super.getMovers().get(playerNumber - 1).turn(direction);
	}

	public void shoot(WeaponController weaponController, byte player) {
		Snake offender = (Snake) super.getMovers().get(player - 1);
		offender.shoot(weaponController);
	}

	public void changeWeapon(byte playerNumber) {
		Snake player = (Snake) super.getMovers().get(playerNumber - 1);
		player.changeWeapon();

	}

}
