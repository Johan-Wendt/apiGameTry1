package rest;

public class Bullet extends Projectile {
	Weapons weapon;

	public Bullet(byte xPos, byte yPos, byte direction, byte speed, Weapons weapon) {
		super(xPos, yPos, direction, speed);
		super.setObjectSubType(weapon.getProjectileSubType());
		this.weapon = weapon;
		System.out.println("Bam bam");
	}
	public Bullet(byte xPos, byte yPos, byte direction, byte speed, Weapons weapon, Players owner) {
		super(xPos, yPos, direction, speed, owner);
		super.setObjectSubType(weapon.getProjectileSubType());
		this.weapon = weapon;
	}

	@Override
	public boolean turnIsAllowed(byte direction) {
		// TODO if homing make turn allowed???
		return false;
	}

	@Override
	public void checkObjectSpecificActions(MasterController masterController) {
		// TODO

	}

	@Override
	public void handleCrashingInto(VisibleObject victim) {
		super.handleCrashingInto(victim);
		super.setToBeRemoved();
		if (victim != null) {
			super.setToBeRemoved();
			byte category = victim.getObjectType();
			byte happening = victim.getObjectSubType();

			switch (category) {
			case Constants.GAME_BOARD:
				super.setToBeRemoved();
				break;
			case Constants.PLAYER:
				super.setToBeRemoved();
				break;
			case Constants.BONUS:
				super.setToBeRemoved();
				break;
			case Constants.PROJECTILES:
				super.setToBeRemoved();
				break;
				
			}
			System.out.println("hit");
			System.out.println("category = " + category);
			System.out.println("happening = " + happening);

		}
		
	}

	public void handleCrashedInto(MovingObject crasher) {
		System.out.println("stuck at " + crasher.getObjectType());
		super.setToBeRemoved();
	}
	switch (weapon) {
	case KNIFE:
		break;
	case PISTOL:
		createBullet(xPos, yPos, direction, speed, );
		break;
	case SHOTGUN:
		break;
	}

}
