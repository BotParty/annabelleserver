package org.botparty.annabelle;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.botparty.annabelle.domain.CommunicationData;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Collection;

/**
 * Created by luke on 1/8/2017.
 */
public class ChatServer extends WebSocketServer {

    private static ChatServer instance = null;

    static ChatServer getInstance() {
        final int port = 8080;
        if(instance == null) {
            try {
                instance = new ChatServer(port);
                instance.start();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }

        return instance;
    }

    private ChatServer(int port) throws UnknownHostException {
        super( new InetSocketAddress(port));
    }

    @Override
    public void onOpen( WebSocket conn, ClientHandshake handshake ) {
      //  this.sendToAll( "new connection: " + handshake.getResourceDescriptor() );
        System.out.println( conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!" );
    }

    @Override
    public void onClose( WebSocket conn, int code, String reason, boolean remote ) {
       // this.sendToAll( conn + " has left the room!" );
        System.out.println( conn + " has left the room!" );
    }

    @Override
    public void onMessage( WebSocket conn, String message ) {
      //  this.sendToAll( message );

        ObjectMapper mapper = new ObjectMapper();
        try {
            CommunicationData communicationData = mapper.readValue(message,CommunicationData.class);
            if(!"server".equals(communicationData.getSender())) {
                // forward message onto correct recipient
                communicationData.setSender("server");
                String newText = mapper.writeValueAsString(communicationData);
                this.sendToAll(newText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( conn + ": " + message );
    }

    @Override
    public void onError( WebSocket conn, Exception ex ) {
        ex.printStackTrace();
        if( conn != null ) {
            // some errors like port binding failed may not be assignable to a specific websocket
        }
    }

    /**
     * Sends <var>text</var> to all currently connected WebSocket clients.
     *
     * @param text
     *            The String to send across the network.
     * @throws InterruptedException
     *             When socket related I/O errors occur.
     */
    private void sendToAll(String text) {
        System.out.println("Sending text - " + text);
        Collection<WebSocket> con = connections();
        synchronized ( con ) {
            for( WebSocket c : con ) {
                c.send( text );
            }
        }
    }
}
