package com.tysci.pmi.utils;

import android.content.Context;

import com.tysci.applibrary.utils.SharedPreferencesUtil;

/**
 * Created by Administrator on 2015/12/25.
 */
public class UserInfoUtil {
    private static final String fileName="user_info";

    private static final String ACCOUNT_EMAIL_KEY="account_email_key";
    private static final String ACCOUNT_NAME_KEY="account_name_key";
    private static final String ACCOUNT_PASSWORD_KEY="account_password_key";
    private static final String ACCOUNT_TOKEN="account_token";

    public static void setUserEmail(Context context,String email){
        SharedPreferencesUtil.setStringValue(context,fileName,ACCOUNT_EMAIL_KEY,email);
    }

    public static String getUserEmail(Context context){
        return SharedPreferencesUtil.getStringValue(context,fileName,ACCOUNT_EMAIL_KEY);
    }

    public static void setUserName(Context context,String name){
        SharedPreferencesUtil.setStringValue(context,fileName,ACCOUNT_NAME_KEY,name);
    }

    public static String getUserName(Context context){
        return SharedPreferencesUtil.getStringValue(context,fileName,ACCOUNT_NAME_KEY);
    }

    public static void setUserPassword(Context context,String password){
        SharedPreferencesUtil.setStringValue(context,fileName,ACCOUNT_PASSWORD_KEY,password);
    }

    public static String getUserPassword(Context context){
        return SharedPreferencesUtil.getStringValue(context,fileName,ACCOUNT_PASSWORD_KEY);
    }

    public static void setUserToken(Context context,String token){
        SharedPreferencesUtil.setStringValue(context,fileName,ACCOUNT_TOKEN,token);
    }

    public static String getUserToken(Context context){
        return SharedPreferencesUtil.getStringValue(context,fileName,ACCOUNT_TOKEN);
    }

}
