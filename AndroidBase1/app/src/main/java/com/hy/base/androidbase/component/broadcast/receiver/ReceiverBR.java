package com.hy.base.androidbase.component.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**

 */
public class ReceiverBR extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("060_Intent_Action:",intent.getAction()+"");
    }
}
