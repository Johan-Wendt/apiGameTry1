package rest;

import java.util.ArrayList;
import java.util.Iterator;

public class WeaponController extends Controller {
	private ArrayList<Projectile> explodables = new ArrayList<>();

	public WeaponController(MasterController masterController) {
		super();
		super.setTypesControlled(Constants.PROJECTILES);
		super.setNumberOfSubTypes((byte) (Weapons.values().length + 1));
	}

	@Override
	public void act(MasterController masterController) {
		super.act(masterController);
		checkForEplosions();
		super.disposeOfRemovables();
	}

	public void shoot(Snake snake) {
		Projectile projectile = new Projectile(snake);
		super.getControlledObjects().add(projectile);
		if (projectile.getTimeToSplite() >= 0) {
		//	System.out.println("Added explodabel");
			explodables.add(projectile);
		}
		if (projectile.getWeapon() == Weapons.MINE) {
			//System.out.println("Added minefield");
			super.getControlledObjects().add(new MineField(snake, projectile));
		}
	}

	private void checkForEplosions() {
		Iterator<Projectile> itr = explodables.iterator();
		while (itr.hasNext()) {
			Projectile projectile = itr.next();
			if (projectile.getTimeToSplite() == 0) {
				Projectile[] blow = projectile.splitProjectile(projectile.getSplitParts());
				int n = 0;
				while(n < blow.length) {
					super.getControlledObjects().add(blow[n]);
				//	System.out.println("added subtype =" + blow[n].getObjectSubType());
					n++;
				}
				
				
				itr.remove();

			}
		}

	}

	// public void createBullet(byte xPos, byte yPos, byte direction, byte
	// speed, Weapons weapon) {
	// super.getControlledObjects().add(new Bullet(xPos, yPos, direction,
	// speed));

	// }

}
