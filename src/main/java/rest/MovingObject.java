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

			// System.out.println("newxPos = " + newxPos);
			// System.out.println("newyPos = " + newyPos);

			VisibleObject crashed = masterController.craschCheck(newxPos, newyPos, this);
			if (crashed != null) {
				// System.out.println("bang bang");
				handleCrashingInto(crashed);
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
		// slowCounter++;
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
		result[0] = newxPos;
		result[1] = newyPos;
		return result;
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

	public void handleCrashingInto(VisibleObject victim) {
		if (victim != null) {
			victim.handleCrashedInto(this);
		}
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

}
