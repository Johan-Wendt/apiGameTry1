package rest;

public class Projectile extends MovingObject {
	private Players owner;
	private int rangeLeft;
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

	private Projectile(byte xPos, byte yPos, byte direction, byte speed, byte sideDirection, Weapons oldWeapon) {
		super(xPos, yPos, direction, speed);
		if (oldWeapon.isExploder()) {
			weapon = Weapons.EXPLOSION;
		} else {
			weapon = oldWeapon;
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
	public void act(MasterController masterController) {
		super.act(masterController);
	//	System.out.println("time to split before = " + timeToSplite);
	//	System.out.println("CountDownStarted = " + countDownStarted);

		if (timeToSplite >= 0 && countDownStarted) {
		//	System.out.println("time to split = " + timeToSplite);
			timeToSplite--;

		}

	};

	@Override
	public void checkObjectSpecificActions(MasterController masterController) {
		rangeLeft--;
		if (rangeLeft <= 0) {
			super.setToBeRemoved();
		}
		if (timeToSplite >= 0 && countDownStarted) {
			System.out.println("time to split = " + timeToSplite);
			timeToSplite--;

		}
	}

	@Override
	public boolean handleCrashingInto(VisibleObject victim) {
		if (victim != null) {
			victim.handleCrashedInto(this);
			byte category = victim.getObjectType();
			byte happening = victim.getObjectSubType();

			switch (category) {
			case Constants.GAME_BOARD:
				super.setToBeRemoved();
				return false;
			case Constants.PLAYER:
				Snake snake = (Snake) victim;
				if (owner != snake.getPlayer()) {
					super.setToBeRemoved();
					return false;
				}
				return true;
			case Constants.BONUS:
				super.setToBeRemoved();
				return false;
			case Constants.PROJECTILES:
				return true;

			}

		}
		return true;

	}

	public void handleCrashedInto(MovingObject crasher) {
		byte category = crasher.getObjectType();
		byte happening = crasher.getObjectSubType();
		switch (category) {
		case Constants.GAME_BOARD:
			super.setToBeRemoved();
			break;
		case Constants.PLAYER:
			Snake snake = (Snake) crasher;
			if (owner != snake.getPlayer()) {
				super.setToBeRemoved();
			}
			break;
		case Constants.BONUS:
			super.setToBeRemoved();
			break;
		case Constants.PROJECTILES:
			break;

		}
		
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
			// result[7] = new Projectile(super.getNextxStep(right),
			// super.getNextyStep(right), this.getCurrentDirection(),
			// this.getSpeed(),
			// right, weapon);
		case 7:
			// result[6] = new Projectile(super.getNextxStep(left),
			// super.getNextyStep(left), this.getCurrentDirection(),
			// this.getSpeed(),
			// left, weapon);

		case 6:
			// result[5] = new Projectile(super.getNextxStep(right),
			// super.getNextyStep(right), this.getCurrentDirection(),
			// this.getSpeed(),
			// right, weapon);
		case 5:
			// result[4] = new Projectile(super.getNextxStep(left),
			// super.getNextyStep(left), this.getCurrentDirection(),
			// this.getSpeed(),
			// left, weapon);

		case 4:
			System.out.println("Creating four expl");
			result[3] = new Projectile(super.getNextxStep(back), super.getNextyStep(back), this.getCurrentDirection(),
					this.getSpeed(), right, weapon);
		case 3:
			result[2] = new Projectile(super.getNextxStep(front), super.getNextyStep(front), this.getCurrentDirection(),
					this.getSpeed(), left, weapon);

		case 2:
			result[1] = new Projectile(super.getNextxStep(right), super.getNextyStep(right), this.getCurrentDirection(),
					this.getSpeed(), right, weapon);
		case 1:
			result[0] = new Projectile(super.getNextxStep(left), super.getNextyStep(left), this.getCurrentDirection(),
					this.getSpeed(), left, weapon);
		}
		return result;
	}

	public byte getSplitParts() {
		return splitParts;
	}

	public void setSplitParts(byte splitParts) {
		this.splitParts = splitParts;
	}

	public int getRangeLeft() {
		return rangeLeft;
	}

	public void setRangeLeft(int rangeLeft) {
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
