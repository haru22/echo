package echo;

import java.util.HashMap;

// thread safe
public class UserTable extends HashMap<String,String> {
    public synchronized String getPassword(String user) {
        return get(user);
    }
    public boolean checkUsername(String user) {
        if (get(user) == null) return true;
        else return false;
    }

    public synchronized void newUser(String user, String password) {
        this.put(user,password);
    }
}
