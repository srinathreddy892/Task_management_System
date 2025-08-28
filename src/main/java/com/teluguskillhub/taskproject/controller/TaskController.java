package com.teluguskillhub.taskproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teluguskillhub.taskproject.payload.TaskDto;
import com.teluguskillhub.taskproject.service.TaskService;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/{userid}/tasks")
    public ResponseEntity<TaskDto> saveTask(@PathVariable(name = "userid") long userid,
                                            @RequestBody TaskDto taskDto) {
        TaskDto savedTask = taskService.saveTask(userid, taskDto);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
   
    }
    
    @PreAuthorize("hasRole('ADMIN')")
  //get all tasks
    @GetMapping("/{userid}/tasks")
    public ResponseEntity<List<TaskDto>> getAllTask(@PathVariable(name="userid")long userid){
    	
		return new ResponseEntity<>(taskService.getAlltasks(userid),HttpStatus.OK);
  	  
    }
 // Get individual task
    @GetMapping("/{userid}/tasks/{taskid}")
    public ResponseEntity<TaskDto> getTask(@PathVariable(name="userid") long userid,
                                           @PathVariable(name="taskid") long taskid) {
        return new ResponseEntity<>(taskService.getTask(userid, taskid), HttpStatus.OK);
    }
 // delete individual task
    @DeleteMapping("/{userid}/tasks/{taskid}")
    public ResponseEntity<String> deleteTask(@PathVariable(name="userid") long userid,
                                             @PathVariable(name="taskid") long taskid) {
        taskService.deleteTask(userid, taskid);
        return new ResponseEntity<>("User task is successfully deleted", HttpStatus.OK);
    }

    
    
}


      
