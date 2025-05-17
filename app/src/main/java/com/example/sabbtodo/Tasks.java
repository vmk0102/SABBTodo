package com.example.sabbtodo;

public class Tasks {
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
        return TaskDueDate;
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
}
