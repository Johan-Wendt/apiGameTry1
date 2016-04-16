package rest;

public class MineField extends Projectile{
private Projectile owningProjectile;
private byte[] positions;
private byte countDown;
private boolean isCountingDown = false;

public MineField(Snake snake, Projectile owningProjectile) {
	super(snake);
	super.setObjectSubType(Constants.MINE_FIELD);
	this.owningProjectile = owningProjectile;
	countDown = owningProjectile.getTimeToSplite();
	fillUpPositions();
	//super.setObjectType(Constants.PROJECTILES); 
}
	@Override
	public void act(MasterController masterController) {
		if(isCountingDown) {
			countDown--;
		}
	}

	@Override
	public void handleCrashedInto(MovingObject crasher) {
		byte category = crasher.getObjectType();
		byte happening = crasher.getObjectSubType();
		switch (category) {
		case Constants.GAME_BOARD:
			super.setToBeRemoved();
			break;
		case Constants.PLAYER:
			Snake snake = (Snake) crasher;
			if (this.getOwner() != snake.getPlayer()) {
				owningProjectile.startCountdown();
				super.setToBeRemoved();
			}
			break;
		case Constants.BONUS:
			super.setToBeRemoved();
			break;
		case Constants.PROJECTILES:
			break;

		}
	}
	
	@Override
	public byte[] getAllPositionsCrasch() {
		return positions;
	}
	@Override
	public byte[] getAllPositionsSend() {
		byte[] result = {100};
		return result;
	}
	private void fillUpPositions(){
		positions = new byte [18];
		byte topLeftX = (byte) (owningProjectile.getxPos() - 1);
		byte topLeftY = (byte) (owningProjectile.getyPos() - 1);
		int n = 0;
		int k = 0;
		while(n < 3) {
			while(k < 3) {
				positions[2 * (3 * n + k)] = topLeftX;
				positions[2 * (3 * n + k) + 1] = topLeftY;
				topLeftX ++;
				k++;
						
			}
			topLeftY ++;
			n++;
		}
	}

}
