package com.taskgo.listeners;

import com.taskgo.events.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CacheEvictionListener {
    private final CacheManager cacheManager;

    @EventListener
    public void handle(SubTasksChangedEvent event) {
        evict("subtasks:by-id", event.getSubTaskId());
        evict("tasks:by-id", event.getTaskId());
        evict("groups:by-id", event.getGroupId());
        evict("workspaces:by-id", event.getWorkspaceId());
        evict("workspaces:by-owner-id", null);
        evict("workspaces:by-viewer-id", null);
    }

    @EventListener
    public void handle(TasksChangedEvent event) {
        evict("tasks:by-id", event.getTaskId());
        evict("groups:by-id", event.getGroupId());
        evict("workspaces:by-id", event.getWorkspaceId());
        evict("workspaces:by-owner-id", null);
        evict("workspaces:by-viewer-id", null);
    }

    @EventListener
    public void handle(GroupsChangedEvent event) {
        evict("groups:by-id", event.getGroupId());
        evict("workspaces:by-id", event.getWorkspaceId());
        evict("workspaces:by-owner-id", null);
        evict("workspaces:by-viewer-id", null);
    }

    @EventListener
    public void handle(ViewersChangedEvent event) {
        evict("workspaces:by-id", event.getWorkspaceId());
        evict("workspaces:by-owner-id", null);
        evict("workspaces:by-viewer-id", null);
    }

    @EventListener
    public void handle(WorkspacesChangedEvent event) {
        evict("workspaces:by-id", event.getWorkspaceId());
        evict("workspaces:by-owner-id", null);
        evict("workspaces:by-viewer-id", null);
    }

    @EventListener
    public void handle(UsersChangedEvent event) {
        evict("users:by-id", event.getUserId());
        evict("users:by-username", event.getUsername());
        evict("users:by-email", event.getEmail());
        evict("users:all", null);

        evict("workspaces:by-id", null);
        evict("workspaces:by-owner-id", null);
        evict("workspaces:by-viewer-id", null);
        evict("groups:by-id", null);
        evict("tasks:by-id", null);
    }

    private void evict(String cacheName, String key) {
        if (cacheManager.getCache(cacheName) != null) {
            if (key != null) {
                Objects.requireNonNull(cacheManager.getCache(cacheName)).evictIfPresent(key);
            } else {
                Objects.requireNonNull(cacheManager.getCache(cacheName)).clear();
            }
        }
    }
}
