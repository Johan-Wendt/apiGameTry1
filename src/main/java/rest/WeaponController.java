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
			super.getControlledObjects().add(new Bullet(xPos, yPos, direction, (byte) (speed * weapon.speedMultiplier()), weapon.getRange(), weapon));
			break;
		case PISTOL:
			super.getControlledObjects().add(new Bullet(xPos, yPos, direction, (byte) (speed * weapon.speedMultiplier()), weapon.getRange(), weapon));
			break;
		case SHOTGUN:
			super.getControlledObjects().add(new Bullet(xPos, yPos, direction, (byte) (speed * weapon.speedMultiplier()), weapon.getRange(), weapon));
			break;
		
		case MINE: 
			break;
		}
	}

	//public void createBullet(byte xPos, byte yPos, byte direction, byte speed, Weapons weapon) {
	//	super.getControlledObjects().add(new Bullet(xPos, yPos, direction, speed));

	//}

}
