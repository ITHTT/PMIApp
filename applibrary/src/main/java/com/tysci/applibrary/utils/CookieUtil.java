package com.tysci.applibrary.utils;

import android.content.Context;

/**
 * Created by Administrator on 2015/12/25.
 */
public class CookieUtil {
    private Context context;

    private static String cookieKey="cookie";

    private static CookieUtil cookieUtil=null;

    public CookieUtil(Context context){
        this.context=context.getApplicationContext();
    }

    public static void initCookieUtil(Context context){
        cookieUtil=new CookieUtil(context);
    }

    public static CookieUtil getCookieUtil(){
        if(cookieUtil==null){
            throw new RuntimeException("cookieUtil is'nt inited");
        }
        return cookieUtil;
    }

    public void saveCookie(String cookie){
        SharedPreferencesUtil.setStringValue(context,cookieKey,cookie);
    }

    public String getCookie(){
        return SharedPreferencesUtil.getStringValue(context,cookieKey);
    }

    public void clearCookie(){
        SharedPreferencesUtil.setStringValue(context,cookieKey,"");
    }


}
