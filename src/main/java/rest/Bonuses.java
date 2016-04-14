package rest;

public enum Bonuses {

	SPPED {

		@Override
		public byte getObjectSubType() {
			return Constants.SPEED_BONUS;
		}

		@Override
		public double getObjectChance() {
			return 0.005;
		}
		
	},
	GROW {
		
		@Override
		public byte getObjectSubType() {
			return Constants.GROW_BONUS;
		}
		
		@Override
		public double getObjectChance() {
			return 0.005;
		}
	
		
	},
	PISTOL {
		
		@Override
		public byte getObjectSubType() {
			return Constants.PISTOL;
		}
		
		@Override
		public double getObjectChance() {
			return 0.002;
		}
	
		
	},
	SHOTGUN {
		
		@Override
		public byte getObjectSubType() {
			return Constants.SHOTGUN;
		}
		
		@Override
		public double getObjectChance() {
			return 0.001;
		}
	
		
	},
AMMO {
		
		@Override
		public byte getObjectSubType() {
			return Constants.AMMO;
		}
		
		@Override
		public double getObjectChance() {
			return 0.005;
		}
	
		
	};
	public  byte getObjectType() {
		return Constants.AMMO;
	}
	public abstract byte getObjectSubType();
	public abstract double getObjectChance();

}
