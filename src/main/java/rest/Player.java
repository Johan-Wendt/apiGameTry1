package rest;

import java.util.ArrayList;

public class Player extends MovingObject implements Constants {

	public Player(GamePlan gamePlan) {
		super(gamePlan);
		super.setxPos((byte) 20);
		super.setyPos((byte) 30);
		Tail tailsTail = new Tail();
		super.setTail(new Tail(tailsTail));
	}

	public void handleCrash(int objectNumber) {
		restart();
	}
	private void restart() {
		super.setxPos((byte) 20);
		super.setyPos((byte) 30);
	}
}