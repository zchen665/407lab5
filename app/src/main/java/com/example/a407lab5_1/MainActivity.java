package com.example.a407lab5_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String ukey = "username";

    public void onLogin(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("a407lab5_1", Context.MODE_PRIVATE);
        EditText myTextField = (EditText) findViewById(R.id.editTextTextPersonName);
        String usrName = myTextField.getText().toString();
        sharedPreferences.edit().putString("username", usrName).apply();
        goToActivity2(usrName);
    }
    public void goToActivity2(String s){
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("usrName",s);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("a407lab5_1", Context.MODE_PRIVATE);
        String curData = sharedPreferences.getString(ukey, "");
//        Log.i("info",curData);
//        setContentView(R.layout.activity_main);
        if(!curData.equals("")){
            goToActivity2(curData);
        }else{

            setContentView(R.layout.activity_main);
        }

    }


}

