package com.akwaresourceflow.services.Interfaces;

import com.akwaresourceflow.models.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTask(Long id);
    Task saveTask(Task task);
    void deleteTask(Long id);
    void updateTask(Long id, Task task);
}
