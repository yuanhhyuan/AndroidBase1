package com.hy.base.androidbase.component.service;


import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;

import com.hy.app.activity.MainActivity;
import com.hy.app.component.R;


/**
 * 前台服务
 */

public class MyForegroundService extends Service {

    String tag = "060_MyForegroundService";

    /**
     * 重写Service生命周期
     */
    @Override
    public IBinder onBind(Intent arg0) {
        Log.e(tag, "MyForegroundService onBind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(tag, "MyForegroundService onCreate");

        //android 3.0后创建notification的方式
        Notification.Builder builder = new Notification.Builder(this.getApplicationContext());
        Intent nfIntent = new Intent(this, MainActivity.class);
        builder.setContentIntent(PendingIntent.getActivity(this, 0, nfIntent, 0))
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher))
                .setContentTitle("来自前台服务提示Title")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("来自前台服务提示要显示的内容")
                .setWhen(System.currentTimeMillis());
        Notification notification = builder.build();
        //设置为默认的声音
        notification.defaults = Notification.DEFAULT_SOUND;

        startForeground(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(tag,"MyForegroundService onStartCommand invoke");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(tag, "MyForegroundService onDestroy");

    }


}

/**
 * 前台服务
 * https://www.jianshu.com/p/5505390503fa
 */
