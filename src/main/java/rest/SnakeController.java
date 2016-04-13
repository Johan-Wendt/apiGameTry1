package rest;

public class SnakeController extends Controller {
	// private ArrayList<Snake> players = new ArrayList<>();
	private MasterController masterController;
	private byte numberOfcreatedPlayers = 0;
	public static final int MAX_NUMBER_OF_SNAKES = 4;

	public SnakeController(MasterController masterController) {
		super();
		super.setTypesControlled(Constants.PLAYER);
		

	}

	public void createPlayer() {
		if (numberOfcreatedPlayers < MAX_NUMBER_OF_SNAKES) {
			super.getControlledObjects().add(new Player((byte) (numberOfcreatedPlayers + 1)));
			numberOfcreatedPlayers++;
			super.setNumberOfSubTypes(numberOfcreatedPlayers);
		}
	}

	public void createPlayerAI() {
		if (numberOfcreatedPlayers < MAX_NUMBER_OF_SNAKES) {
			super.getControlledObjects().add(new PlayerAI((byte) (numberOfcreatedPlayers + 1)));
			numberOfcreatedPlayers++;
			super.setNumberOfSubTypes(numberOfcreatedPlayers);
		}
	}

	public void turnPlayer(int playerNumber, byte direction) {
		Snake player = (Snake) super.getControlledObjects().get(playerNumber - 1);
		player.turn(direction);
	}

	public void shoot(WeaponController weaponController, byte player) {
		Snake offender = (Snake) super.getControlledObjects().get(player - 1);
		offender.shoot(weaponController);
	}

	public void changeWeapon(byte playerNumber) {
		Snake player = (Snake) super.getControlledObjects().get(playerNumber - 1);
		player.changeWeapon();

	}


}
