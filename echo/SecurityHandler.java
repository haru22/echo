package echo;

import java.net.Socket;

public class SecurityHandler extends ProxyHandler{
    // username and password require
    private static UserTable users = new UserTable();
    private Boolean loggedIn = false;

    public SecurityHandler(Socket s) { super(s); }
    public SecurityHandler() { super(); }

    // override in a subclass:
    protected String response(String request) throws Exception {
        // if request = "new user password" call users.newUser
        // and terminate session Thread.stop
        String[] arrOfRequest = request.split(" ", 0);
        String username = arrOfRequest[1];
        String password = arrOfRequest[2];

        if (!loggedIn) {
            if (arrOfRequest[0].equalsIgnoreCase("new")) {
                if (users.checkUsername(username)) { // username is unique
                    users.newUser(username,password);
                    active = false; // terminate thread
                    return "Ok, session ended";
                }
                else { // user already exist
                    active = false; // terminate thread
                    return "this username already exist. session ended";
                }
            }
            // if request = "login user password" if legit loggedin = true, else stop
            else if (arrOfRequest[0].equalsIgnoreCase("login")) {

                if (password.equals(users.getPassword(username))) {
                    loggedIn = true;
                    return "Login success";
                }
                else {
                    return "user/password is wrong";
                }
            }
            else return "please login";
        }
        else if (loggedIn){
             return super.response(request); // else if loggedIn return super.response(request)
        }

        return "error command";
    }

    /* testing
    java echo.Server math.MathHandler
    java echo.ProxyServer echo.CacheHandler 5555 6666
    java echo.ProxyServer echo.SecurityHandler 6666 7777
    java echo.SimpleClient 7777

     */


    /*
    * 1. If the user is not logged in, the only commands that can be executed are new and login,
    * otherwise tell the user to login.  If the user is logged in, then login or new can't be executed.
2. User can't create a new login with the same user name.
3. After successfully creating a new user account, the user must successfully log in
* before he/she can execute any math commands. But the session does not have to terminate.
    * */
}

