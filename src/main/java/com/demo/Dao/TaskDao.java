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

    void deleteTaskByID(int id);

    void updateTask(Task task) throws ParseException;

    void insertTask(Task task);
}
