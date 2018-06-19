package com.hy.base.androidbase.component.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.hy.app.component.R;


/**

 */
public class Activity23 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The activity is being created.
        setContentView(R.layout.activity_example1);

        //setResult
        setResult(Activity.RESULT_OK);

        //setResult
        Intent intent = getIntent();
        intent.putExtra("a", "hello form activity23");
        intent.putExtra("b", 2);
        setResult(Activity.RESULT_OK, intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}
