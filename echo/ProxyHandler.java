package echo;

import echo.*;

import java.net.Socket;

public class ProxyHandler extends RequestHandler {

    protected Correspondent peer;

    public ProxyHandler(Socket s) { super(s); }
    public ProxyHandler() { super(); }

    public void initPeer(String host, int port) {
        peer = new Correspondent();
        peer.requestConnection(host,port);
    }

    protected void shutDown() { peer.send("quit"); }
    // override in a subclass:
    protected String response(String msg) throws Exception {
        // msg comes from client so forward msg to peer
        this.peer.send(msg);
        // return peer's response
        return this.peer.receive();
    }
}
