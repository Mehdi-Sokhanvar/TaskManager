package com.task.controller;

import com.task.dto.TaskRequestDTO;
import com.task.dto.TaskRequestUpdateDTO;
import com.task.dto.TaskResponse;
import com.task.dto.TaskResponseDTO;
import com.task.model.Task;
import com.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> add(@Valid @RequestBody TaskRequestDTO requestDTO) {
        TaskResponseDTO createdTask = taskService.addTask(requestDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Task created successfully");
        response.put("task", createdTask);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse>  findById(@PathVariable UUID taskId){
        return new ResponseEntity<>(taskService.findById(taskId),HttpStatus.FOUND);
    }

    @PutMapping("{taskId}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID taskId,@Valid @RequestBody TaskRequestUpdateDTO requestDTO) {
        TaskResponseDTO updatedTask =taskService.update(taskId,requestDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Task update successfully");
        response.put("task", updatedTask);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/approved/{taskId}")
    public ResponseEntity<Map<String, Object>> approvedTask(@PathVariable UUID taskId) {
        TaskResponseDTO updatedTask =taskService.approvedTask(taskId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Task Approved successfully");
        response.put("task", updatedTask);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/{taskId}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID taskId) {
        taskService.deleteTask(taskId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Task Delete successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskResponse>> allTasks(){
        return new ResponseEntity<>(taskService.getAllTask(),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<TaskResponse>> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(taskService.getAllTasks(page, size));
    }
}
