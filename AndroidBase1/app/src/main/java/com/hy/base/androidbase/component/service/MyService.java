package com.hy.base.androidbase.component.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;


/**
 * 该service作为全局的service，在Application启动。
 * 用来监听亮灭屏广播、充电状态广播、电源键
 */

public class MyService extends Service {

    String tag = "060_MyService";

    private LocalBinder binder = new LocalBinder();

    /**
     * 创建Binder对象，返回给客户端即Activity使用，提供数据交换的接口
     */
    public class LocalBinder extends Binder {
        // 声明一个方法，getService。（提供给客户端调用）
        MyService getService() {
            // 返回当前对象LocalService,这样我们就可在客户端端调用Service的公共方法了
            return MyService.this;
        }
    }

    /**
     * 把Binder类返回给客户端
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(tag, "MyService onBind");
        return binder;
    }

    /**
     * 重写Service生命周期
     */
    int count = 0;
    Timer timer = new Timer();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(tag, "MyService onCreate");

        /* 注册屏幕唤醒时的广播 */
        IntentFilter mScreenOnFilter = new IntentFilter("android.intent.action.SCREEN_ON");
        registerReceiver(mScreenOReceiver, mScreenOnFilter);


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                count++;
                Log.e(tag,"count ++ : " + (count));
            }
        }, 100,1000);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(tag,"MyService onStartCommand invoke");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(tag, "MyService onDestroy ，count is ：" + count);
        unregisterReceiver(mScreenOReceiver);
    }





    /**
     * 公共方法
     * @return
     */
    public int getCount(){
        return 10;
    }

    /**
     * 锁屏的管理类叫KeyguardManager，
     * 通过调用其内部类KeyguardLockmKeyguardLock的对象的disableKeyguard方法可以取消系统锁屏，
     * newKeyguardLock的参数用于标识是谁隐藏了系统锁屏
     */
    private BroadcastReceiver mScreenOReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals("android.intent.action.SCREEN_ON")) {
                Log.e(tag, "—— SCREEN_ON ——");
            } else if (action.equals("android.intent.action.SCREEN_OFF")) {
                Log.e(tag, "—— SCREEN_OFF ——");
            }
        }
    };



}
