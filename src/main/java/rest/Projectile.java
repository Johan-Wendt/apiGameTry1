package rest;

public class Projectile extends MovingObject {
	private Players owner;
	private byte rangeLeft;
	private Weapons weapon;
	private byte splitParts = 0;
	private byte timeToSplite = -1;
	private boolean countDownStarted;

	public Projectile(Snake snake) {
		super();
		owner = snake.getPlayer();
		weapon = snake.getWeapon();
		super.setObjectType(Constants.PROJECTILES);
		super.setObjectSubType(weapon.getProjectileSubType());
		super.setCurrentDirection(snake.getMovingDirection());
		super.setSpeed(((byte) (snake.getSpeed() * weapon.speedMultiplier())));
		super.setPosition(snake.getShotPosition(weapon.isFrontFire()));
		rangeLeft = weapon.getRange();
		timeToSplite = weapon.timeToSplit();
		splitParts = weapon.splitParts();
		countDownStarted = weapon.isStartCountDown();


	}

	private Projectile(byte xPos, byte yPos, byte direction, byte speed, byte sideDirection, Weapons weapon) {
		super(xPos, yPos, direction, speed);
		if(weapon.isExploder()) {
			this.weapon = Weapons.EXPLOSION;
		}

		super.setObjectType(Constants.PROJECTILES);
		super.setObjectSubType(weapon.getProjectileSubType());
		super.setSideWinderDirection(sideDirection);
		rangeLeft = weapon.getRange();

	}

	@Override
	public boolean turnIsAllowed(byte direction) {
		// TODO if homing make turn allowed???
		return false;
	}

	@Override
	public void checkObjectSpecificActions(MasterController masterController) {
		rangeLeft--;
		if (rangeLeft <= 0) {
			super.setToBeRemoved();
		}
		if (timeToSplite >= 0 && countDownStarted) {
			timeToSplite--;

		}
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

	public Players getOwner() {
		return owner;
	}

	public void setOwner(Players owner) {
		this.owner = owner;
	}

	public Projectile[] splitProjectile(byte parts) {
		byte front = this.getCurrentDirection();
		byte right = MovingObject.getRightOFDirection(front);
		byte left = MovingObject.getLeftOFDirection(front);
		byte back = MovingObject.getOppositOfDirection(front);
		
		
		Projectile[] result = new Projectile[parts];
		switch (parts) {
		case 8:
		//	result[1] = new Projectile(super.getNextxStep(right), super.getNextyStep(right), this.getCurrentDirection(), this.getSpeed(),
			//		right, weapon);
		case 7:
		//	result[0] = new Projectile(super.getNextxStep(left), super.getNextyStep(left), this.getCurrentDirection(), this.getSpeed(),
			//		left, weapon);
		
		case 6:
		//	result[1] = new Projectile(super.getNextxStep(right), super.getNextyStep(right), this.getCurrentDirection(), this.getSpeed(),
			//		right, weapon);
		case 5:
	//		result[0] = new Projectile(super.getNextxStep(left), super.getNextyStep(left), this.getCurrentDirection(), this.getSpeed(),
		//			left, weapon);
		
		case 4:
			result[1] = new Projectile(super.getNextxStep(back), super.getNextyStep(back), this.getCurrentDirection(), this.getSpeed(),
					right, weapon);
		case 3:
			result[0] = new Projectile(super.getNextxStep(front), super.getNextyStep(front), this.getCurrentDirection(), this.getSpeed(),
					left, weapon);
		
		case 2:
			result[1] = new Projectile(super.getNextxStep(right), super.getNextyStep(right), this.getCurrentDirection(), this.getSpeed(),
					right, weapon);
		case 1:
			result[0] = new Projectile(super.getNextxStep(left), super.getNextyStep(left), this.getCurrentDirection(), this.getSpeed(),
					left, weapon);
		}
		return result;
	}

	public byte getSplitParts() {
		return splitParts;
	}

	public void setSplitParts(byte splitParts) {
		this.splitParts = splitParts;
	}

	public byte getRangeLeft() {
		return rangeLeft;
	}

	public void setRangeLeft(byte rangeLeft) {
		this.rangeLeft = rangeLeft;
	}

	public Weapons getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapons weapon) {
		this.weapon = weapon;
	}

	public byte getTimeToSplite() {
		return timeToSplite;
	}

	public void setTimeToSplite(byte timeToSplite) {
		this.timeToSplite = timeToSplite;
	}
	public void startCountdown() {
		countDownStarted = true;
	}

}
