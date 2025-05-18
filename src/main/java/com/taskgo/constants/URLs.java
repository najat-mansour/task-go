package com.taskgo.constants;

public class URLs {
    public static final String GLOBAL_API_PREFIX = "/task-go/v1";

    public static final String USERS_PREFIX = "/users";
    public static final String WORKSPACES_PREFIX = "/workspaces";

    public static final String USERS_CREATE = "/";
    public static final String USERS_UPDATE = "/{id}";
    public static final String USERS_GET_ALL = "/";
    public static final String USERS_GET_BY_ID = "/id/{id}";
    public static final String USERS_GET_BY_USERNAME = "/username/{username}";
    public static final String USERS_GET_BY_EMAIL = "/email/{email}";

    public static final String WORKSPACES_CREATE = "/";
    public static final String WORKSPACES_UPDATE = "/{workspaceId}";
    public static final String WORKSPACES_DELETE = "/{workspaceId}";

    public static final String WORKSPACES_USERS_CREATE = "/{workspaceId}/viewers";
    public static final String WORKSPACES_USERS_DELETE = "/{workspaceId}/viewers/{viewerId}";

    public static final String GROUPS_CREATE = "/{workspaceId}/groups";
    public static final String GROUPS_UPDATE = "/groups/{groupId}";
    public static final String GROUPS_DELETE = "/groups/{groupId}";

    public static final String TASKS_CREATE = "/groups/{groupId}/tasks";
    public static final String TASKS_UPDATE = "/groups/tasks/{taskId}";
    public static final String TASKS_DELETE = "/groups/tasks/{taskId}";

    public static final String SUB_TASKS_CREATE = "/groups/tasks/{taskId}/subtasks";
    public static final String SUB_TASKS_UPDATE = "/groups/tasks/subtasks/{subTaskId}";
    public static final String SUB_TASKS_DELETE = "/groups/tasks/subtasks/{subTaskId}";
}