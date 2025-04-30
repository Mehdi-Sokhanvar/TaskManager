package com.task.service.imple;

import com.task.dto.TaskRequestDTO;
import com.task.dto.TaskResponseDTO;
import com.task.model.Task;
import com.task.model.User;
import com.task.model.enums.PRIORITY;
import com.task.model.enums.STATUS;
import com.task.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void testAddTask_ShouldSaveAndReturnResponseDTO() {
        // Given
        TaskRequestDTO requestDTO = new TaskRequestDTO(
                "Test Task",
                "This is a test",
                LocalDate.of(2025, 5, 1),
                PRIORITY.MEDIUM
        );

        User mockUser = new User(); // or mock(User.class)
        UUID generatedId = UUID.randomUUID();

        Task taskToSave = new Task.Builder()
                .uuid(generatedId)
                .title("Test Task")
                .description("This is a test")
                .dueDate(LocalDate.of(2025, 5, 1))
                .priority(PRIORITY.MEDIUM)
                .status(STATUS.IN_PROGRESS)
                .user(mockUser)
                .build();

        // Simulate behavior
        when(taskRepository.save(any(Task.class))).thenReturn(taskToSave);

        // When
        TaskResponseDTO response = taskService.addTask(requestDTO);

        // Then
        assertNotNull(response);
        assertEquals(generatedId, response.uuid());
        assertEquals("Test Task", response.title());
    }
}