package com.taskgo.repositories;

import com.taskgo.entities.WorkspaceViewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkspaceViewerRepository extends JpaRepository<WorkspaceViewer, String> {
    Optional<WorkspaceViewer> findByWorkspaceIdAndViewerId(String workspaceId, String viewerId);
}