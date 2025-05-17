package com.example.sabbtodo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class viewActivity extends AppCompatActivity {
    ListView lvTasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        SQLiteDatabase db = openOrCreateDatabase("todo",MODE_PRIVATE,null);
        Cursor c =db.rawQuery("SELECT * FROM TASKS",null);
        c.moveToFirst();
        ArrayList<Tasks> tasks= new ArrayList<>();
        lvTasks=(ListView) findViewById(R.id.lvTasks);
        while(c.moveToNext()){
            Tasks task= new Tasks();
            task.setTaskName(c.getString(1));
            task.setTaskDesc(c.getString(2));
            task.setTaskDueDate(c.getString(3));
            task.setTaskStatus(c.getString(4));
            tasks.add(task);


        }
        c.close();
        db.close();
        TaskAdaptor taskAdaptor=new TaskAdaptor(viewActivity.this,tasks);
        lvTasks.setAdapter(taskAdaptor);

    }
}