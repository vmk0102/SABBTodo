package com.example.sabbtodo;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class viewActivity extends AppCompatActivity {
    ListView lvTasks;
    SQLiteDatabase db;
    EditText etFilter;
    Tasks tasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
         tasks=new Tasks(viewActivity.this);
        lvTasks=(ListView) findViewById(R.id.lvTasks);
        etFilter=(EditText)findViewById(R.id.etSearchTask);
        lvTasks.setAdapter(tasks.LoadTask(viewActivity.this));
        Button delBtn=(Button)findViewById(R.id.btnDeleteAll);
        etFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text=etFilter.getText().toString();
                LoadFilteredTask(text);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ab = new AlertDialog.Builder(viewActivity.this);
                ab.setTitle("Confirmation");
                ab.setMessage("Are you sure you want delete all task");
                ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tasks.DeleteAllTask(lvTasks);
                    }
                });
                ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();

            }
        });

    }



    public  void LoadFilteredTask(String TaskName){


        lvTasks.setAdapter(tasks.LoadTask(viewActivity.this));
    }


}