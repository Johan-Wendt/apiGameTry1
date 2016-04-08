package rest;

import java.util.ArrayList;
import java.util.Iterator;

public class WeaponController extends Controller{
	private ArrayList<Projectile> projectiles = new ArrayList<>();
	
	public WeaponController() {
		
	}
	

	@Override
	public byte[] getAllPositions() {
		byte[] result;
		int nrBonuses = projectiles.size();
		if (nrBonuses > 0) {
			result = new byte[projectiles.size() * 3 + 1];
			result[0] = Constants.PROJECTILES;
			int n = 1;
			for (Projectile projectile : projectiles) {
				result[n] = projectile.getObjectSubType();
				result[n + 1] = projectile.getxPos();
				result[n + 2] = projectile.getyPos();
				n += 3;
			}
		} else {
			result = new byte[1];
			result[0] = -1;
		}
		return result;
	}
	
	public void createBullet(byte xPos, byte yPos, byte speed) {
		projectiles.add(new Bullet(xPos, yPos, speed));
		
	}
	/**
	//This is used to see if a moving object has moved in to the bullet:
	public byte[] hitCheck(byte x, byte y) {
		byte[] result = new byte[2];
		Iterator<Projectile> itr = projectiles.iterator();
		while(itr.hasNext()) {
			Projectile projectile = itr.next();
			if(projectile.isInPosition(x, y)) {
				result[0] = projectile.getObjectType();
				result[1] = projectile.getObjectSubType();
				itr.remove();
				return result;
			}
		}

		result[0] = -1;
		return result;
	}
	**/
	public byte[] hitCheck(byte xPosition, byte yPosition) {
	//First byte stands for crash with player, second for player number of the crasher.
			//returns first byte -1 if no crash
			byte[] result = new byte[2];
			for(Projectile projectile: projectiles) {
				byte [] positions = projectile.getAllPositionsCrasch();
				//for(Snake playerInner: players) {
					int n = 0;
					while(n < (positions.length) / 2) {
					//	System.out.println("x Tested = " + positions[2 * n]);
					//	System.out.println("y Tested = " + positions[2 * n + 1]);
						if(xPosition == positions[2 * n] && yPosition == positions[2 * n + 1]) {
							//System.out.println("Bang");

							result[0] = projectile.getObjectType();
							result[1] = projectile.getObjectSubType();
							return result;
						}
						n ++;
					}
			//	}
				
			}
			result[0] = -1;
			return result;
	}
}
