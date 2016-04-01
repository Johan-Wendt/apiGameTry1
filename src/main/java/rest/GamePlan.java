package rest;

import java.util.HashMap;
import java.util.HashSet;

public class GamePlan {
	public static final int GAME_HIGHT = 51;
	public static final int GAME_WIDTH = 51;
	private static HashSet<Integer> boundaries = new HashSet<>();
	private static HashMap<Integer, Integer> onStage = new HashMap<>();
	
	public GamePlan() {
		loadGameStartBoundaries();
	}
	public void loadGameStartBoundaries() {
		Integer n = 1;
		while(n <= GAME_WIDTH) {
			boundaries.add(n);
			n ++;
		}
		Integer k = GAME_WIDTH * (GAME_HIGHT - 1) + 1;
		while(k <= GAME_WIDTH * GAME_HIGHT) {
			boundaries.add(k);
			k ++;
		}
		Integer i = 1 + GAME_WIDTH;
		while(i <= GAME_WIDTH * (GAME_HIGHT - 1) + 1) {
			boundaries.add(i);
			i *= GAME_WIDTH;
		}
		Integer j = GAME_WIDTH * 2;
		while(j < GAME_WIDTH * GAME_HIGHT) {
			boundaries.add(j);
			j *= GAME_WIDTH;
		}
	}

}
