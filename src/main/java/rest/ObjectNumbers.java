package rest;

public enum ObjectNumbers {
	
	OUT_OF_BORDERS((byte) 1), PLAYER_ONE((byte) 1), PLAYER_TWO((byte) 2), PLAYER_THREE((byte) 3), PLAYER_FOUR((byte) 4), SPEED_BONUS((byte) 1); 
	
	private byte value; 
	
	private ObjectNumbers(byte value) { 
		this.value = value; 
		}


}