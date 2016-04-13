package rest;

public abstract class ActingObject extends VisibleObject{
	
	public ActingObject() {
		
	}
	public ActingObject(byte xPos, byte yPos) {
		super(xPos, yPos);
	}
	
	public abstract void act(MasterController masterController);

}
