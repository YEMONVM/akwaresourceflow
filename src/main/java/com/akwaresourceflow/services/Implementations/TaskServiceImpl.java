package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.models.Task;
import com.akwaresourceflow.services.Interfaces.TaskService;
import com.akwaresourceflow.repositories.TaskRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;

    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    @Override
    public Task getTask(Long id) {
        return taskRepo.findById(id).orElse(null);
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepo.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }

    @Override
    public void updateTask(Long id, Task task) {
        if (taskRepo.existsById(id)) {
            task.setId(id);
            taskRepo.save(task);
        }
    }
}
