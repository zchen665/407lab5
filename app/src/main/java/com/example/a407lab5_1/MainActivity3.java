package com.example.a407lab5_1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity3 extends AppCompatActivity {
    int noteid = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s3_main);
        EditText myTextField = (EditText) findViewById(R.id.textView2);
        Intent intent = getIntent();
        noteid = intent.getIntExtra("noteid", -1);
        if (noteid != -1){
            Note note = MainActivity2.notes.get(noteid);
            String content = note.getContent();
            myTextField.setText(content);
        }

    }

    public void onSave(View view) {
        EditText myTextField = (EditText) findViewById(R.id.textView2);
        String newContent = myTextField.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("a407lab5_1", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);
        DBHelper helper = new DBHelper(sqLiteDatabase);

        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if(noteid == -1){
            title = "NOTE_" + (MainActivity2.notes.size() + 1);
            helper.saveNotes(username,title, newContent, date);
        }else{
            title = "NOTE_" + (noteid + 1);
            helper.updateNote(username,title,newContent,date);
        }

        goToActivity2(username);
    }
    public void goToActivity2(String usr){
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("usrName",usr);
        startActivity(intent);
    }

}

