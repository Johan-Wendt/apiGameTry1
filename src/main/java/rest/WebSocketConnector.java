package rest;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class WebSocketConnector {
    public static void main(String[] args) throws Exception {
        Server server = new Server(9014);
        WebSocketHandler wsHandler = new WebSocketHandler() {
            @Override
            public void configure(WebSocketServletFactory factory) {
                factory.register(HumanTouch.class);
            }
        };
        server.setHandler(wsHandler);
        server.start();
        server.join();
        
    }
    
}