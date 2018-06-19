package com.hy.base.androidbase.component.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @version V1.0
 * @Package com.hy.base.androidbase.component.broadcast.receiver
 * @Description: ${todo}
 * @author: huangyuan
 * @date: 2017/11/28 14:10
 * @Copyright: www.***.com Inc. All rights reserved.
 */
public class SortBroadcastReceiver1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("060_Demo:","广播接收者1");
    }
}
