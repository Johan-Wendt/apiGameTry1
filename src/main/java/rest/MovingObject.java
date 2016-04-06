package rest;

public abstract class MovingObject extends VisibleObject {

	private int slowness = 10;
	private int slowCounter = 0;
	private int currentDirection = 1;
	private Tail tail;
	private boolean mayTurn = true;

	public MovingObject() {
	}
	public MovingObject(int length) {
		if(length > 1) {
			tail = new Tail(length - 1);
		}
	}

	public void move(MasterController masterController) {
		if (slowCounter % slowness == 0) {
			checkObjectSpecificActions();
			byte newxPos = super.getxPos();
			byte newyPos = super.getyPos();
			switch (currentDirection) {
			case Constants.MOVE_UP:
				newyPos--;
				break;
			case Constants.MOVE_RIGHT:
				newxPos++;
				break;
			case Constants.MOVE_DOWN:
				newyPos++;
				break;
			case Constants.MOVE_LEFT:
				newxPos--;
				break;
			}

			
			byte[] crashed = masterController.craschCheck(newxPos, newyPos);
			if (crashed[0] != -1) {
				handleCrash(crashed);
			} else {

				if (tail != null) {
					tail.move(super.getxPos(), super.getyPos());
				}
				super.setxPos(newxPos);
				super.setyPos(newyPos);
				mayTurn = true;
			}

		}
		slowCounter++;
	}

	public byte[] getAllPositionsSend() {
		
		int size = (tail == null) ? 3 : tail.getTailSize(3);
		byte[] result = new byte[size + 1];
		result[0] = super.getObjectTypeNumber();
		int n = 1;
		result[n] = super.getObjectNumber();
		result[n + 1] = super.getxPos();
		result[n + 2] = super.getyPos();
		n += 3;
		if (tail != null) {
			tail.getTailPositions(result, n);
		}
		return result;
	}
	
public byte[] getAllPositionsCrasch() {
		
		int size = (tail == null) ? 2 : tail.getTailSize(2);
		byte[] result = new byte[size];
		int n = 0;
		result[n + 1] = super.getxPos();
		result[n + 2] = super.getyPos();
		n += 2;
		if (tail != null) {
			tail.getTailPositions(result, n);
		}
		return result;
	}

	public abstract boolean turnIsAllowed(byte direction);


	public byte getMovingDirection() {
		if (super.getyPos() - tail.getyPos() < 0) {
			return Constants.MOVE_UP;
		}
		if (super.getyPos() - tail.getyPos() > 0) {
			return Constants.MOVE_DOWN;
		}
		if (super.getxPos() - tail.getxPos() < 0) {
			return Constants.MOVE_LEFT;
		}
		if (super.getxPos() - tail.getxPos() > 0) {
			return Constants.MOVE_RIGHT;
		}
		return 0;
	}

	public void turn(byte direction) {
		
		if (turnIsAllowed(direction)) {
			currentDirection = direction;
			mayTurn = false;
		}
	}
	public abstract void checkObjectSpecificActions();

	public void makeLonger() {
		if (tail == null) {
			tail = new Tail();
		} else {
			tail.addTail();
		}
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

	public boolean isMayTurn() {
		return mayTurn;
	}

	public void setMayTurn(boolean mayTurn) {
		this.mayTurn = mayTurn;
	}


}
