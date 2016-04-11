package rest;

public abstract class Controller {
	private byte typesControlled;
	private byte numberOfSubTypes;
	public Controller() {
		
	}
	public abstract byte[] getAllPositions();
	public abstract void disposeOfRemovables();
	public byte getTypesControlled() {
		return typesControlled;
	}
	public void setTypesControlled(byte typesControlled) {
		this.typesControlled = typesControlled;
	}
	public byte getNumberOfSubTypes() {
		return numberOfSubTypes;
	}
	public void setNumberOfSubTypes(byte numberOfSubTypes) {
		this.numberOfSubTypes = numberOfSubTypes;
	}
	
	


}
