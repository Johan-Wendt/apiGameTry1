package rest;

public abstract class MovingObject extends VisibleObject {

	private int slowness = 4;
	private int slowCounter = 0;
	private int currentDirection = 1;
	private Tail tail;

	public MovingObject(MasterController masterController) {
		super(masterController);
	}

	public void move() {
		if (slowCounter % slowness == 0) {
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
			byte[] crashed = super.getMasterController().craschCheck(newxPos, newyPos);
			if (crashed[0] != -1) {
				handleCrash(crashed);
			} else {

				if (tail != null) {
					tail.move(super.getxPos(), super.getyPos());
				}
				super.setyPos(newxPos);
				super.setxPos(newyPos);
			}

		}
		slowCounter++;
	}

	public byte[] getAllPositions() {
		System.out.println("current direction = " + currentDirection);
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
		int k = 0;
		while(k< result.length) {
			System.out.println(result[k]);
			k++;
		}
		return result;
	}

	private boolean turnIsAllowed(byte direction) {
		byte currentDirection = getMovingDirection();
		if ((currentDirection == Constants.MOVE_UP && direction == Constants.MOVE_DOWN)
				|| (currentDirection == Constants.MOVE_DOWN && direction == Constants.MOVE_UP)
				|| (currentDirection == Constants.MOVE_RIGHT && direction == Constants.MOVE_LEFT)
				|| (currentDirection == Constants.MOVE_LEFT && direction == Constants.MOVE_RIGHT)) {
			return false;
		}
		return true;
	}

	private byte getMovingDirection() {
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
		}
	}

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

}
