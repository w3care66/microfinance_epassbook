package com.samraddhbestwin.microfinance.Session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


import com.samraddhbestwin.microfinance.SplashActivity;

import java.util.HashMap;
import java.util.List;

public class SessionManager {
    SharedPreferences pref;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "MICROFINANCE";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_member_id = "member_id";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_pin = "pin";




    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void createLoginSession(String member_id, String pin){
        editor.putString(KEY_member_id, member_id);
        editor.putString(KEY_pin, pin);
         editor.commit();
    }
    public void TokenSession(String token){
        editor.putString(KEY_TOKEN, token);

        editor.commit();
    }
    public void checkLogin(){
        if(!this.isLoggedIn()){
//            Intent i = new Intent(_context, LoginActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            _context.startActivity(i);
        }
    }
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_member_id, pref.getString(KEY_member_id, null));
        user.put(KEY_pin, pref.getString(KEY_pin, null));
        return user;
    }
    public HashMap<String, String> getUserDetailsToken(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_TOKEN, pref.getString(KEY_TOKEN, null));
        return user;
    }
    public void logoutUser(){
        editor.putString(KEY_pin, null);
        editor.commit();
//        editor.clear();
//        editor.commit();
        Intent i = new Intent(_context, SplashActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }
    public void logoutToken(){
        editor.putString(KEY_TOKEN, null);
        editor.commit();
//        editor.clear();
//        editor.commit();
//        Intent i = new Intent(_context, SplashActivity.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        _context.startActivity(i);
    }
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }


}