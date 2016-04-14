package rest;

import java.util.ArrayList;

public class WeaponController extends Controller {

	public WeaponController(MasterController masterController) {
		super();
		super.setTypesControlled(Constants.PROJECTILES);
		super.setNumberOfSubTypes((byte) 3);
	}
	@Override
	public void act(MasterController masterController) {
		super.act(masterController);
		super.disposeOfRemovables();
	}

	public void shoot(byte xPos, byte yPos, byte direction, byte speed, Weapons weapon) {
		super.getControlledObjects().add(new Bullet(xPos, yPos, direction, speed, weapon));
	}

	//public void createBullet(byte xPos, byte yPos, byte direction, byte speed, Weapons weapon) {
	//	super.getControlledObjects().add(new Bullet(xPos, yPos, direction, speed));

	//}

}
