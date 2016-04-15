package rest;

public class MineField extends ActingObject{
private Projectile owner;
private byte[] positions;

public MineField(Projectile owner) {
	this.owner = owner;
	fillUpPositions();
}
	@Override
	public void act(MasterController masterController) {
		
	}

	@Override
	public void handleCrashedInto(MovingObject crasher) {
		
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
		Fyll upp  3 ggr 3 runt owner
	}

}
