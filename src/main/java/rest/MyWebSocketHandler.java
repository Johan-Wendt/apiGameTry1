package rest;

import java.io.IOException;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class MyWebSocketHandler {
	private Session session;
	private GameLoop gameLoop;
	//RemoteEndpoint remote = session.getRemote();

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
       // gameLoop = new GameLoop(session.getRemote(), new Player());
       // gameLoop.runGameLoop();

    }
/**
    @OnWebSocketMessage
    public void onMessage(String message) {
        System.out.println("Message: " + message);
    }
    **/
    @OnWebSocketMessage
    public void onMessage(Reader reader) throws IOException {
    	char[] bpa = new char[3]; 
    	int apa = reader.read(bpa, 0, 3);
        int test = numberify(bpa);
        System.out.println("Sent: " + test);
    }
    
    private int numberify(char[] numChar) {
    	;
    	int current = numChar.length;
    	int result = 0;
    	int mult = 1;
    	while(current > 0) {
    		System.out.println("current " + numChar[current - 1]);
    		result += numChar[current - 1] * mult;
    		System.out.println("reslut " + result);
    		current --;
    		mult *= 10;

    	}
    	
    	return result;
    }
    public void sendStats() {
    	
    }
    
}