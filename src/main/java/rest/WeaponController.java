package rest;

import java.util.ArrayList;

public class WeaponController extends MovingController {

	public WeaponController(MasterController masterController) {
		super(masterController);
		super.setTypesControlled(Constants.PROJECTILES);
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
		super.getMovers().add(new Bullet(xPos, yPos, direction, speed));
		ArrayList<MovingObject> pros = super.getMovers();
		for (MovingObject pro : pros) {
			System.out.println("x = " + pro.getxPos());
			System.out.println("y = " + pro.getyPos());
		}
	}
	/**
	 * //This is used to see if a moving object has moved in to the bullet:
	 * public byte[] hitCheck(byte x, byte y) { byte[] result = new byte[2];
	 * Iterator<Projectile> itr = projectiles.iterator(); while(itr.hasNext()) {
	 * Projectile projectile = itr.next(); if(projectile.isInPosition(x, y)) {
	 * result[0] = projectile.getObjectType(); result[1] =
	 * projectile.getObjectSubType(); itr.remove(); return result; } }
	 * 
	 * result[0] = -1; return result; }
	 **/

	/**
	 * public byte[] hitCheck(byte xPosition, byte yPosition) { //First byte
	 * stands for crash with player, second for player number of the crasher.
	 * //returns first byte -1 if no crash byte[] result = new byte[2];
	 * for(Projectile projectile: projectiles) { byte [] positions =
	 * projectile.getAllPositionsCrasch(); //for(Snake playerInner: players) {
	 * int n = 0; while(n < (positions.length) / 2) { // System.out.println(
	 * "x Tested = " + positions[2 * n]); // System.out.println("y Tested = " +
	 * positions[2 * n + 1]); if(xPosition == positions[2 * n] && yPosition ==
	 * positions[2 * n + 1]) { //System.out.println("Bang");
	 * 
	 * result[0] = projectile.getObjectType(); result[1] =
	 * projectile.getObjectSubType(); return result; } n ++; } // }
	 * 
	 * } result[0] = -1; return result; }
	 **/
}
