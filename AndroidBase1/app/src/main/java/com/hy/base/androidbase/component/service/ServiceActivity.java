package com.hy.base.androidbase.component.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hy.app.component.R;
import com.hy.app.config.MyApplication;


public class ServiceActivity extends AppCompatActivity {
    String tag = "060_ServiceActivity";

    Button mstartforeground;
    Button mstopforeground;
    Button mstart;
    Button mstop;
    Button mbind;
    Button munbind;
    Button mgetdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        initView();  //初始化view
        initListener();  //初始化多个监听事件

        newServiceConnection();
    }

    private void initView() {
        mstartforeground = (Button) findViewById(R.id.mstartforeground);
        mstopforeground = (Button) findViewById(R.id.mstopforeground);
        mstart = (Button) findViewById(R.id.mstart);
        mstop = (Button) findViewById(R.id.mstop);
        mbind = (Button) findViewById(R.id.mbind);
        munbind = (Button) findViewById(R.id.munbind);
        mgetdata = (Button) findViewById(R.id.mgetdata);
    }

    private void initListener() {
        mstartforeground.setOnClickListener(new MyListener());
        mstopforeground.setOnClickListener(new MyListener());
        mstart.setOnClickListener(new MyListener());
        mstop.setOnClickListener(new MyListener());
        mbind.setOnClickListener(new MyListener());
        munbind.setOnClickListener(new MyListener());
        mgetdata.setOnClickListener(new MyListener());

    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {  //同时监听多个事件
            switch (v.getId()) {
                case R.id.mstartforeground:
                    startforeground();
                    break;
                case R.id.mstopforeground:
                    stopforeground();
                    break;
                case R.id.mstart:
                    startservice();
                    break;
                case R.id.mstop:
                    stopservice();
                    break;
                case R.id.mbind:
                    bindservice();
                    break;
                case R.id.munbind:
                    unbindservice();
                    break;
                case R.id.mgetdata:
                    getdata();
                    break;
                default:
                    break;
            }
        }
    }

    final Intent intent = new Intent(MyApplication.getContext(), MyForegroundService.class);
    private void startforeground() {
        //0,开启前台服务,1,关闭前台服务
        intent.putExtra("cmd", 0);
        startService(intent);
    }
    private void stopforeground() {
        //0,开启前台服务,1,关闭前台服务
        intent.putExtra("cmd", 1);
        startService(intent);
    }

    Intent it = new Intent(MyApplication.getContext(), MyService.class);
    private void startservice() {
        startService(it);
    }
    private void stopservice() {
        stopService(it);
    }

    private void bindservice() {
        Log.e(tag, "绑定调用：bindservice");
        bindService(it, conn, Service.BIND_AUTO_CREATE);
    }
    private void unbindservice() {
        Log.e(tag, "解除绑定调用：unbindService");
        // 解除绑定
        if(mService!=null) {
            mService = null;
            unbindService(conn);
        }
    }
    private void getdata() {
        Log.e(tag, "获取数据：getdata");
        if (mService != null) {
            // 通过绑定服务传递的Binder对象，获取Service暴露出来的数据

            Log.e(tag, "从服务端获取数据：" + mService.getCount());
        } else {

            Log.e(tag, "还没绑定呢，先绑定,无法从服务端获取数据");
        }
    }

    /**
     * ServiceConnection代表与服务的连接，它只有两个方法，
     * onServiceConnected和onServiceDisconnected，
     * 前者是在操作者在连接一个服务成功时被调用，而后者是在服务崩溃或被杀死导致的连接中断时被调用
     */
    private ServiceConnection conn;
    private MyService mService;
    private void newServiceConnection() {
        conn = new ServiceConnection() {
            /**
             * 与服务器端交互的接口方法 绑定服务的时候被回调，在这个方法获取绑定Service传递过来的IBinder对象，
             * 通过这个IBinder对象，实现宿主和Service的交互。
             */
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e(tag, "绑定成功调用：onServiceConnected");
                // 获取Binder
                MyService.LocalBinder binder = (MyService.LocalBinder) service;
                mService = binder.getService();
            }

            /**
             * 当取消绑定的时候被回调。但正常情况下是不被调用的，它的调用时机是当Service服务被意外销毁时，
             * 例如内存的资源不足时这个方法才被自动调用。
             */
            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e(tag, "：onServiceDisconnected");
                mService = null;
            }
        };
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
