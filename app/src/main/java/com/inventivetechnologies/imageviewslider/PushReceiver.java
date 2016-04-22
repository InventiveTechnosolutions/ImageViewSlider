package com.inventivetechnologies.imageviewslider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

/**
 * Created by Shiv on 21-04-2016.
 */
public class PushReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle dataBundle = intent.getBundleExtra("data");
        try {
            Log.i("PushDemo", "Notification content: " + dataBundle.getString("alert"));
            Log.i("PushDemo", "Notification title: " + dataBundle.getString("title"));
            Log.i("PushDemo", "Is Your App Active: " + dataBundle.getBoolean("isActive"));

            JSONObject customJSON = new JSONObject(dataBundle.getString("custom"));
            if (customJSON.has("a"))
                Log.i("PushDemo", "additionalData: " + customJSON.getJSONObject("a").toString());
        } catch (Throwable t) {
            t.printStackTrace();
        }


    }
}
