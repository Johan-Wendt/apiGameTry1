package rest;

public enum Weapons {
	KNIFE {

		@Override
		public byte getObjectSubType() {
			return Constants.KNIFE;
		}
		
		@Override
		public double getObjectChance() {
			return 0.0001;
		}
		
	},
	PISTOL{
		
		@Override
		public byte getObjectSubType() {
			return Constants.PISTOL;
		}
		
		@Override
		public double getObjectChance() {
			return 0.0001;
		}
		
	},
	SHOTGUN{
		
		@Override
		public byte getObjectSubType() {
			return Constants.SHOTGUN;
		}
		
		@Override
		public double getObjectChance() {
			return 0.0001;
		}
		
	};
	public abstract byte getObjectSubType();
	public abstract double getObjectChance();

}
