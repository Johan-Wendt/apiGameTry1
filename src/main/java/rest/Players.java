package rest;

public enum Players {
	PlayerOne {

		@Override
		public byte getStartingDirection() {
			return Constants.MOVE_UP;
		}

		@Override
		public byte getStartingPositionX() {
			return ((GamePlan.GAME_WIDTH - 1)/ 2);
		}

		@Override
		public byte getStartingPositionY() {
			return ((GamePlan.GAME_HEIGHT - 1 )/ 2) - 1;
		}
	},
	PlayerTwo {

		@Override
		public byte getStartingDirection() {
			return Constants.MOVE_DOWN;
		}

		@Override
		public byte getStartingPositionX() {
			return ((GamePlan.GAME_WIDTH - 1)/ 2);
		}

		@Override
		public byte getStartingPositionY() {
			return ((GamePlan.GAME_HEIGHT - 1 )/ 2) + 1;
		}
		
	},
	
	PlayerThree {

		@Override
		public byte getStartingDirection() {
			return Constants.MOVE_RIGHT;
		}

		@Override
		public byte getStartingPositionX() {
			return ((GamePlan.GAME_WIDTH - 1)/ 2) + 1;
		}

		@Override
		public byte getStartingPositionY() {
			return ((GamePlan.GAME_HEIGHT - 1 )/ 2);
		}
	},
	PlayerFour {

		@Override
		public byte getStartingDirection() {
			return Constants.MOVE_LEFT;
		}

		@Override
		public byte getStartingPositionX() {
			return ((GamePlan.GAME_WIDTH - 1)/ 2) - 1;
		}

		@Override
		public byte getStartingPositionY() {
			return ((GamePlan.GAME_HEIGHT - 1 )/ 2);
		}
		
	};
	public abstract byte getStartingDirection();
	public abstract byte getStartingPositionX();
	public abstract byte getStartingPositionY();
	
	public byte getStartingLength() {
		return 4;
	}

}
