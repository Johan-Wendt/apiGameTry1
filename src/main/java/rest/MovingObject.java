package rest;

public abstract class MovingObject extends VisibleObject implements Constants{
	
	private int slowness = 5;
	private int slowCounter = 0;
	private int currentDirection = 1;
	private Tail tail;
	
	public MovingObject(MasterController masterController) {
		super(masterController);
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
			byte[] crashed = super.getMasterController().craschCheck(super.getxPos(), super.getyPos());
			if(crashed[0] != -1) {
				handleCrash(crashed);
			}
					
		}
		slowCounter++;
	}
	public byte[] getAllPositions() {
		int size = (tail == null) ? 3 : tail.getTailSize(3);
		byte[] result = new byte[size + 1];
		result[0] = super.getObjectTypeNumber();
		int n = 1;
		result[n] = super.getObjectNumber();
		result[n + 1] = super.getxPos();
		result[n + 2] = super.getyPos();
		n += 3;
		if(tail != null) {
			tail.getTailPositions(result, n);
		}
		return result;
	}
	private boolean turnIsAllowed(byte direction) {
		byte currentDirection = getMovingDirection();
		if((currentDirection == MOVE_UP && direction == MOVE_DOWN) || (currentDirection == MOVE_DOWN && direction == MOVE_UP)
				|| (currentDirection == MOVE_RIGHT && direction == MOVE_LEFT) || (currentDirection == MOVE_LEFT && direction == MOVE_RIGHT)) {
			return false;
		}
		return true;
	}
	
	private byte getMovingDirection() {
		if(super.getyPos() - tail.getyPos() < 0) {
			return MOVE_UP;
		}
		if(super.getyPos() - tail.getyPos() > 0) {
			return MOVE_DOWN;
		}
		if(super.getxPos() - tail.getxPos() < 0) {
			return MOVE_LEFT;
		}
		if(super.getxPos() - tail.getxPos() > 0) {
			return MOVE_RIGHT;
		}
		return 0;
	}

	public abstract void handleCrash(byte[] crashInfo);
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
