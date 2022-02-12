package com.toulzx.stitp_module_user.utils;

import static android.content.Context.MODE_PRIVATE;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一管理存储 Userdata 的 SharedPreferences
 * @date 2021/10/7 15:22
 * @author tou
 */

public class SPHelper {

    private static final String TAG = SPHelper.class.getSimpleName();

    private static Application mApp = null;
    private static SharedPreferences mSharedPreferences = null;

    public static final String SP_NAME = "userSP";

    public static final String KEY_ID = "id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_COUNT = "count";
    public static final String KEY_LOGIN_STATUS = "status";

    public static final String VALUE_DEFAULT_ID = "999";
    public static final String VALUE_DEFAULT_USERNAME = "somebody";
    public static final String VALUE_DEFAULT_PASSWORD = "";
    public static final String VALUE_DEFAULT_COUNT = "888";
    public static final String VALUE_DEFAULT_LOGIN_STATUS = "false";

    public static final String VALUE_DEFAULT_ORIGINAL_STATUS = "NEW";

    public static final String VALUE_WRONG_ID = "-999";
    public static final String VALUE_WRONG_ACCOUNT = "wrong";
    public static final String VALUE_WRONG_PASSWORD = "";
    public static final String VALUE_WRONG_COUNT = "-888";
    public static final String VALUE_WRONG_LOGIN_STATUS = "wrong";


    /**
     * 使用默认数值进行初始化
     * @param app:
     * @return void
     * @date 2021/10/7 10:16
     * @author tou
     */
    public static void init(Application app) {

        mApp = app;

        mSharedPreferences = mApp.getSharedPreferences(SP_NAME, MODE_PRIVATE);

        String originalStatus = mSharedPreferences.getString(KEY_LOGIN_STATUS, VALUE_DEFAULT_ORIGINAL_STATUS);

        if ( originalStatus.equals(VALUE_DEFAULT_ORIGINAL_STATUS) ) {

            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString(KEY_ID, VALUE_DEFAULT_ID);
            editor.putString(KEY_USERNAME, VALUE_DEFAULT_USERNAME);
            editor.putString(KEY_PASSWORD, VALUE_DEFAULT_PASSWORD);
            editor.putString(KEY_COUNT, VALUE_DEFAULT_COUNT);
            editor.putString(KEY_LOGIN_STATUS, VALUE_DEFAULT_LOGIN_STATUS);
            editor.apply();

        }

        Log.i(TAG, "init: \nSharedPreferences 初始化成功！");
        Log.i(TAG, "init: SharedPreferences details: \n" +
                "UserName:\t" + getUserName() + " \n" +
                "Password:\t" + getPassword() + " \n" +
                "Count:\t" + getCount() + " \n" +
                "LoginStatus:\t" + getStatus() + " \n");

    }

    /**
     * 修改 SharedPReferences 中所有的数据，不需要修改的填写 null
     * @param id:
     * @param username:
     * @param password:
     * @param count:
     * @param loginStatus:
     * @return void
     * @date 2021/10/7 10:55
     * @author tou
     */
    public static void setValue(String id, String username, String password,
                                String count, String loginStatus) {

        if (mApp == null) {
            Log.e(TAG, "setValue: mApp == null");
            return;
        }

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (id != null) { editor.putString(KEY_ID, id); }
        if (username != null) { editor.putString(KEY_USERNAME, username); }
        if (password != null) { editor.putString(KEY_PASSWORD, password); }
        if (count != null) { editor.putString(KEY_COUNT, count); }
        if (loginStatus != null) { editor.putString(KEY_LOGIN_STATUS, loginStatus); }
        editor.apply();

        Log.i(TAG, "setValue: 修改成功！");

    }

    /**
     * 获取 SharedPreferences 中所有数据的值，返回 Map
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @date 2021/10/7 13:49
     * @author tou
     */
    public static Map<String,String> getAll() {

        if (mApp == null) {
            Log.e(TAG, "setValue: getAll(): mApp == null");
            return null;
        }

        Map <String,String> map = new HashMap<>();

        map.put(KEY_ID, getId());
        map.put(KEY_USERNAME, getUserName());
        map.put(KEY_PASSWORD, getPassword());
        map.put(KEY_COUNT, getCount());
        map.put(KEY_LOGIN_STATUS, getStatus());

        return map;

    }

    /* 以下方法们用来快速获取 SharedPreferences 中的某个数据 2021/10/7 */

    public static String getId() {

        return mSharedPreferences.getString(KEY_ID, VALUE_WRONG_ID);

    }

    public static String getUserName() {

        return mSharedPreferences.getString(KEY_USERNAME, VALUE_WRONG_ACCOUNT);

    }

    public static String getPassword() {

        return mSharedPreferences.getString(KEY_PASSWORD, VALUE_WRONG_PASSWORD);

    }

    public static String getCount() {

        return mSharedPreferences.getString(KEY_COUNT, VALUE_WRONG_COUNT);

    }

    public static String getStatus() {

        return mSharedPreferences.getString(KEY_LOGIN_STATUS, VALUE_WRONG_LOGIN_STATUS);

    }

}
