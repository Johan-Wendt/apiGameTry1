package rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BonusController extends VisibleController {
	private double bonusChance;
	private double weaponChance;
	private ArrayList<Bonuses> bonuses = new ArrayList<Bonuses>();
	private ArrayList<Double> chanceMapBonus = new ArrayList<Double>();
	private ArrayList<Weapons> weapons = new ArrayList<Weapons>();
	private ArrayList<Double> chanceMapWeapons = new ArrayList<Double>();

	public BonusController() {
		super.setTypesControlled(Constants.BONUS);
		super.setNumberOfSubTypes((byte) 2);
	}

	public void bonusRound() {
		if (Math.random() * 10 < 7) {
			addRandomBonus();
		} else {
			addRandomWeapon();
		}
	}

	public void addRandomBonus() {
		double limit = Math.random();
		if (limit < bonusChance) {
			addBonusRandomPlace(getRandomBonus(limit));

		}
	}

	public void addRandomWeapon() {
		double limit = Math.random();
		if (limit < weaponChance) {
			if (limit < bonusChance) {
				addWeaponRandomPlace(getRandomWeapon(limit));
			}
		}
	}

	public Bonuses getRandomBonus(double limit) {
		int n = 0;
		double soFar = 0;
		while (n < bonuses.size()) {
			soFar += chanceMapBonus.get(n);

			if (limit < soFar) {
				return bonuses.get(n);
			}

			n++;

		}
		return null;
	}

	public Weapons getRandomWeapon(double limit) {
		int n = 0;
		double soFar = 0;
		while (n < bonuses.size()) {
			soFar += chanceMapBonus.get(n);

			if (limit < soFar) {
				return weapons.get(n);
			}

			n++;

		}
		return null;
	}

	private void loadBonusChances() {
		bonuses.add(Bonuses.GROW);
		chanceMapBonus.add(Bonuses.GROW.getObjectChance());
		bonusChance += Bonuses.GROW.getObjectChance();

		bonuses.add(Bonuses.SPPED);
		chanceMapBonus.add(Bonuses.SPPED.getObjectChance());
		bonusChance += Bonuses.SPPED.getObjectChance();

		weapons.add(Weapons.PISTOL);
		chanceMapWeapons.add(Weapons.PISTOL.getObjectChance());
		weaponChance += Weapons.PISTOL.getObjectChance();

		weapons.add(Weapons.SHOTGUN);
		chanceMapWeapons.add(Weapons.SHOTGUN.getObjectChance());
		weaponChance += Weapons.SHOTGUN.getObjectChance();

	}

	private void addBonusRandomPlace(Bonuses bonus) {
		byte xPos = (byte) (Math.random() * (GamePlan.GAME_WIDTH - 2) + 1);
		byte yPos = (byte) (Math.random() * (GamePlan.GAME_HEIGHT - 2) + 1);
		super.getControlledObjects().add(new PowerUp(xPos, yPos, bonus));
	}

	private void addWeaponRandomPlace(Weapons weapon) {
		byte xPos = (byte) (Math.random() * (GamePlan.GAME_WIDTH - 2) + 1);
		byte yPos = (byte) (Math.random() * (GamePlan.GAME_HEIGHT - 2) + 1);
		super.getControlledObjects().add(new Weapon(xPos, yPos, weapon));
	}

}
