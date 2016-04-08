package rest;

public abstract class MovingObject extends VisibleObject {

	private byte speed = 0;;
	private int currentDirection = 1;
	private int partTillNextmove = 0;
	private Tail tail;
	private boolean mayTurn = true;

	public MovingObject() {
	}
	public MovingObject(byte xPos, byte yPos) {
		super(xPos, yPos);
	}
	public MovingObject(byte xPos, byte yPos, byte speed) {
		super(xPos, yPos);
		this.speed = speed;
	}
	public MovingObject(int length) {
		if(length > 1) {
			tail = new Tail(length - 1);
		}
	}

	public void move(MasterController masterController) {
		if (partTillNextmove >= Constants.UPDATES_PER_SECOND) {
			partTillNextmove -= Constants.UPDATES_PER_SECOND;
			checkObjectSpecificActions(masterController);
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

		//	System.out.println("newxPos = " + newxPos);
		//	System.out.println("newyPos = " + newyPos);
			
			byte[] crashed = masterController.craschCheck(newxPos, newyPos);
			if (crashed[0] != -1) {
			//	System.out.println("bang bang");
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
		partTillNextmove += speed;
		//slowCounter++;
	}
	public byte[] getNextStep(int direction) {
		byte[] result = new byte[2];
		byte newxPos = super.getxPos();
		byte newyPos = super.getyPos();
		switch (direction) {
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
		result [0] = newxPos;
		result [1] = newyPos;
		return result;
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
		result[n] = super.getxPos();
		result[n + 1] = super.getyPos();
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
	public abstract void checkObjectSpecificActions(MasterController masterController);

	public void makeLonger() {
		if (tail == null) {
			tail = new Tail();
		} else {
			tail.addTail();
		}
	}

	public abstract void handleCrash(byte[] crashInfo);


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
	public byte getSpeed() {
		return speed;
	}
	public void setSpeed(byte speed) {
		this.speed = speed;
	}
	public void setLength(byte length) {
		if(length > 1) {
			if(tail == null) {
				tail = new Tail(length - 1);
			}
			else {
				tail.setLength((byte) (length - 1));
			}
		}
		else {
			removeAllTails();
		}
	}
	public byte getLength() {
		byte result = 1;
		if(tail != null) {
			return tail.getLength(result);
		}
		return result;
	}
	public void removeAllTails() {
		if(tail != null) {
		    tail.removeAllTails();
		    tail = null;
		}
	}


}
