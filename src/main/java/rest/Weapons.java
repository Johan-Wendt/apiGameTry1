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
		public int getRange() {
			return 1;
		}

		@Override
		public double speedMultiplier() {
			return 1;
		}

		@Override
		public boolean isExploder() {
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
		public int getRange() {
			return 100;
		}

		@Override
		public double speedMultiplier() {
			return 1.5;
		}

		@Override
		public boolean isExploder() {
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
		public int getRange() {
			return 7;
		}

		@Override
		public double speedMultiplier() {
			return 1.2;
		}

		@Override
		public boolean isExploder() {
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
			return Constants.MINE;
		}

		@Override
		public byte getAmmoToShoot() {
			return 3;
		}

		@Override
		public int getRange() {
			return 1000;
		}

		@Override
		public double speedMultiplier() {
			return 0;
		}

		@Override
		public boolean isExploder() {
			return true;
		}

		@Override
		public boolean isFrontFire() {
			return false;
		}

		@Override
		public byte timeToSplit() {
			return 40;
		}

		@Override
		public byte splitParts() {
			return 4;
		}

		@Override
		public boolean isStartCountDown() {
			return false;
		}

	},
	MINE_FIELD {

		@Override
		public byte getProjectileSubType() {
			return Constants.MINE_FIELD;
		}

		@Override
		public byte getAmmoToShoot() {
			return 3;
		}

		@Override
		public int getRange() {
			return 1000;
		}

		@Override
		public double speedMultiplier() {
			return 0;
		}

		@Override
		public boolean isExploder() {
			return true;
		}

		@Override
		public boolean isFrontFire() {
			return false;
		}

		@Override
		public byte timeToSplit() {
			return 40;
		}

		@Override
		public byte splitParts() {
			return 4;
		}

		@Override
		public boolean isStartCountDown() {
			return false;
		}

	},
	EXPLOSION {

		@Override
		public byte getProjectileSubType() {
			return Constants.EXPLOSION;
		}

		@Override
		public byte getAmmoToShoot() {
			return 3;
		}

		@Override
		public int getRange() {
			return 4;
		}

		@Override
		public double speedMultiplier() {
			return 0;
		}

		@Override
		public boolean isExploder() {
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

	};
	public abstract byte getProjectileSubType();

	public abstract byte getAmmoToShoot();

	public abstract int getRange();

	public abstract double speedMultiplier();

	public abstract boolean isExploder();

	public abstract boolean isFrontFire();

	public abstract byte timeToSplit();

	public abstract byte splitParts();

	public abstract boolean isStartCountDown();

}
