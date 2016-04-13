package pers.lwm.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lwm on 2016/4/13.
 */
public class StringUtil {

    public static double getNumFromString(String str) {
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return Double.valueOf(m.replaceAll("").trim());
    }
}
