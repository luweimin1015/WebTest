package pers.lwm.base.util;

/**
 * Created by lwm on 2016/3/25.
 */
public class StringUtil {
    
    public static boolean isNullOrEmpty(Object str) {
        return str == null || "".equals(str);
    }
}
