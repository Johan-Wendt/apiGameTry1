package rest;

public enum Bonuses {

	SPPED {

		@Override
		public byte getObjectSubType() {
			return Constants.SPEED_BONUS;
		}

		@Override
		public double getObjectChance() {
			return 0.01;
		}
		
	},
	GROW {
		
		@Override
		public byte getObjectSubType() {
			return Constants.GROW_BONUS;
		}
		
		@Override
		public double getObjectChance() {
			return 0.01;
		}
	
		
	};
	public abstract byte getObjectSubType();
	public abstract double getObjectChance();

}
