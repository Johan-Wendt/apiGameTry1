package rest;

import java.util.ArrayList;

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
				result[n] = projectile.getObjectTypeNumber();
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
	
	public void createBullet() {
		
	}

}
