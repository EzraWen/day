package com.ezra.config;

import org.springframework.stereotype.Component;

@Component
public class DBHelper {
     /** 
     * 线程独立 
     */  
    private ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
    
    public static final String DB_TYPE_NEW = "newdb";
    public static final String DB_TYPE_HISTORY = "historydb";

    public String getDBType() {
        String db = contextHolder.get();  
        if (db == null) {
            db = DB_TYPE_NEW;
            // 默认是新库
        }
        return db;  
    }
  
    public void setDBType(String str) {
        contextHolder.set(str);
    }
   
    public void clearDBType() {  
        contextHolder.remove();  
    } 
}