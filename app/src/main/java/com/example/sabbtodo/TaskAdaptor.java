package com.example.sabbtodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskAdaptor extends BaseAdapter {
    Context context;
    ArrayList<Tasks> taskList;
    public TaskAdaptor(Context context, ArrayList<Tasks> taskList){
        this.context=context;
        this.taskList=taskList;

    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Tasks getItem(int position) {
        return taskList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.todoadapter,parent,false);
            RelativeLayout rl = (RelativeLayout)convertView.findViewById(R.id.taskLayout);
            Button btnDelete=  (Button)convertView.findViewById(R.id.btnDelete);
            TextView tvTaskName = (TextView)convertView.findViewById(R.id.tvTaskName);
            TextView tvTaskDesc = (TextView)convertView.findViewById(R.id.tvTaskDesc);
            TextView tvTaskStatus = (TextView)convertView.findViewById(R.id.tvTaskStatus);
            TextView tvTaskDueDate = (TextView)convertView.findViewById(R.id.tvDueDate);
            CheckBox cb = (CheckBox)convertView.findViewById(R.id.cbComplete);
            Tasks task = getItem(position);
            tvTaskName.setText(task.getTaskName());
            tvTaskDesc.setText(task.getTaskDesc());
            tvTaskDueDate.setText(task.getTaskDueDate());
            tvTaskStatus.setText(task.getTaskStatus());
            if(task.getTaskStatus().equals("INCOMPLETE")){
                rl.setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_light));
            }else{
                rl.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_light));
            }
            if(task.getTaskStatus().equals("INCOMPLETE")){
                cb.setChecked(false);
            }else{
                cb.setChecked(true);
            }
            viewActivity vw=(viewActivity) context;

            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cb.isChecked()){
                        vw.updateTasktoComplete(task);
                    }else{
                        vw.updateTasktoINComplete(task);
                    }
                }
            });
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vw.deleteTask(task);
                }
            });

        }
        return  convertView;
    }
}
