package rest;

public interface Constants {
	public static final byte UPDATES_PER_SECOND = 60;
	//Moving constants
	public static final byte MOVE_UP = 1;
	public static final byte MOVE_RIGHT = 2;
	public static final byte MOVE_DOWN = 3;
	public static final byte MOVE_LEFT = 4;
	public static final byte PAUSE = 5;
	
	//Object type constants
	public static final byte GAME_BOARD = 0;
	public static final byte PLAYER = 1; 
	public static final byte BONUS = 2; 
	public static final byte PROJECTILES = 3; 
	public static final byte WEPAPON = 4; 

	//Object sub category constants
	public static final byte OUT_OF_BORDERS = 1;
	
	//PLayer subs
	public static final byte PLAYER_ONE = 1;
	public static final byte PLAYER_TWO = 2;
	public static final byte PLAYER_THREE = 3;
	public static final byte PLAYER_FOUR = 4;
	
	//Bonus subs
	public static final byte SPEED_BONUS = 1;
	public static final byte GROW_BONUS = 2;
	
	//Projectile subs
	public static final byte BULLET = 1;
	
	//Bullet subs
	public static final byte HIT = 1;
	
	//Communication with MasterController constants
	public static final byte PROJECTILE_FIRED = 1;
	
	//Object category constants
	
	//Game board
	public static final int GAME_HEIGHT = 41;
	public static final int GAME_WIDTH = 41;
	
	
	//Weapon sub
	public static final byte KNIFE = 1;
	public static final byte PISTOL = 2;
	public static final byte SHOTGUN = 3;
	
	//Odds for bonuses
	public static final Double SPEED_CHANCE = 0.0001;
	public static final Double PISTOL_CHANCE = 0.0001;
	

}
