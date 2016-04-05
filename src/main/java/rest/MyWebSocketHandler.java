package rest;

import java.io.IOException;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class MyWebSocketHandler implements Constants {
	private Session session;
	private RemoteEndpoint remote;
	private GameLoop gameLoop;
	private GamePlan gamePlan = new GamePlan();
	private BonusController bonusController = new BonusController(gamePlan);
	private MasterController masterController = new MasterController(gamePlan, bonusController, gameLoop);

	@OnWebSocketClose
	public void onClose(int statusCode, String reason) {
		System.out.println("Close: statusCode=" + statusCode + ", reason=" + reason);
	}

	@OnWebSocketError
	public void onError(Throwable t) {
		System.out.println("Error: " + t.getMessage());
	}

	@OnWebSocketConnect
	public void onConnect(Session sessions) {
		System.out.println("Connect: ");
		session = sessions;
		System.out.println("Connect: " + session.getRemoteAddress().getAddress());
		remote = session.getRemote();
		gameLoop = new GameLoop(this, masterController);
	    gameLoop.runGameLoop();
	}

	/**
	 * @OnWebSocketMessage public void onMessage(String message) {
	 *                     System.out.println("Message: " + message); }
	 **/
	@OnWebSocketMessage
	public void onMessage(Reader reader) throws IOException {
		char[] bpa = new char[3];
		reader.read(bpa, 0, 3);
		int[] realResult = numberify(bpa);

		masterController.handleInput(realResult);
	}

	public static int[] numberify(char[] arr) {
		int[] result = new int[arr.length];
		int n = 0;
		while (n < arr.length) {
			result[n] = java.lang.Character.getNumericValue(arr[n]);
			n++;
		}
		return result;
	}

	public void updatePlayer(ByteBuffer buf) {
		try {
			remote.sendBytes(buf);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}

	}

}