package com.hy.base.androidbase.component.contentprovider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.hy.app.component.R;

import java.util.ArrayList;
import java.util.List;

/**
 ContentResolver访问联系人进程的数据
 */
public class ContactsActivity extends AppCompatActivity {
    String TAG = "060_ContactsActivity";

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    ListView contactsView;
    ArrayAdapter<String> adapter;
    List<String> contactsList =new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        contactsView=(ListView)findViewById(R.id.contacts_views);

        showContacts();

        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,contactsList);
        contactsView.setAdapter(adapter);

    }
    /**
     *  requestPermissions获取读contacts的权限
     */
    private void showContacts() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            readContacts();
        }
    }
    /**
     *  requestPermissions获取读contacts的权限
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted

                showContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     *  读取的联系人信息放入contactsList
     */
    private void readContacts(){
        Cursor cursor=null;
        try{
            cursor=getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null
            );
            while(cursor.moveToNext()){
                String displayName =cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactsList.add(displayName+"\n"+number);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
        }
    }
}
