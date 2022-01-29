package com.task.task.controller;


import com.task.task.model.Task;
import com.task.task.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tareas")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("")
    List<Task> index(){
        return taskRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    Task create(@RequestBody Task task){
        return taskRepository.save(task);
    }

    @PutMapping("{id}")
    Task update(@PathVariable String id, @RequestBody Task task){
        Task taskFromDB = taskRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);

        taskFromDB.setName(task.getName());
        taskFromDB.setCompleted(task.isCompleted());

        return taskRepository.save(taskFromDB);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    void delete(@PathVariable String id){
        Task task = taskRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);

        taskRepository.delete(task);
    }

}
