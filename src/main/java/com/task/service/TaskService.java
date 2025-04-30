package com.task.service;

import com.task.dto.TaskRequestDTO;
import com.task.dto.TaskRequestUpdateDTO;
import com.task.dto.TaskResponse;
import com.task.dto.TaskResponseDTO;
import com.task.model.Task;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    TaskResponseDTO addTask(@Valid TaskRequestDTO requestDTO);

    TaskResponse findById(UUID taskId);

    TaskResponseDTO update(UUID uuid,@Valid TaskRequestUpdateDTO requestDTO);

    TaskResponseDTO approvedTask(UUID taskId);

    void deleteTask(UUID taskId);

    List<TaskResponse> getAllTask();

    Page<TaskResponse> getAllTasks(int page, int size);
}
