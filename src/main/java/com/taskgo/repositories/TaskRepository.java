package com.taskgo.repositories;

import com.taskgo.constants.Status;
import com.taskgo.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
    Integer countAllByAssignedToId(String assignedToId);
    Integer countAllByAssignedToIdAndStatus(String assignedToId, Status status);
}
