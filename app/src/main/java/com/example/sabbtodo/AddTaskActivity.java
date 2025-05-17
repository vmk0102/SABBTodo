package com.example.sabbtodo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddTaskActivity extends AppCompatActivity {
    EditText txtName;
    EditText txtDesc;
    EditText txtDueDate;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        SQLiteDatabase db = openOrCreateDatabase("todo",MODE_PRIVATE,null);

        txtName=(EditText)findViewById(R.id.txtTaskName);
        txtDesc=(EditText) findViewById(R.id.txtTaskDesc);
        txtDueDate=(EditText) findViewById(R.id.txtTaskDueDate);
        btnAdd=(Button) findViewById(R.id.btnAddTask);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName=txtName.getText().toString();
                String taskDesc=txtDesc.getText().toString();
                String taskDueDate=txtDueDate.getText().toString();
                String q = "INSERT INTO TASKS(TASKNAME,TASKDESC,DUEDATE) VALUES " +
                        "('"+taskName+"','"+taskDesc+"','"+taskDueDate+"');";
                db.execSQL(q);
                Toast.makeText(AddTaskActivity.this, "Task Added", Toast.LENGTH_SHORT).show();
                Toast.makeText(AddTaskActivity.this, "karlena time pe", Toast.LENGTH_SHORT).show();
            }
        });
    }
}