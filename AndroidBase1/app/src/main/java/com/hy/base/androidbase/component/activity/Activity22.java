package com.hy.base.androidbase.component.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.hy.app.component.R;


/**

 */
public class Activity22 extends Activity {
    String tag = "060_Activity3";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example1);

        TextView mtest = findViewById(R.id.mtest);
        //获取Bundle
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bun");
        String str = bundle.getString("data");
        mtest.setText(str);
//        Toast.makeText(Activity22.this,str,Toast.LENGTH_SHORT).show();
//        Log.d(tag, str);
    }

//    /**
//     * @param exitAnim //退出时的动画对应的资源id,设置0,不播放任何动画
//     */
//    @Override
//    public void overridePendingTransition(int enterAnim, int exitAnim) {
//        super.overridePendingTransition(0, 0);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("060" ,"ExampleActivity onDestroy");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0,0);
    }

}
