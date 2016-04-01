package rest;

import java.util.HashMap;
import java.util.HashSet;

public class GamePlan {
	public static final int GAME_HEIGHT = 51;
	public static final int GAME_WIDTH = 51;
	private HashSet<Integer> boundaries = new HashSet<>();
	private HashMap<Integer, Integer> onStage = new HashMap<>();
	private static byte[] startBoundaries;
	private byte xPos;
	private byte yPos;
	private byte[] xOccupied;
	private byte[] yOccupied;
	
	public GamePlan() {
		loadGameStartBoundaries();
		loadGameStartBoundariesForClient(); 
	}
	
	public void loadGameStartBoundaries() {
		Integer n = 1;
		while(n <= GAME_WIDTH) {
			boundaries.add(n);
			n ++;
		}
		Integer k = GAME_WIDTH * (GAME_HEIGHT - 1) + 1;
		while(k <= GAME_WIDTH * GAME_HEIGHT) {
			boundaries.add(k);
			k ++;
		}
		Integer i = 1 + GAME_WIDTH;
		while(i <= GAME_WIDTH * (GAME_HEIGHT - 1) + 1) {
			boundaries.add(i);
			i *= GAME_WIDTH;
		}
		Integer j = GAME_WIDTH * 2;
		while(j < GAME_WIDTH * GAME_HEIGHT) {
			boundaries.add(j);
			j *= GAME_WIDTH;
		}
	}
	
	public void loadGameStartBoundariesForClient() {
		xOccupied = new byte[2 * GAME_WIDTH + 2 * GAME_HEIGHT - 2];
		yOccupied = new byte[2 * GAME_WIDTH + 2 * GAME_HEIGHT - 2];
		int n = 0;
		xPos = 1;
		yPos = 1;
		while(xPos <= GAME_WIDTH) {
			xOccupied[n] = xPos;
			yOccupied[n] = yPos;
			xPos ++;
			n++;
		}
		xPos = 1;
		yPos = GAME_HEIGHT;
		while(xPos <= GAME_WIDTH) {
			xOccupied[n] = xPos;
			yOccupied[n] = yPos;
			xPos ++;
			n++;
		}
		xPos = 1;
		yPos = 2;
		while(yPos <= GAME_HEIGHT - 1) {
			xOccupied[n] = xPos;
			yOccupied[n] = yPos;
			yPos ++;
			n++;
		}
		xPos = GAME_WIDTH;
		yPos = 2;
		while(yPos <= GAME_HEIGHT - 1) {
			xOccupied[n] = xPos;
			yOccupied[n] = yPos;
			yPos ++;
			n++;
		}
	}
	public boolean isInBoundaries(byte xPos, byte yPos) {
		Integer n = new Integer(xPos * yPos);
		return boundaries.contains(n);
	}

}
