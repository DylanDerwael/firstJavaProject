package Services;

import java.awt.*;

/**
 * Created by dylan on 13.01.15.
 */
public interface Validator {

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
            if (d > 0 && d <= 120){
                return true;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        return false;
    }

    public static boolean isNotNull(String str){
        if(str.length() > 0){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isColor(Object c){
        if(c instanceof Color){
            return true;
        } else {
            return false;
        }
    }


}
