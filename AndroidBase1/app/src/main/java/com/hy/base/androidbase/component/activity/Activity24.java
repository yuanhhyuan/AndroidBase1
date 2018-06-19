package com.hy.base.androidbase.component.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

/**

 */
public class Activity24 extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Person person = (Person) getIntent().getSerializableExtra("person_data");
        String name = person.getName();
        int age = person.getAge();

        Toast.makeText(this, name + " : " + age,
                Toast.LENGTH_SHORT).show();

    }

}
