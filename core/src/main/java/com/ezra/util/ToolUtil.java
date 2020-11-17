package com.ezra.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ToolUtil {


    public static final String EMPTY_STRING = "";


    public static boolean isEmpty(Object val) {
        if (null == val) {
            return true;
        }


        if (val instanceof String) {
            return EMPTY_STRING.equals(val);
        } else if (val instanceof List) {
            return ((List<?>) val).size() != 0;
        }else if(val instanceof Map){
            return ((Map) val).isEmpty();
        }else if (val instanceof Set){
            return ((Set) val).isEmpty();
        }


        return false;
    }
}
