package com.taskgo.repositories;

import com.taskgo.entities.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, String> {
    List<Workspace> findAllByOwnerId(String ownerId);

    Integer countAllByOwnerId(String ownerId);
}
