package com.hy.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hy.app.component.R;
import com.hy.base.androidbase.component.activity.ExampleActivity;
import com.hy.base.androidbase.component.broadcast.send.DynamicRegisterBroadcastActivity;
import com.hy.base.androidbase.component.broadcast.send.SendStaticBRActivity;
import com.hy.base.androidbase.component.contentprovider.ContactsActivity;
import com.hy.base.androidbase.component.contentprovider.MyProviderActivity;
import com.hy.base.androidbase.component.service.ServiceActivity;



public class MainActivity extends Activity {
    Button mactivity1;

    Button mcontacts;
    Button mmyprovider;

    Button jumptosendstaticregisterbr;
    Button jumptosenddynamicregisterbr;

    Button jumptoservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_androidbase);


        initView();  //初始化view
        initListener();  //初始化多个监听事件
    }

    private void initView(){
        mactivity1 = (Button) findViewById(R.id.mactivity1);

        mcontacts = (Button) findViewById(R.id.jumptocontacts);
        mmyprovider = (Button) findViewById(R.id.mmyprovider);

        jumptosendstaticregisterbr = (Button) findViewById(R.id.jumptosendstaticregisterbr);
        jumptosenddynamicregisterbr = (Button) findViewById(R.id.jumptosenddynamicregisterbr);

        jumptoservice = (Button) findViewById(R.id.jumptoservice);
    }

    private void initListener(){
        mactivity1.setOnClickListener(new MyListener());

        mcontacts.setOnClickListener(new MyListener());
        mmyprovider.setOnClickListener(new MyListener());

        jumptosendstaticregisterbr.setOnClickListener(new MyListener());
        jumptosenddynamicregisterbr.setOnClickListener(new MyListener());

        jumptoservice.setOnClickListener(new MyListener());

    }
    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {  //同时监听多个事件
            switch (v.getId()) {
                case R.id.mactivity1:
                    newExampleActivity1();
                    break;

                case R.id.jumptocontacts:
                    newContactsActivity();
                    break;
                case R.id.mmyprovider:
                    newMyProviderActivity();
                    break;

                case R.id.jumptoservice:
                    newServiceActivity();
                    break;


                case R.id.jumptosendstaticregisterbr:
                    newSendStaticBRActivity();
                    break;
                case R.id.jumptosenddynamicregisterbr:
                    newDynamicRegisterBroadcastActivity();
                    break;

                default:
                    break;
            }
        }
    }

    private void newExampleActivity1(){
        Intent i = new Intent(MainActivity.this,ExampleActivity.class);
        startActivity(i);
    }
    private void newContactsActivity(){
        Intent i = new Intent(MainActivity.this,ContactsActivity.class);
        startActivity(i);
    }
    private void newMyProviderActivity(){
        Intent i = new Intent(MainActivity.this,MyProviderActivity.class);
        startActivity(i);
    }
    private void newServiceActivity(){
        Intent i = new Intent(MainActivity.this,ServiceActivity.class);
        startActivity(i);
    }
    private void newSendStaticBRActivity(){
        Intent i = new Intent(MainActivity.this,SendStaticBRActivity.class);
        startActivity(i);
    }
    private void newDynamicRegisterBroadcastActivity(){
        Intent i = new Intent(MainActivity.this,DynamicRegisterBroadcastActivity.class);
        startActivity(i);
    }

}
