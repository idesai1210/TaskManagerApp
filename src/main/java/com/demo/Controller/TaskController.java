package com.demo.Controller;

import com.demo.Entity.Task;
import com.demo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ishan on 2/6/17.
 */
@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String homepage(){
        return "index.html";
    }


    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public List<Task> getAllTask(){
        return taskService.getAllTask();
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
    public Task getTaskById(@PathVariable("id") int id){
        return taskService.getTaskById(id);
    }

    @RequestMapping(value = "/tasks/delete/{taskStatus}", method = RequestMethod.DELETE)
    public void deleteTaskByStatus(@PathVariable("taskStatus") String taskStatus){
        taskService.deleteTaskByStatus(taskStatus);
    }

    @RequestMapping(value = "/tasks/update/{taskId}/{taskStatus}" ,method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateTask(@PathVariable("taskId") int taskId, @PathVariable("taskStatus") String taskStatus ){
        taskService.updateTask(taskId, taskStatus);
    }

    @RequestMapping(value = "/tasks/insert/{taskName}/{taskDesc}/{taskPriority}/{taskStatus}" ,method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertTask(@PathVariable("taskName") String taskName,@PathVariable("taskDesc") String taskDesc,@PathVariable("taskPriority") String taskPriority,@PathVariable("taskStatus") String taskStatus){
        Task task = new Task();
        task.setTaskName(taskName);
        task.setTaskDescription(taskDesc);
        task.setTaskPriority(taskPriority);
        task.setTaskStatus(taskStatus);
        taskService.insertTask(task);
    }
}
