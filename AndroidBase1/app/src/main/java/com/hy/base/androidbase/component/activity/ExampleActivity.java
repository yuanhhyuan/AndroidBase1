package com.hy.base.androidbase.component.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hy.app.base1.BaseActivity;
import com.hy.app.component.R;


/**
 activity的跳转


 */
public class ExampleActivity extends BaseActivity {
    int code = 1;

    /**
     * **********************************************************************
     * 典型情况下的生命周期
     * **********************************************************************
     * */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        Log.v("060" ,"ExampleActivity onCreate");

        //恢复onSaveInstanceState保存的数据
        if (savedInstanceState != null) {
            String tempData = savedInstanceState.getString("Activity");
            Toast.makeText(ExampleActivity.this,tempData,Toast.LENGTH_SHORT).show();
        }

        /**
         * 1、使用Intent的putExtra传递
         * */
        //向下一个活动传递数据
        Button btn01 = findViewById(R.id.btn01);
        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Hello Activity21 from ExampleActivity";
                Intent intent = new Intent(ExampleActivity.this, Activity21.class);
                intent.putExtra("extra_data", data);
                startActivity(intent);
            }
        });

        /**
         * 2、使用Intent的Bundle传递
         * */
        Button btn02 = findViewById(R.id.btn02);
        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "Hello Activity22 from ExampleActivity";
                Intent intent = new Intent(ExampleActivity.this,Activity22.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                Bundle bundle = new Bundle();
                bundle.putString("data", str);
                intent.putExtra("bun", bundle);
                startActivity(intent);
            }
        });

        /**
         * 3、使用Activity销毁时传递数据
         * */
        //返回数据给上一个活动
        Button btn03 = findViewById(R.id.btn03);
        btn03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ExampleActivity.this, Activity23.class);
                startActivityForResult(it, code);
            }
        });

        /**
         * 4、使用使用序列化对象Seriazable传递数据
         * */
        Button btn04 = findViewById(R.id.btn04);
        btn04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = new Person();
                person.setName("Tom");
                person.setAge(20);
                Intent intent = new Intent(ExampleActivity.this, Activity24.class);
                intent.putExtra("person_data", person);
                startActivity(intent);
            }
        });



        //跳转到设备上其他应用提供的Activity https://www.cnblogs.com/JLZT1223/p/6805558.html
        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.baidu.com");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
        });

        //跳转拨号界面
        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == code) {
            Toast.makeText(ExampleActivity.this, "Activity1跳转成功" + " , " + "char is : " + data.getStringExtra("a") + ", int is : " + data.getIntExtra("b",0), Toast.LENGTH_SHORT).show();
            Log.v("060" ,"Activity1跳转成功");
            Log.v("060" ,"char is : " + data.getStringExtra("a") + ", int is : " + data.getIntExtra("b",0));

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("060" ,"ExampleActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("060" ,"ExampleActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("060" ,"ExampleActivity onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v("060" ,"ExampleActivity onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("060" ,"ExampleActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("060" ,"ExampleActivity onDestroy");
    }



    /**
     * **********************************************************************
     * 非典型情况下的生命周期
     * **********************************************************************
     * */
    //保证一定在活动被回收之前调用，保存该活动的数据。
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String string = "activity 被系统回收了怎么办？";
        outState.putString("Activity", string);
    }

    //恢复onSaveInstanceState保存的数据，官方建议采用该方案
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        String tempData = savedInstanceState.getString("Activity");
        Toast.makeText(ExampleActivity.this,tempData,Toast.LENGTH_SHORT).show();
    }



    /**
     * **********************************************************************
     * 按下Back 键
     * **********************************************************************
     * */
    //通过按下Back 键
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }





    @Override
    public void initView() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initService() {

    }



}

