package rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import com.google.common.primitives.UnsignedBytes;

public class GamePlan implements Constants {
	public static final int GAME_HEIGHT = 31;
	public static final int GAME_WIDTH = 31;
	private ArrayList<Integer> xOccupied = new ArrayList<>();
	private ArrayList<Integer> yOccupied = new ArrayList<>();
	private HashMap<Integer, Integer> onStage = new HashMap<>();
	private byte xPos;
	private byte yPos;
	private byte[] xInitialSend;
	private byte[] yInitialSend;
	boolean firstDone = false;
	
	public GamePlan() {
		loadGameStartBoundaries();
		loadGameStartBoundariesForClient(); 
	}
	
	public void loadGameStartBoundaries() {
		/**
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
		**/
	}
	
	public void loadGameStartBoundariesForClient() {
		xInitialSend = new byte[2 * GAME_WIDTH + 2 * GAME_HEIGHT - 2];
		yInitialSend = new byte[2 * GAME_WIDTH + 2 * GAME_HEIGHT - 2];
		int n = 0;
		xPos = 1;
		yPos = 1;
		System.out.println("first");
		while(xPos <= GAME_WIDTH) {
			xInitialSend[n] = xPos;
			yInitialSend[n] = yPos;
			xOccupied.add((int) xPos);
			yOccupied.add((int) yPos);
			System.out.println("x = " + xPos);
			System.out.println("y = " + yPos);
			xPos ++;
			n++;
		}
		xPos = 1;
		yPos = GAME_HEIGHT;
		System.out.println("second");
		while(xPos <= GAME_WIDTH) {
			xInitialSend[n] = xPos;
			yInitialSend[n] = yPos;
			xOccupied.add((int) xPos);
			yOccupied.add((int) yPos);
			System.out.println("x = " + xPos);
			System.out.println("y = " + yPos);
			xPos ++;
			n++;
		}
		xPos = 1;
		yPos = 2;
		System.out.println("third");
		while(yPos <= GAME_HEIGHT - 1) {
			xInitialSend[n] = xPos;
			yInitialSend[n] = yPos;
			xOccupied.add((int) xPos);
			yOccupied.add((int) yPos);
			System.out.println("x = " + xPos);
			System.out.println("y = " + yPos);
			yPos ++;
			n++;
		}
		xPos = GAME_WIDTH;
		yPos = 2;
		System.out.println("fourth");
		while(yPos <= GAME_HEIGHT - 1) {
			xInitialSend[n] = xPos;
			yInitialSend[n] = yPos;
			xOccupied.add((int) xPos);
			yOccupied.add((int) yPos);
			System.out.println("x = " + xPos);
			System.out.println("y = " + yPos);
			yPos ++;
			n++;
		}
	}
	public byte[] isInBoundaries(byte xPos, byte yPos) {
		byte[] result = new byte[2];
		int n = 0;
		while(n < xOccupied.size()) {
			if((int) xOccupied.get(n) == xPos) {
				if(yOccupied.get(n) == yPos) {
					result[0] = BOUNDARIES;
					result[1] = OUT_OF_BORDERS;
					return result;
				}
			}
			n ++;
		}
		result[0] = -1;
		return result;
	}

	public byte[] getStartBoundaries() {
		byte[] startBoundaries = new byte[xInitialSend.length + yInitialSend.length + 2];
		startBoundaries[0] = BOUNDARIES;
		startBoundaries[1] = OUT_OF_BORDERS;
		int n = 0;
		while (n < xInitialSend.length) {
			startBoundaries[2 * n + 2] = xInitialSend[n];
			startBoundaries[2 * n + 1 + 2] = yInitialSend[n];
			n ++;
		}
		return startBoundaries;
	}
	public byte[] getChangeBoundaries() {
		if(firstDone == false) {
			firstDone = true;
			return getStartBoundaries();
		}
		else {
			byte[]result = {-1};
			return result;
		}
	}
}
