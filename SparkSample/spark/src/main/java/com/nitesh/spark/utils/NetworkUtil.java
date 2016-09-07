package com.nitesh.spark.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Keep;

@Keep
public class NetworkUtil {

    public static final int WIFI_NETWORK = 1;
    public static final int MOBILE_NETWORK = 2;
    public static final int NOT_CONNECTED = 0;


    static int getConnectivityStatus(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {

            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI && activeNetwork.isConnected()){
                return WIFI_NETWORK;
            }
            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE && activeNetwork.isConnected())
                return MOBILE_NETWORK;
        }
        return NOT_CONNECTED;
    }

    public static boolean isConnected(Context context) {
        int conn = NetworkUtil.getConnectivityStatus(context);
        if (conn == NetworkUtil.NOT_CONNECTED) {
            return false;
        }
        return true;
    }
}