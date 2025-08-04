package com.taskgo.services;

import com.taskgo.dtos.tasks.TaskCreateRequestDTO;
import com.taskgo.dtos.tasks.TaskResponseDTO;
import com.taskgo.dtos.tasks.TaskUpdateRequestDTO;
import com.taskgo.entities.Task;
import com.taskgo.entities.User;
import com.taskgo.events.TasksChangedEvent;
import com.taskgo.exceptions.NoGroupsFoundException;
import com.taskgo.exceptions.NoTasksFoundException;
import com.taskgo.exceptions.NoUsersFoundException;
import com.taskgo.mappers.TaskMapper;
import com.taskgo.repositories.GroupRepository;
import com.taskgo.repositories.TaskRepository;
import com.taskgo.repositories.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;
    private final EmailSenderService emailSenderService;

    private final ApplicationEventPublisher eventPublisher;

    private void publishTasksChangedEvent(Task task) {
        eventPublisher.publishEvent(new TasksChangedEvent(
                task.getId(),
                task.getGroup().getId(),
                task.getGroup().getWorkspace().getId()
        ));
    }

    public void createTask(String groupId, TaskCreateRequestDTO taskCreateRequestDTO) throws NoGroupsFoundException, NoUsersFoundException, MessagingException {
        Task task = taskMapper.toEntity(taskCreateRequestDTO);
        task.setGroup(groupRepository.findById(groupId).orElseThrow(NoGroupsFoundException::new));
        User user = userRepository.findById(taskCreateRequestDTO.getAssignedToId()).orElseThrow(NoUsersFoundException::new);
        task.setAssignedTo(user);
        taskRepository.save(task);

        // Notify the user
        emailSenderService.sendNotificationEmailWhenUserAssignedToTask(user.getEmail());

        publishTasksChangedEvent(task);
    }

    public void updateTask(String taskId, TaskUpdateRequestDTO taskUpdateRequestDTO) throws NoTasksFoundException, NoUsersFoundException, MessagingException {
        Task task = taskRepository.findById(taskId).orElseThrow(NoTasksFoundException::new);
        if (taskUpdateRequestDTO.getName() != null) {
            task.setName(taskUpdateRequestDTO.getName());
        }
        if (taskUpdateRequestDTO.getDescription() != null) {
            task.setDescription(taskUpdateRequestDTO.getDescription());
        }
        if (taskUpdateRequestDTO.getStatus() != null) {
            task.setStatus(taskUpdateRequestDTO.getStatus());
        }
        if (taskUpdateRequestDTO.getPriority() != null) {
            task.setPriority(taskUpdateRequestDTO.getPriority());
        }
        if (taskUpdateRequestDTO.getStartingTimestamp() != null) {
            task.setStartingTimestamp(taskUpdateRequestDTO.getStartingTimestamp());
        }
        if (taskUpdateRequestDTO.getEndingTimestamp() != null) {
            task.setEndingTimestamp(taskUpdateRequestDTO.getEndingTimestamp());
        }
        if (taskUpdateRequestDTO.getIsFavorite() != null) {
            task.setIsFavorite(taskUpdateRequestDTO.getIsFavorite());
        }
        if (taskUpdateRequestDTO.getAssignedToId() != null) {
            User user = userRepository.findById(taskUpdateRequestDTO.getAssignedToId()).orElseThrow(NoUsersFoundException::new);
            task.setAssignedTo(user);

            // Notify the user
            emailSenderService.sendNotificationEmailWhenUserAssignedToTask(user.getEmail());
        }
        taskRepository.save(task);

        publishTasksChangedEvent(task);
    }

    public void deleteTask(String taskId) throws NoTasksFoundException {
        Task task = taskRepository.findById(taskId).orElseThrow(NoTasksFoundException::new);
        taskRepository.delete(task);

        publishTasksChangedEvent(task);
    }

    @Cacheable(value = "tasks:by-id", key = "#taskId")
    public TaskResponseDTO getTaskById(String taskId) throws NoTasksFoundException {
        Optional<Task> task = taskRepository.findById(taskId);
        return task.map(taskMapper::toResponseDTO).orElseThrow(NoTasksFoundException::new);
    }
}
