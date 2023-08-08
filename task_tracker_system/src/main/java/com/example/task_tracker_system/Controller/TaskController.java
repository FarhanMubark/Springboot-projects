package com.example.task_tracker_system.Controller;

import com.example.task_tracker_system.ApiResponse.ApiResponse;
import com.example.task_tracker_system.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    ArrayList<Task> tasks = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Task> getTasks(){
        return tasks;
    }

    @PostMapping("/add")
    public ApiResponse addTask(@RequestBody Task task){
        tasks.add(task);
        return new ApiResponse("added successfully",200);
    }


    @PutMapping("/update/{index}")
    public ApiResponse updateTask(@PathVariable int index, @RequestBody Task task){
        tasks.set(index, task);
        return new ApiResponse("updated successfully",200);
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index){
        tasks.remove(index);
        return new ApiResponse("deleted successfully",200);
    }

    @PutMapping("/status/{index}")
    public ApiResponse changeStatus(@PathVariable int index){

        Task l = tasks.get(index);
        if (l.getStatus().equals("Done")){
            l.setStatus("Not Done");
        } else l.setStatus("Done");

        return new ApiResponse("changed successfully",200);

    }

    @GetMapping("/serach/{title}")
    public Task serachTask(@PathVariable String title){
          Task t1 = new Task();
        for (Task task: tasks){
            if (task.getTitle().equals(title)){
                return task;
            }
        }
        return t1;
    }



}
