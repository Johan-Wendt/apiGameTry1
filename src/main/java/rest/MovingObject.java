package rest;

public abstract class MovingObject extends ActingObject {

	private byte speed = 0;;
	private byte currentDirection = 1;
	private byte sideWinderDirection = 0;
	private int partTillNextmove = 0;
	private Tail tail;
	private boolean mayTurn = true;

	public MovingObject() {
	}

	public MovingObject(byte xPos, byte yPos) {
		super(xPos, yPos);
	}

	public MovingObject(byte xPos, byte yPos, byte direction, byte speed) {
		super(xPos, yPos);
		currentDirection = direction;
		this.speed = speed;
	}

	public MovingObject(int length) {
		if (length > 1) {
			tail = new Tail(length - 1, super.getObjectSubType());
		}
	}

	public void act(MasterController masterController) {
		move(masterController);
	}

	public void move(MasterController masterController) {
		if (partTillNextmove >= Constants.UPDATES_PER_SECOND) {
			boolean goOn = true;
			partTillNextmove -= Constants.UPDATES_PER_SECOND;
			checkObjectSpecificActions(masterController);
			byte[] newPos = getNextStep(currentDirection);

			VisibleObject crashed = masterController.craschCheck(newPos[0], newPos[1], this);
			if (crashed != null) {
				goOn = handleCrashingInto(crashed);
			} if(goOn) {

				if (tail != null) {
					tail.move(super.getxPos(), super.getyPos());
				}

				super.setPosition(newPos);
				mayTurn = true;
			}

		}
		partTillNextmove += speed;
		// slowCounter++;
	}

	public byte[] getNextStep(byte direction) {
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
		switch (sideWinderDirection) {
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
		result[0] = newxPos;
		result[1] = newyPos;
		return result;
	}
	public byte getNextxStep(byte direction) {
		byte newxPos = super.getxPos();
		switch (direction) {

		case Constants.MOVE_RIGHT:
			newxPos++;
			break;

		case Constants.MOVE_LEFT:
			newxPos--;
			break;
		}
		return newxPos;
	}
	public byte getNextyStep(byte direction) {
		byte newyPos = super.getyPos();
		switch (direction) {
		case Constants.MOVE_UP:
			newyPos--;
			break;
		case Constants.MOVE_DOWN:
			newyPos++;
			break;
		}
		return newyPos;
	}

	public byte[] getAllPositionsSend() {

		int size = (tail == null) ? 1 : tail.getTailSize(1);
		int sendSize = size * 2 + 1;
		byte[] result = new byte[sendSize];
		result[0] = super.getObjectSubType();
		int n = 1;
		result[n] = super.getxPos();
		result[n + 1] = super.getyPos();
		n += 2;
		if (tail != null) {
			tail.getTailPositions(result, n);
		}
		return result;
	}

	public byte[] getAllPositionsCrasch() {

		int size = (tail == null) ? 2 : (2 * tail.getTailSize(1));
		byte[] result = new byte[size];
		int n = 0;
		result[n] = super.getxPos();
		result[n + 1] = super.getyPos();
		n += 2;
		if (tail != null) {
			tail.getTailPositionsCrash(result, n);
		}
		return result;
	}

	public abstract boolean turnIsAllowed(byte direction);

	public byte getMovingDirection() {
		if (tail != null) {
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
		}
		return (byte) currentDirection;
	}



	public static byte getRightOFDirection(byte direction) {
		switch (direction) {
		case Constants.MOVE_UP:
			return Constants.MOVE_RIGHT;
		case Constants.MOVE_RIGHT:
			return Constants.MOVE_DOWN;
		case Constants.MOVE_DOWN:
			return Constants.MOVE_LEFT;
		case Constants.MOVE_LEFT:
			return Constants.MOVE_UP;
		default:
			return Constants.MOVE_RIGHT;

		}
	}
	public static byte getLeftOFDirection(byte direction) {
		switch (direction) {
		case Constants.MOVE_UP:
			return Constants.MOVE_LEFT;
		case Constants.MOVE_RIGHT:
			return Constants.MOVE_UP;
		case Constants.MOVE_DOWN:
			return Constants.MOVE_RIGHT;
		case Constants.MOVE_LEFT:
			return Constants.MOVE_DOWN;
		default:
			return Constants.MOVE_LEFT;

		}
	}
	public static byte getOppositOfDirection(byte direction) {
		switch (direction) {
		case Constants.MOVE_UP:
			return Constants.MOVE_DOWN;
		case Constants.MOVE_RIGHT:
			return Constants.MOVE_LEFT;
		case Constants.MOVE_DOWN:
			return Constants.MOVE_UP;
		case Constants.MOVE_LEFT:
			return Constants.MOVE_RIGHT;
		default:
			return Constants.MOVE_DOWN;

		}
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
			tail = new Tail(super.getObjectSubType());
		} else {
			tail.addTail();
		}
	}

	public abstract boolean handleCrashingInto(VisibleObject victim);

	public byte getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(byte currentDirection) {
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
		if (length > 1) {
			if (tail == null) {
				tail = new Tail((length - 1), super.getObjectSubType());
			} else {
				tail.setLength((byte) (length - 1));
			}
		} else {
			removeAllTails();
		}
	}

	public byte getLength() {
		byte result = 1;
		if (tail != null) {
			return tail.getLength(result);
		}
		return result;
	}

	public void removeAllTails() {
		if (tail != null) {
			tail.removeAllTails();
			tail = null;
		}
	}

	public int getPartTillNextmove() {
		return partTillNextmove;
	}

	public void setPartTillNextmove(int partTillNextmove) {
		this.partTillNextmove = partTillNextmove;
	}

	public byte[] getLastTailPosition() {
		if (tail == null) {
			byte[] result = { super.getxPos(), super.getyPos() };
			return result;
		}
		return tail.getLastTailPosition();
	}

	public byte getSideWinderDirection() {
		return sideWinderDirection;
	}

	public void setSideWinderDirection(byte sideWinderDirection) {
		this.sideWinderDirection = sideWinderDirection;
	}

}
