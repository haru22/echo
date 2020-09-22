package echo;

import java.util.Collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;



// thread safe

public class Cache extends HashMap<String, String> {
    public synchronized String search(String request) {
        return get(request);
    }
    public synchronized String update(String request, String response) {
        return put(request,response);
    }


}
