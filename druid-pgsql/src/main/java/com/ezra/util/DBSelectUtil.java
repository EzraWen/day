package com.ezra.util;

import com.ezra.config.DBHelper;

public class DBSelectUtil {


    private static final  String RULE_ID = "ID";
    private static final  String RULE_DATE = "DATE";

    public static String getById(Object value) {
        if (value instanceof Long) {
            return (Long) value % 2 == 0 ? DBHelper.DB_TYPE_NEW : DBHelper.DB_TYPE_HISTORY;
        } else if (value instanceof Integer) {
            return (Integer) value % 2 == 0 ? DBHelper.DB_TYPE_NEW : DBHelper.DB_TYPE_HISTORY;
        }
        return DBHelper.DB_TYPE_NEW;
    }



    public static String getByRule(String source,Object value){
        switch (source){
            case RULE_ID:
                return getById(value);
            case RULE_DATE:
                return getByDate(value);
            default:
                return DBHelper.DB_TYPE_NEW;
        }
    }

    /**
     * 日期判断
     * @param value
     * @return
     */
    private static String getByDate(Object value) {
        if (value instanceof Long) {
            return (Long) value > 100L? DBHelper.DB_TYPE_NEW : DBHelper.DB_TYPE_HISTORY;
        } else if (value instanceof Integer) {
            return (Integer) value >100 ? DBHelper.DB_TYPE_NEW : DBHelper.DB_TYPE_HISTORY;
        }
        return DBHelper.DB_TYPE_NEW;
    }
}
