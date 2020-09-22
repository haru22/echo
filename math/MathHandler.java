package math;

import com.sun.codemodel.internal.JCatchBlock;
import com.sun.org.apache.bcel.internal.generic.FSUB;
import echo.*;
import javafx.util.Pair;

import java.net.Socket;

public class MathHandler extends RequestHandler {


    public MathHandler(Socket s) { super(s); }
    public MathHandler() { super(); }


    // override in a subclass:
    protected String response(String msg) throws Exception {
        String[] arrOfMsg = msg.split(" ", 0);
        String result = null;
        try {
            switch (arrOfMsg[0]) {
                case "add":
                    result = add(arrOfMsg);
                    break;
                case "mul":
                    result = mul(arrOfMsg);
                    break;
                case "sub":
                    result = sub(arrOfMsg);
                    break;
                case "div":
                    result = div(arrOfMsg);
                    break;
                default:
                    result = "wrong command";
                    break;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return result;
    }

    public String add(String[] msg) {
        double result = Double.parseDouble(msg[1]);
        for (int i = 2; i < msg.length; i++) {
            try {
                result += Double.parseDouble(msg[i]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        String strResult = String.valueOf(result);
        return strResult;
    }

    public String sub(String[] msg) {
        double result = Double.parseDouble(msg[1]);
        for (int i = 2; i < msg.length; i++) {
            try {
                result = result - Double.parseDouble(msg[i]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        String strResult = String.valueOf(result);
        return strResult;
    }

    public String mul(String[] msg) {
        double result = Double.parseDouble(msg[1]);
        for (int i = 2; i < msg.length; i++) {
            try {
                result = result * Double.parseDouble(msg[i]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        String strResult = String.valueOf(result);
        return strResult;
    }

    public String div(String[] msg) {
        double result = Double.parseDouble(msg[1]);
        for (int i = 2; i < msg.length; i++) {
            try {
                if (Double.parseDouble(msg[i]) == 0) throw new IllegalArgumentException("Error Argument divisor zero");
                result = result / Double.parseDouble(msg[i]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        String strResult = String.valueOf(result);
        return strResult;
    }



}
