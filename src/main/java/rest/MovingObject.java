package rest;

public abstract class MovingObject extends VisibleObject implements Constants{
	private int slowness = 20;
	private int slowCounter = 0;
	private int currentDirection = 1;
	private Tail tail;
	
	
	public MovingObject() {
		
	}
	public void move() {
		if (slowCounter % slowness == 0) {
			if(tail != null) {
			    tail.move(super.getxPos(), super.getyPos());
			}
			switch (currentDirection) {
			case MOVE_UP:
				super.setyPos((byte)(super.getyPos() - 1));
				break;
			case MOVE_RIGHT:
				super.setxPos((byte)(super.getxPos() + 1));
				break;
			case MOVE_DOWN:
				super.setyPos((byte)(super.getyPos() + 1));
				break;
			case MOVE_LEFT:
				super.setxPos((byte)(super.getxPos() - 1));
				break;
			}
		}
		slowCounter++;
	}
	public byte[] getAllPositions() {
		int size = (tail == null) ? 3 : tail.getTailSize(3);
		byte[] result = new byte[size];
		int n = 0;
		result[n] = super.getObjectNumber();
		result[n + 1] = super.getxPos();
		result[n + 2] = super.getyPos();
		n += 3;
		if(tail != null) {
			tail.getTailPositions(result, n);
		}
		return result;
	}
	public int getSlowness() {
		return slowness;
	}
	public void setSlowness(int slowness) {
		this.slowness = slowness;
	}
	public int getSlowCounter() {
		return slowCounter;
	}
	public void setSlowCounter(int slowCounter) {
		this.slowCounter = slowCounter;
	}
	public int getCurrentDirection() {
		return currentDirection;
	}
	public void setCurrentDirection(int currentDirection) {
		this.currentDirection = currentDirection;
	}
	public Tail getTail() {
		return tail;
	}
	public void setTail(Tail tail) {
		this.tail = tail;
	}

}
