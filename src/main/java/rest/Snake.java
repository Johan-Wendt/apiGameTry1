package rest;

import java.util.ArrayList;
import java.util.HashSet;

import com.google.common.primitives.Bytes;

public abstract class Snake extends MovingObject {
	private Players player;
	private Weapons weapon;
	private ArrayList<Weapons> allWeapons = new ArrayList<>();
	private int ammo = 0;

	public Snake(byte playerNumber) {
		super();

		setPlayer(playerNumber);
		restart();
		super.setObjectSubType(playerNumber);
		super.setObjectType(Constants.PLAYER);
		super.setSpeed(player.getStartingSpeed());
		super.setLength(player.getStartingLength());
		restart();

		loadALLWeaponsCheat();

	}

	public boolean handleCrashingInto(VisibleObject victim) {
		// super.handleCrashingInto(victim);
		if (victim != null) {
			victim.handleCrashedInto(this);
			byte category = victim.getObjectType();
			byte happening = victim.getObjectSubType();

			switch (category) {
			case Constants.GAME_BOARD:
				deathPenalty();
				restart();
				return false;
			case Constants.PLAYER:
				deathPenalty();
				restart();
				return false;
			case Constants.BONUS:
				receiveBonus(happening);
				super.setMayTurn(false);
				return false;
			case Constants.PROJECTILES:
				Projectile projectile = (Projectile) victim;
				if (projectile.getOwner() == player || projectile.getObjectSubType() == Constants.MINE_FIELD
						|| projectile.getObjectSubType() == Constants.MINE) {
					return true;
				}
				deathPenalty();
				restart();
				return false;

			}

		}
		return true;

	}

	public void handleCrashedInto(MovingObject crasher) {
		if (crasher != null) {
			byte category = crasher.getObjectType();
			byte happening = crasher.getObjectSubType();

			switch (category) {
			case Constants.PLAYER:
				// deathPenalty();
				// restart();
				break;
			case Constants.PROJECTILES:
				Projectile projectile = (Projectile) crasher;
				if (projectile.getOwner() != player || projectile.getObjectSubType() != Constants.MINE_FIELD
						|| projectile.getObjectSubType() != Constants.MINE) {
					deathPenalty();
					restart();
				}
				break;
			}

		}

	}

	private void restart() {
		weapon = Weapons.KNIFE;
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
		if (ammo >= weapon.getAmmoToShoot()) {
			ammo -= weapon.getAmmoToShoot();
			super.setPartTillNextmove(0);
			byte[] dir = super.getNextStep(super.getMovingDirection());
			weaponController.shoot(this);
		}
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
		addWeaponToColletcion(Weapons.MINE);

		ammo = 1000;
	}

	private void addWeaponToColletcion(Weapons weapon) {
		if (!allWeapons.contains(weapon)) {
			allWeapons.add(weapon);
			this.weapon = weapon;
		}
	}

	public void changeWeapon() {
		int index = allWeapons.indexOf(weapon);
		int newIndex = (index == allWeapons.size() - 1) ? 0 : index + 1;
		weapon = allWeapons.get(newIndex);

	}

	public void receiveBonus(byte happening) {

		switch (happening) {
		case Constants.SPEED_BONUS:
			setLength((byte) (getLength() + 2));
			break;
		case Constants.GROW_BONUS:
			setSpeed((byte) (getSpeed() + 2));
			break;
		case Constants.PISTOL:
			addWeaponToColletcion(Weapons.PISTOL);
			break;
		case Constants.SHOTGUN:
			addWeaponToColletcion(Weapons.SHOTGUN);
			break;

		case Constants.AMMO:
			ammo++;
			break;

		}

	}

	public byte[] getShotPosition(boolean front) {
		if (front) {
			return super.getNextStep(super.getMovingDirection());
		}
		return super.getLastTailPosition();
	}

}
