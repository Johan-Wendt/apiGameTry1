package rest;

public enum Weapons {
	KNIFE {

		@Override
		public byte getProjectileSubType() {
			return Constants.KNIFE_CUT;
		}
		
		@Override
		public double getObjectChance() {
			return 0.0001;
		}

		@Override
		public byte getAmmoToShoot() {
			return 0;
		}
		
	},
	PISTOL{
		
		@Override
		public byte getProjectileSubType() {
			return Constants.PISTOL_BULLET;
		}
		
		@Override
		public double getObjectChance() {
			return 0.01;
		}
		@Override
		public byte getAmmoToShoot() {
			return 1;
		}
		
	},
	SHOTGUN{
		
		@Override
		public byte getProjectileSubType() {
			return Constants.SHOTGUN_BULLET;
		}
		
		@Override
		public double getObjectChance() {
			return 0.01;
		}
		@Override
		public byte getAmmoToShoot() {
			return 3;
		}
		
	};
	public abstract byte getProjectileSubType();
	
	public abstract double getObjectChance();
	public abstract byte getAmmoToShoot();

}
