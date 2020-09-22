package echo;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.io.*;
import java.net.*;

public class Server {

    protected ServerSocket mySocket;
    protected int myPort;
    public static boolean DEBUG = true;
    protected Class<?> handlerType;

    public Server(int port, String handlerType) {
        try {
            myPort = port;
            mySocket = new ServerSocket(myPort);
            this.handlerType = (Class.forName(handlerType));
        } catch(Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } // catch
    }


    public void listen() throws IOException {
        Socket socket = null;
        while(true) {
            System.out.println("Server listening at port: " + myPort);
            try {
                // accept a connection
                socket = mySocket.accept();
                // make handler
                RequestHandler handler = makeHandler(socket);
                // start handler in its own thread
                Thread thread = new Thread(handler);
                thread.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } // while
    }

    public RequestHandler makeHandler(Socket s) {
        // handler = a new instance of handlerType
        RequestHandler handler = null;
        try {
            handler = (RequestHandler) handlerType.getDeclaredConstructor().newInstance();
            // set handler's socket to s
            handler.setSocket(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // return handler
        return handler;
    }



    public static void main(String[] args) throws IOException {
        int port = 5555;
        String service = "echo.RequestHandler";
        if (1 <= args.length) {
            service = args[0];
        }
        if (2 <= args.length) {
            port = Integer.parseInt(args[1]);
        }
        Server server = new Server(port, service);
        server.listen();
    }
}
