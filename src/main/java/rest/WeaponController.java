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
		switch (weapon) {
		case KNIFE:
			break;
		case PISTOL:
			createBullet(xPos, yPos, direction, speed);
			break;
		case SHOTGUN:
			break;
		}
	}

	public void createBullet(byte xPos, byte yPos, byte direction, byte speed) {
		super.getControlledObjects().add(new Bullet(xPos, yPos, direction, speed));

	}

}
