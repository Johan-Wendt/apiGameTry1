package rest;

public class MineField extends ActingObject{
private Projectile owner;
private byte[] positions;
private byte countDown;
private boolean isCountingDown = false;

public MineField(Projectile owner) {
	this.owner = owner;
	countDown = owner.getTimeToSplite();
	fillUpPositions();
}
	@Override
	public void act(MasterController masterController) {
		if(isCountingDown) {
			countDown--;
		}
	}

	@Override
	public void handleCrashedInto(MovingObject crasher) {
		isCountingDown = true;
		owner.startCountdown();
	}
	
	@Override
	public byte[] getAllPositionsCrasch() {
		return positions;
	}
	@Override
	public byte[] getAllPositionsSend() {
		byte[] result = {-1};
		return result;
	}
	private void fillUpPositions(){
		positions = new byte [18];
		byte topLeftX = (byte) (owner.getxPos() - 1);
		byte topLeftY = (byte) (owner.getyPos() - 1);
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
