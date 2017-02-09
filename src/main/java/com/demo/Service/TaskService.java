package com.demo.Service;

import com.demo.Dao.MySqlTaskDaoImpl;
import com.demo.Entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Ishan on 2/6/17.
 */
@Service
public class TaskService {

    @Autowired
    @Qualifier("MySql")
    private MySqlTaskDaoImpl mySqlTaskDaoImpl;

    public List<Task> getAllTask(){
        return mySqlTaskDaoImpl.getAllTasks();

    }

    public Task getTaskById(int id){
        return mySqlTaskDaoImpl.getTaskById(id);
    }

    public void deleteTaskByStatus(String taskStatus){
        mySqlTaskDaoImpl.deleteTaskByStatus(taskStatus);
    }

    public void updateTask(int taskId, String taskStatus){
        try {
            this.mySqlTaskDaoImpl.updateTask(taskId, taskStatus);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void insertTask(Task task) {
        mySqlTaskDaoImpl.insertTask(task);
    }
}
