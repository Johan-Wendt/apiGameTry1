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
	private ArrayList<Player> players = new ArrayList<>();

	@OnWebSocketClose
	public void onClose(int statusCode, String reason) {
		System.out.println("Close: statusCode=" + statusCode + ", reason=" + reason);
	}

	@OnWebSocketError
	public void onError(Throwable t) {
		System.out.println("Error: " + t.getMessage());
	}

	@OnWebSocketConnect
	public void onConnect(Session session) {
		System.out.println("Connect: ");
		this.session = session;
		System.out.println("Connect: " + session.getRemoteAddress().getAddress());
		remote = session.getRemote();
		players.add(new Player());
		gameLoop = new GameLoop(this, new Player());
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
		System.out.println("real 0 = " + realResult[0]);
		System.out.println("real 1 = " + realResult[1]);
		System.out.println("real 2 = " + realResult[2]);
		Player player = players.get(realResult[0] - 1);

		if (realResult[1] == 1) {
			switch (realResult[2]) {
			case moveUp:
				player.setCurrentDirection(moveUp);
				break;
			case moveRight:
				player.setCurrentDirection(moveRight);
				break;
			case moveLeft:
				player.setCurrentDirection(moveLeft);
				break;
			case moveDown:
				player.setCurrentDirection(moveDown);
				break;
			}
		}
		System.out.println(player.getCurrentDirection());

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