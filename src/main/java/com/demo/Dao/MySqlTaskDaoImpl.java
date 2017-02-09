package com.demo.Dao;

import java.sql.*;
//import java.sql.PreparedStatement;
//import java.text.ParseException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import com.demo.Entity.Task;
import com.demo.Utility.DBUtility;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import java.util.Date;

/**
 * Created by Ishan on 2/6/17.
 */
@Repository
@Qualifier("MySql")
public class MySqlTaskDaoImpl implements TaskDao {
    private Connection connection;

    public MySqlTaskDaoImpl() {
        connection = DBUtility.getConnection();
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<Task>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from task_list where task_archived=0");
            while (rs.next()) {
                Task task = new Task();
                task.setTaskId(rs.getInt("task_id"));
                task.setTaskName(rs.getString("task_name"));
                task.setTaskDescription(rs.getString("task_description"));
                task.setTaskPriority(rs.getString("task_priority"));
                task.setTaskStatus(rs.getString("task_status"));
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public Task getTaskById(int id) {
        Task task = new Task();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from task_list where task_id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                task.setTaskId(rs.getInt("task_id"));
                task.setTaskName(rs.getString("task_name"));
                task.setTaskDescription(rs.getString("task_description"));
                task.setTaskPriority(rs.getString("task_priority"));
                task.setTaskStatus(rs.getString("task_status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public void deleteTaskByStatus(String taskStatus) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from task_list where task_status=?");
            // Parameters start with 1
            preparedStatement.setString(1, taskStatus);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTask(int taskId, String taskStatus) throws ParseException {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update task_list set task_status=?" +
                            "where task_id=?");
           // preparedStatement.setString(1, task.getTaskName());
           // preparedStatement.setString(2, task.getTaskDescription());
            //preparedStatement.setString(3, task.getTaskPriority());
            preparedStatement.setString(1, taskStatus);
            preparedStatement.setInt(2, taskId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertTask(Task task) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into task_list(task_name,task_description,task_priority,task_status,task_archived,task_start_time,task_end_time) values (?, ?, ?,?,?,?,?)");
            System.out.println("Task:"+task.getTaskName());
            preparedStatement.setString(1, task.getTaskName());
            preparedStatement.setString(2, task.getTaskDescription());
            preparedStatement.setString(3, task.getTaskPriority());
            preparedStatement.setString(4, task.getTaskStatus());
            preparedStatement.setInt(5,0);
            Date dt = new Date();
            SimpleDateFormat sdf =
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(dt);
            preparedStatement.setString(6,currentTime);
            preparedStatement.setString(7,currentTime);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
