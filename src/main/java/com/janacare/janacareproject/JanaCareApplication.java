package com.janacare.janacareproject;

import android.content.Context;

/**
 * Created by pradeep on 17/1/16.
 */
public class JanaCareApplication {

    private static JanaCareApplication application = null;
    private static JanaCareApplication instance;
    private static Context mContext;

    public static JanaCareApplication getInstance() {
        if (instance == null) {
            instance = new JanaCareApplication();
        }
        return instance;
    }

    public static Context getContext() {
        return mContext;
    }
}
