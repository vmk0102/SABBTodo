package com.example.sabbtodo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class viewActivity extends AppCompatActivity {
    ListView lvTasks;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
         db = openOrCreateDatabase("todo",MODE_PRIVATE,null);
        lvTasks=(ListView) findViewById(R.id.lvTasks);
        LoadTask();

    }
    public  void LoadTask(){
        Cursor c =db.rawQuery("SELECT * FROM TASKS",null);
        c.moveToFirst();
        ArrayList<Tasks> tasks= new ArrayList<>();

        while(c.moveToNext()){
            Tasks task= new Tasks();
            task.setTaskID(String.valueOf(c.getInt(0)));
            task.setTaskName(c.getString(1));
            task.setTaskDesc(c.getString(2));
            task.setTaskDueDate(c.getString(3));
            task.setTaskStatus(c.getString(4));
            tasks.add(task);
            Toast.makeText(viewActivity.this, task.getTaskName(), Toast.LENGTH_SHORT).show();


        }
        Toast.makeText(this, String.valueOf(tasks.size()), Toast.LENGTH_SHORT).show();
        c.close();


        TaskAdaptor taskAdaptor=new TaskAdaptor(viewActivity.this,tasks);
        lvTasks.setAdapter(taskAdaptor);

    }
    public void updateTasktoComplete(Tasks task){

        String q="UPDATE TASKS SET TASKSTATUS='COMPLETED',TASKNAME='MURGAAAAAAA' WHERE TASKID="+task.getTaskID()+";";
        db.execSQL(q);
        Toast.makeText(this, "Task Set to Completed", Toast.LENGTH_SHORT).show();
        LoadTask();
    }
    public void updateTasktoINComplete(Tasks task){

        String q="UPDATE TASKS SET TASKSTATUS='INCOMPLETE' WHERE TASKID="+task.getTaskID()+";";
        db.execSQL(q);
        Toast.makeText(this, "Task Set to Completed", Toast.LENGTH_SHORT).show();
        LoadTask();
    }
}