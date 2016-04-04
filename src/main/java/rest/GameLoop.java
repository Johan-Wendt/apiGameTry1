package rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import com.google.common.primitives.Bytes;

import sun.security.x509.IssuingDistributionPointExtension;

import org.eclipse.jetty.websocket.api.RemoteEndpoint;

public class GameLoop implements Constants {
	private MyWebSocketHandler socket;
	private Player player;
	private boolean running = true;
	private boolean paused = false;
	private int fps = 60;
	private int frameCount = 0;
	private BonusController bonusController;
	private GamePlan gamePlan;

	public GameLoop(MyWebSocketHandler socket, Player player, GamePlan gamePlan, BonusController bonusController) {
		this.socket = socket;
		this.player = player;
		this.gamePlan = gamePlan;
		this.bonusController = bonusController;
	}

	public void runGameLoop() {
		Thread loop = new Thread() {
			public void run() {
				gameLoop();
			}
		};
		loop.start();

	}

	private void gameLoop() {
		// This value would probably be stored elsewhere.
		final double GAME_HERTZ = 30.0;
		// Calculate how many ns each frame should take for our target game
		// hertz.
		final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
		// At the very most we will update the game this many times before a new
		// render.
		// If you're worried about visual hitches more than perfect timing, set
		// this to 1.
		final int MAX_UPDATES_BEFORE_RENDER = 5;
		// We will need the last update time.
		double lastUpdateTime = System.nanoTime();
		// Store the last time we rendered.
		double lastRenderTime = System.nanoTime();

		// If we are able to get as high as this FPS, don't render again.
		final double TARGET_FPS = 60;
		final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;

		// Simple way of finding FPS.
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);

		while (running) {
			double now = System.nanoTime();
			int updateCount = 0;
			if (paused) {
				System.out.print("");
			}

			if (!paused) {
				// System.out.println("also running");
				// Do as many game updates as we need to, potentially playing
				// catchup.
				while (now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER) {
					updateGame();
					lastUpdateTime += TIME_BETWEEN_UPDATES;
					updateCount++;
				}

				// If for some reason an update takes forever, we don't want to
				// do an insane number of catchups.
				// If you were doing some sort of game that needed to keep EXACT
				// time, you would get rid of this.
				if (now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
					lastUpdateTime = now - TIME_BETWEEN_UPDATES;
				}

				// Render. To do so, we need to calculate interpolation for a
				// smooth render.
				// float interpolation = Math.min(1.0f, (float) ((now -
				// lastUpdateTime) / TIME_BETWEEN_UPDATES));
				// drawGame(interpolation);
				sendPositions();
				lastRenderTime = now;

				// Update the frames we got.
				int thisSecond = (int) (lastUpdateTime / 1000000000);
				if (thisSecond > lastSecondTime) {
					System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
					fps = frameCount;
					frameCount = 0;
					lastSecondTime = thisSecond;
				}

				// Yield until it has been at least the target time between
				// renders. This saves the CPU from hogging.
				while (now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS
						&& now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
					Thread.yield();

					// This stops the app from consuming all your CPU. It makes
					// this slightly less accurate, but is worth it.
					// You can remove this line and it will still work (better),
					// your CPU just climbs on certain OSes.
					// FYI on some OS's this can cause pretty bad stuttering.
					// Scroll down and have a look at different peoples'
					// solutions to this.
					try {
						Thread.sleep(1);
					} catch (Exception e) {
					}

					now = System.nanoTime();
				}
			}
		}
	}

	public void updateGame() {
		player.move();
		bonusController.BonusRound();
	}

	public void sendPositions() {
		// ByteBuffer buf = ByteBuffer.wrap(new byte[]
		// {player.getPlayerNumber(), player.getxPos(), player.getyPos()});
		ByteBuffer buf = ByteBuffer.wrap(buildPostitions());
		socket.updatePlayer(buf);
	}
/**
	public void sendStartGamePlan() {
		byte[] objectInfo = { GAME_BOARD, OUT_OF_BORDERS };
		byte[] concatenater = { -1 };
		// byte[] gameSize = Bytes.concat(objectInfo, concatenater);
	    gamePlan.getStartBoundaries();
		byte[] gameSize = Bytes.concat(objectInfo, gamePlan.getStartBoundaries(), concatenater);
		ByteBuffer buf = ByteBuffer.wrap(gameSize);
	//	socket.updatePlayer(buf);
	}
**/
	public byte[] buildPostitions() {
		byte[] concatenater = { -1 };
		byte[] playerPositions = player.getAllPositions(); // bör skötas av en
															// PlayerController
															// som ger alla
															// relevanta
															// positioner
		byte[] bonusPositions = bonusController.getAllPositions();
		
		byte[] gamePlanChanges = gamePlan.getChangeBoundaries();

		byte[] result = Bytes.concat(playerPositions, concatenater, bonusPositions, concatenater, gamePlanChanges, concatenater);

		return result;
	}

	public void pause() {

		if (paused == true) {
			paused = false;
		} else {
			paused = true;
		}
	}

}
