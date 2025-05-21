package com.example.sabbtodo;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
         db = openOrCreateDatabase("todo",MODE_PRIVATE,null);
        lvTasks=(ListView) findViewById(R.id.lvTasks);
        etFilter=(EditText)findViewById(R.id.etSearchTask);
        LoadTask();
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

    public  void LoadFilteredTask(String TaskName){
        String query = "SELECT * FROM TASKS WHERE TaskName LIKE '%"+TaskName+"%'";
        Cursor c = db.rawQuery(query,null);
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


        }
        c.close();


        TaskAdaptor taskAdaptor=new TaskAdaptor(viewActivity.this,tasks);
        lvTasks.setAdapter(taskAdaptor);

    }
    public void updateTasktoComplete(Tasks task){

        String q="UPDATE TASKS SET TASKSTATUS='COMPLETED' WHERE TASKID="+task.getTaskID()+";";
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
    public void deleteTask(Tasks task){

        String q="DELETE FROM TASKS WHERE TASKID="+task.getTaskID()+";";
        AlertDialog.Builder ab = new AlertDialog.Builder(viewActivity.this);
        ab.setTitle("Confrimation");
        ab.setMessage("Are you sure you want to delete this?");
        ab.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.execSQL(q);
                Toast.makeText(viewActivity.this, "Bye bye task", Toast.LENGTH_SHORT).show();
                LoadTask();

            }
        });
        ab.setNegativeButton("NOOOOOOOOO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        ab.show();
    }
}