package rest;

public enum Weapons {
	KNIFE {

		@Override
		public byte getProjectileSubType() {
			return Constants.KNIFE_CUT;
		}

		@Override
		public byte getAmmoToShoot() {
			return 0;
		}

		@Override
		public byte getRange() {
			return 1;
		}

		@Override
		public double speedMultiplier() {
			return 1;
		}

		@Override
		public boolean isMine() {
			return false;
		}

		@Override
		public boolean isFrontFire() {
			return true;
		}

		@Override
		public byte timeToSplit() {
						return -1;
		}

		@Override
		public byte splitParts() {
			return 0;
		}

		@Override
		public boolean isStartCountDown() {
			return false;
		}

	},
	PISTOL {

		@Override
		public byte getProjectileSubType() {
			return Constants.PISTOL_BULLET;
		}

		@Override
		public byte getAmmoToShoot() {
			return 1;
		}

		@Override
		public byte getRange() {
			return 100;
		}

		@Override
		public double speedMultiplier() {
			return 1.5;
		}

		@Override
		public boolean isMine() {
			return false;
		}
		@Override
		public boolean isFrontFire() {
			return true;
		}
		@Override
		public byte timeToSplit() {
						return -1;
		}

		@Override
		public byte splitParts() {
			return 0;
		}
		@Override
		public boolean isStartCountDown() {
			return false;
		}

	},
	SHOTGUN {

		@Override
		public byte getProjectileSubType() {
			return Constants.SHOTGUN_BULLET;
		}

		@Override
		public byte getAmmoToShoot() {
			return 3;
		}

		@Override
		public byte getRange() {
			return 7;
		}

		@Override
		public double speedMultiplier() {
			return 1.2;
		}

		@Override
		public boolean isMine() {
			return false;
		}
		@Override
		public boolean isFrontFire() {
			return true;
		}
		@Override
		public byte timeToSplit() {
						return 1;
		}

		@Override
		public byte splitParts() {
			return 2;
		}
		@Override
		public boolean isStartCountDown() {
			return true;
		}

	},
	MINE {

		@Override
		public byte getProjectileSubType() {
			return Constants.MINE_FIELD;
		}

		@Override
		public byte getAmmoToShoot() {
			return 3;
		}

		@Override
		public byte getRange() {
			return 1;
		}

		@Override
		public double speedMultiplier() {
			return 1.5;
		}

		@Override
		public boolean isMine() {
			return true;
		}
		@Override
		public boolean isFrontFire() {
			return false;
		}
		@Override
		public byte timeToSplit() {
						return 4;
		}

		@Override
		public byte splitParts() {
			return 8;
		}
		@Override
		public boolean isStartCountDown() {
			return false;
		}

	};
	public abstract byte getProjectileSubType();

	public abstract byte getAmmoToShoot();

	public abstract byte getRange();

	public abstract double speedMultiplier();

	public abstract boolean isMine();
	
	public abstract boolean isFrontFire();
	public abstract byte timeToSplit();
	public abstract byte splitParts();
	public abstract boolean isStartCountDown();

}
