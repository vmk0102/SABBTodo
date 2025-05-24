package com.example.sabbtodo;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Tasks {
    private String  TaskID;
    private String TaskName;
    private String TaskDesc;
    private String TaskDueDate;
     private String TaskStatus;

    public String getTaskStatus() {
        return TaskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        TaskStatus = taskStatus;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public String getTaskDueDate() {
        return TaskDueDate.toString();
    }

    public void setTaskDueDate(String taskDueDate) {
        TaskDueDate = taskDueDate;
    }

    public String getTaskDesc() {
        return TaskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        TaskDesc = taskDesc;
    }

    public String getTaskID() {
        return TaskID;
    }

    public void setTaskID(String taskID) {
        TaskID = taskID;
    }

    SQLiteDatabase db;

    public void intializeDB(Context context){
        db = context.openOrCreateDatabase("todo", Context.MODE_PRIVATE,null);

    }
    Context context;
    public Tasks(Context context){
        intializeDB(context);
        this.context=context;
    }

    public void AddTask(Tasks task){
        String q = "INSERT INTO TASKS(TASKNAME,TASKDESC,DUEDATE,TASKSTATUS) VALUES " +
                "('"+task.getTaskName()+"','"+task.getTaskDesc()+"','"+task.getTaskDueDate()+"','INCOMPLETE');";
        db.execSQL(q);
    }
    public TaskAdaptor LoadTask(Context context){
        Cursor c =db.rawQuery("SELECT * FROM TASKS",null);
        c.moveToFirst();
        ArrayList<Tasks> tasks= new ArrayList<>();

        while(c.moveToNext()){
            Tasks task= new Tasks(context);
            task.setTaskID(String.valueOf(c.getInt(0)));
            task.setTaskName(c.getString(1));
            task.setTaskDesc(c.getString(2));
            task.setTaskDueDate(c.getString(3));
            task.setTaskStatus(c.getString(4));
            tasks.add(task);


        }
        c.close();


        TaskAdaptor taskAdaptor=new TaskAdaptor(context,tasks);
        return taskAdaptor;

    }
    public void updateTasktoComplete(String isComplete, ListView lv){

        String q="UPDATE TASKS SET TASKSTATUS='"+isComplete+"' WHERE TASKID="+getTaskID()+";";
        db.execSQL(q);
        Toast.makeText(context, "Task Set to Completed", Toast.LENGTH_SHORT).show();
        lv.setAdapter(LoadTask(context));
    }

    public void deleteTask(Tasks task,ListView lv){

        String q="DELETE FROM TASKS WHERE TASKID="+task.getTaskID()+";";
        AlertDialog.Builder ab = new AlertDialog.Builder(context);
        ab.setTitle("Confrimation");
        ab.setMessage("Are you sure you want to delete this?");
        ab.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.execSQL(q);
                Toast.makeText(context, "Bye bye task", Toast.LENGTH_SHORT).show();

                lv.setAdapter(LoadTask(context));

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
    public void DeleteAllTask(ListView lv){
        String q = "delete from TASKS";
        db.execSQL(q);
        lv.setAdapter(LoadTask(context));

    }





}
