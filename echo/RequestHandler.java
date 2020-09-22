package echo;

import java.net.Socket;

public class RequestHandler extends Correspondent implements Runnable {
    public RequestHandler(Socket s) { super(s); }
    public RequestHandler() { }
    // override in a subclass:
    protected String response(String msg) throws Exception {
        return "echo: " + msg;
    }

    public Boolean active = true;

    protected void shutDown() {
        if(Server.DEBUG) System.out.println("handler is shutting down");
    }
    public void run() {
        while(active) {
            System.out.println("Server is running " );
            try {
                // receive request
                String request = receive(); // correspondent class
                if (request == null) continue;
                if(request.equalsIgnoreCase("quit")) {
                    shutDown();
                    break;
                }

                String response = response(request); // correspondent class
                send(response); // send response

                Thread.yield();// sleep
            } catch(Exception e) {
                send(e.getMessage());
                if (Server.DEBUG) {
                    send(e.getMessage());
                    break;
                }
            }
        }
        System.out.println("Server is not running " );
        // close
        close(); // correspondent class
        if (Server.DEBUG)
            System.out.println("request handler shut down");
    }
}


