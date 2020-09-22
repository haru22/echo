package echo;

import java.net.Socket;

public class CacheHandler extends ProxyHandler {
    public static Cache cache = new Cache();

    public CacheHandler(Socket s) { super(s); }
    public CacheHandler(){super();}

    // override in a subclass:
    protected String response(String request) throws Exception {
        String result = cache.search(request);

        if (result != null) {
            return result + " (No activity in Math handler) ";
        } else {
            result = super.response(request);
            cache.update(request,result);

        }
        return result;
    }
}

