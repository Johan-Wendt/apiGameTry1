package rest;

import java.util.ArrayList;
import java.util.HashSet;

import com.google.common.primitives.Bytes;

public abstract class Snake extends MovingObject {
	private Players player;
	private Weapons weapon;
	private ArrayList<Weapons> allWeapons = new ArrayList<>();

	public Snake(byte playerNumber) {
		super();
		// Tail tailsTail = new Tail();
		// super.setTail(new Tail(tailsTail));
		setPlayer(playerNumber);
		restart();
		super.setObjectSubType(playerNumber);
		super.setObjectType(Constants.PLAYER);
		super.setSpeed(player.getStartingSpeed());
		super.setLength(player.getStartingLength());
		restart();
		
		
		//loadALLWeaponsCheat();

	}

	/**
	 * public void handleCrashingInto(byte[] crashInfo) { if (crashInfo.length >
	 * 1) { byte category = crashInfo[0]; byte happening = crashInfo[0];
	 * 
	 * switch (category) { case Constants.BOUNDARIES: deathPenalty(); restart();
	 * break; case Constants.PLAYER: deathPenalty(); restart(); break; case
	 * Constants.BONUS: super.makeLonger(); super.setMayTurn(false); break; }
	 * 
	 * } }
	 **/
	public void handleCrashingInto(VisibleObject victim) {
		super.handleCrashingInto(victim);
		if (victim != null) {
			byte category = victim.getObjectType();
			byte happening = victim.getObjectSubType();

			switch (category) {
			case Constants.GAME_BOARD:
				deathPenalty();
				restart();
				break;
			case Constants.PLAYER:
				deathPenalty();
				restart();
				break;
			case Constants.BONUS:
				super.makeLonger();
				super.setMayTurn(false);
				break;
			case Constants.PROJECTILES:
				deathPenalty();
				restart();
				break;
			}

		}

	}

	public void handleCrashedInto(MovingObject crasher) {
		if (crasher != null) {
			byte category = crasher.getObjectType();
			byte happening = crasher.getObjectSubType();

			switch (category) {
			case Constants.PLAYER:
			//	deathPenalty();
			//	restart();
				break;
			case Constants.PROJECTILES:
				deathPenalty();
				restart();
				break;
			}

		}

	}

	private void restart() {
		weapon = Weapons.PISTOL;
		// if(super.getTail() != null) {
		// super.getTail().hideTails();
		// }
		byte length = getLength();
		removeAllTails();
		setLength(length);

		super.setxPos(player.getStartingPositionX());
		super.setyPos(player.getStartingPositionY());
		super.setCurrentDirection(player.getStartingDirection());
	}

	private void setPlayer(byte playerNumber) {
		switch (playerNumber) {
		case Constants.PLAYER_ONE:
			player = Players.PlayerOne;
			break;
		case Constants.PLAYER_TWO:
			player = Players.PlayerTwo;
			break;
		case Constants.PLAYER_THREE:
			player = Players.PlayerThree;
			break;
		case Constants.PLAYER_FOUR:
			player = Players.PlayerFour;
			break;
		}
	}

	private void deathPenalty() {
		super.setLength((byte) (super.getLength() - 2));
		weapon = Weapons.KNIFE;
	}

	public void shoot(WeaponController weaponController) {
		super.setPartTillNextmove(0);
		byte[] dir = super.getNextStep(super.getMovingDirection());
		weaponController.shoot(dir[0], dir[1], super.getMovingDirection(), (byte) (super.getSpeed() + 5), weapon);
	}

	public Players getPlayer() {
		return player;
	}

	public void setPlayer(Players player) {
		this.player = player;
	}

	public Weapons getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapons weapon) {
		this.weapon = weapon;
	}
	private void loadALLWeaponsCheat() {
		addWeaponToColletcion(Weapons.KNIFE);
		addWeaponToColletcion(Weapons.PISTOL);
		addWeaponToColletcion(Weapons.SHOTGUN);
	}
	private void addWeaponToColletcion(Weapons weapon) {
		if(!allWeapons.contains(weapon)) {
			allWeapons.add(weapon);
			this.weapon = weapon;
		}
	}
	public void changeWeapon() {
		int index = allWeapons.indexOf(weapon); 
		int newIndex = (index == allWeapons.size() -1) ? 0 : index + 1;
		weapon = allWeapons.get(newIndex);

		
	}
	public void receiveBonus(Bonus bonus) {
		if(bonus instanceof Weapon) {
			Weapon weapon = (Weapon) bonus;
			addWeaponToColletcion(weapon.getWeapon());
		}
		switch (bonus){
		
		}

	}

}
