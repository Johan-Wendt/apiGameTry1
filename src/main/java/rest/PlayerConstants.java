package rest;

public interface PlayerConstants {
	public static final byte PLAYER_ONE_STARTING_DIRECTION = Constants.MOVE_UP;
	public static final byte PLAYER_TWO_STARTING_DIRECTION = Constants.MOVE_DOWN;
	public static final byte PLAYER_THREE_STARTING_DIRECTION = Constants.MOVE_RIGHT;
	public static final byte PLAYER_FOUR_STARTING_DIRECTION = Constants.MOVE_LEFT;
	
	public static final byte PLAYER_ONE_STARTING_POSITION_X = ((GamePlan.GAME_WIDTH + 1)/ 2);
	public static final byte PLAYER_ONE_STARTING_POSITION_Y = ((GamePlan.GAME_HEIGHT +1 )/ 2) - 1;
	public static final byte PLAYER_TWO_STARTING_POSITION_X = ((GamePlan.GAME_WIDTH + 1)/ 2);
	public static final byte PLAYER_TWO_STARTING_POSITION_Y = ((GamePlan.GAME_HEIGHT +1 )/ 2) + 1;
	public static final byte PLAYER_THREE_STARTING_POSITION_X = ((GamePlan.GAME_WIDTH + 1)/ 2) + 1;
	public static final byte PLAYER_THREE_STARTING_POSITION_Y = ((GamePlan.GAME_HEIGHT +1 )/ 2);
	public static final byte PLAYER_FOUR_STARTING_POSITION_X = ((GamePlan.GAME_WIDTH + 1)/ 2) - 1;
	public static final byte PLAYER_FOUR_STARTING_POSITION_Y = ((GamePlan.GAME_HEIGHT +1 )/ 2);


}
