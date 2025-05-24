package com.example.sabbtodo;

import static android.app.NotificationManager.IMPORTANCE_HIGH;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kotlinx.coroutines.scheduling.Task;

public class AddTaskActivity extends AppCompatActivity {
    EditText txtName;
    EditText txtDesc;
    EditText txtDueDate;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel nc = new NotificationChannel("crud_channel","Notifiy Crud Operations",IMPORTANCE_HIGH);
        nm.createNotificationChannel(nc);

        txtName=(EditText)findViewById(R.id.txtTaskName);
        txtDesc=(EditText) findViewById(R.id.txtTaskDesc);
        txtDueDate=(EditText) findViewById(R.id.txtTaskDueDate);
        btnAdd=(Button) findViewById(R.id.btnAddTask);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tasks tasks = new Tasks(AddTaskActivity.this);
                tasks.setTaskName(txtName.getText().toString());
                tasks.setTaskDesc(txtDesc.getText().toString());
                tasks.setTaskDueDate(txtDueDate.getText().toString());
                tasks.AddTask(tasks);
                NotificationCompat.Builder nb = new NotificationCompat.Builder(AddTaskActivity.this,"crud_channel");
                nb.setContentTitle(getResources().getString(R.string.app_name));
                nb.setContentText("New Task Added: "+tasks.getTaskName());
                nb.setSmallIcon(R.mipmap.sabbkarlo_round);
                nm.notify(1,nb.build());


             /*   Toast.makeText(AddTaskActivity.this, "Task Added", Toast.LENGTH_SHORT).show();
                Toast.makeText(AddTaskActivity.this, "karlena time pe", Toast.LENGTH_SHORT).show();*/
            }
        });
    }
}