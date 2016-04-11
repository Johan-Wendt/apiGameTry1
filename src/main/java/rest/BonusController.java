package rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BonusController extends VisibleController {
	private double chance;
	private ArrayList<Enum> chanceMap = new ArrayList<Enum> ();
	

	public BonusController() {
		super.setTypesControlled(Constants.BONUS);
		super.setNumberOfSubTypes((byte) 2);
	}

	public void bonusRound() {
		if (Math.random() < chance) {
			byte xPos = (byte) (Math.random() * (GamePlan.GAME_WIDTH - 2) + 1);
			byte yPos = (byte) (Math.random() * (GamePlan.GAME_HEIGHT - 2) + 1);
			super.getControlledObjects().add(new SpeedBonus(xPos, yPos));
		}
	}
	public void addRandomBonus() {
		
	}
	public byte getRandomBonus(byte start, double random) {
		//Kolla om random är under totalsumman. Om den är det använd rekursion för
		//att hitta vilken bonus det är. 
	}
	private void loadBonusChances() {
		chanceMap.add(Bonuses.GROW);
		chance += Bonuses.GROW.getObjectChance();
		
		chanceMap.add(Bonuses.SPPED);
		chance += Bonuses.SPPED.getObjectChance();
				
		chanceMap.add(Weapons.PISTOL);
		chance += Weapons.PISTOL.getObjectChance();
				
		chanceMap.add(Weapons.SHOTGUN);
		chance += Weapons.SHOTGUN.getObjectChance();
		
		
	}
	



}
