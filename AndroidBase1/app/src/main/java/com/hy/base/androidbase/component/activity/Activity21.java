package com.hy.base.androidbase.component.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.hy.app.component.R;


/**

 */
public class Activity21 extends Activity {
    String tag = "060_Activity2";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The activity is being created.
        setContentView(R.layout.activity_example1);

        TextView mtest = findViewById(R.id.mtest);

        //get上一个activity传过来的信息
        Intent intent = getIntent();
        String data = intent.getStringExtra("extra_data");
        mtest.setText(data);
//        Toast.makeText(Activity21.this,data,Toast.LENGTH_SHORT).show();
//        Log.d(tag, data);
    }

}
