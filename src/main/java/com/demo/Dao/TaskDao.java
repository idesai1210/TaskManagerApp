package com.demo.Dao;

import com.demo.Entity.Task;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Ishan on 2/7/17.
 */
public interface TaskDao {
    List<Task> getAllTasks();

    Task getTaskById(int id);

    void deleteTaskByStatus(String taskStatus);

    void updateTask(int taskId, String taskStatus) throws ParseException;

    void insertTask(Task task);
}
