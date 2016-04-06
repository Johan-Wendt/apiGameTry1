package rest;

public enum ObjectTypes {
	
	BOUNDARIES((byte)0), PLAYER((byte)1), BONUS((byte)2); 
	
	private byte value; 
	
	private ObjectTypes(byte value) { 
		this.value = value; 
		}


}
