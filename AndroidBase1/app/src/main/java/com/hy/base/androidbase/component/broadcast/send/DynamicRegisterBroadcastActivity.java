package com.hy.base.androidbase.component.broadcast.send;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hy.app.component.R;
import com.hy.base.androidbase.component.broadcast.receiver.ReceiverBR;


/**
发送自定义的动态广播
 */
public class DynamicRegisterBroadcastActivity extends Activity {

    public static final String NEW_LIFEFORM_DETECTED = "com.broadcast.set6666.dynamicbroadcast";
    protected ReceiverBR receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamicregbr);
        Button btn0 = (Button) findViewById(R.id.btn0);
        btn0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //发送广播
                Intent it = new Intent(NEW_LIFEFORM_DETECTED);
                sendBroadcast(it);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //注册广播
        IntentFilter counterActionFilter = new IntentFilter(NEW_LIFEFORM_DETECTED);
        receiver = new ReceiverBR();
        registerReceiver(receiver, counterActionFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //取消广播，否则内存泄漏
        unregisterReceiver(receiver);
    }
}
