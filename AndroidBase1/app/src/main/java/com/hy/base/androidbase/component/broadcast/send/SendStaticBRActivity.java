package com.hy.base.androidbase.component.broadcast.send;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hy.app.component.R;


/**
发送自定义的静态广播
 */
public class SendStaticBRActivity extends Activity {

    Button btn1;
    Button btn2;
    Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendbr);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);

        //使用静态的方式注册广播，无序广播（一个receiver）
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //使用静态的方式注册广播，可以使用显示意图进行发送广播
                Intent broadcast = new Intent("com.broadcast.set5555.staticregisterbroadcast");
                sendBroadcast(broadcast,null);
            }
        });

        //使用静态的方式注册广播，无序广播（两个receiver）
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent broadcast = new Intent("com.broadcast.disorder.staticregisterbroadcast");
                sendBroadcast(broadcast,null);
            }
        });

        //使用静态的方式注册广播，有序广播（两个receiver）
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent broadcast = new Intent("com.broadcast.order.staticregisterbroadcast");
                sendOrderedBroadcast(broadcast,null);
            }
        });

    }

}
