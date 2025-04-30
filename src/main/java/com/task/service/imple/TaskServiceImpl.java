package com.task.service.imple;

import com.task.dto.TaskRequestDTO;
import com.task.dto.TaskRequestUpdateDTO;
import com.task.dto.TaskResponse;
import com.task.dto.TaskResponseDTO;
import com.task.exception.AccessDeniedException;
import com.task.exception.TaskNotFoundException;
import com.task.model.Task;
import com.task.model.User;
import com.task.model.enums.STATUS;
import com.task.repository.TaskRepository;
import com.task.service.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable; // ✅ درست

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public TaskResponseDTO addTask(TaskRequestDTO requestDTO) {
        Task task = new Task.Builder()
                .title(requestDTO.title())
                .description(requestDTO.description())
                .dueDate(requestDTO.dueDate())
                .priority(requestDTO.priority())
                .status(STATUS.IN_PROGRESS)
                .user(userWhoIsLoggedIn())
                .build();


        Task taskCreated = taskRepository.save(task);
        return new TaskResponseDTO(taskCreated.getId(), taskCreated.getTitle());
    }

    @Override
    public TaskResponse findById(UUID taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() ->
                new TaskNotFoundException(String.format("Task not found exception with this id : %s", taskId)));
        if (task.getUser().equals(userWhoIsLoggedIn())) {
            throw new AccessDeniedException(String.format("You can not access to this task : %s", taskId));
        }
        return new TaskResponse(task.getId(), task.getTitle(), task.getDescription(), task.getDueDate(), task.getPriority(), task.getStatus());
    }

    @Override
    public TaskResponseDTO update(UUID taskId, TaskRequestUpdateDTO requestDTO) {
        findById(taskId);
        Task task = new Task(taskId, requestDTO.title(), requestDTO.description(),
                requestDTO.dueDate(), requestDTO.priority(), STATUS.TODO,
                userWhoIsLoggedIn());
        taskRepository.save(task);
        return new TaskResponseDTO(task.getId(), task.getTitle());
    }

    @Override
    public TaskResponseDTO approvedTask(UUID taskId) {
        TaskResponse taskFounded = findById(taskId);
        Task task = new Task(taskId, taskFounded.title(), taskFounded.description(),
                taskFounded.dueDate(), taskFounded.priority(), STATUS.DONE,
                userWhoIsLoggedIn());
        taskRepository.save(task);
        return new TaskResponseDTO(task.getId(), task.getTitle());
    }

    @Override
    public void deleteTask(UUID taskId) {
        findById(taskId);
        taskRepository.deleteById(taskId);
    }

    @Transactional
    @Override
    public List<TaskResponse> getAllTask() {
        User user = userWhoIsLoggedIn();
        return taskRepository.findAllTaskByUser(user).stream()
                .map(task -> new TaskResponse(task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getDueDate(),
                        task.getPriority(),
                        task.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public Page<TaskResponse> getAllTasks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("status").ascending());
        return taskRepository.findAll(pageable)
                .map(task -> new TaskResponse(task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getDueDate(),
                        task.getPriority(),
                        task.getStatus()));
    }

    public User userWhoIsLoggedIn() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
