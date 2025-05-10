# Tasks

## <mark>Create Task</mark>

### **Endpoint**

```http
POST /task-go/v1/workspaces/{workspaceId}/groups/{groupId}/tasks
```

### **Path Parameters**

| Parameter     | Description                         |
| ------------- | ----------------------------------- |
| `workspaceId` | UUID of the workspace to be managed |
| `groupId`     | UUID of the group to be managed     |

### **Headers**

| Header        | Value          |
| ------------- | -------------- |
| Authorization | Bearer `{JWT}` |

### **Request Body**

```json
{
  "name": "Setup project",
  "description": "Initialize Git repo and base structure",
  "status": "NOT_STARTED",
  "priority": "HIGH",
  "starting_timestamp": "2025-05-01T10:00:00",
  "ending_timestamp": "2025-05-02T18:00:00",
  "ownerId": "user-uuid"
}
```

### **Responses**

#### ✅ 201 Created

```json
{
  "message": "Task created successfully."
}
```

#### 🔐 401 Unauthorized

```json
{
  "error": "Unauthorized. Token missing or invalid."
}
```

#### ❌ 400 Bad Request

```json
{
  "error": "Invalid input format."
}
```

## <mark>Update Task</mark>

### **Endpoint**

```http
PATCH /task-go/v1/workspaces/{workspaceId}/groups/{groupId}/tasks/{taskId}
```

### **Path Parameters**

| Parameter     | Description                         |
| ------------- | ----------------------------------- |
| `workspaceId` | UUID of the workspace to be managed |
| `groupId`     | UUID of the group to be managed     |
| `taskId`      | UUID of the task to be managed      |

### **Headers**

| Header        | Value          |
| ------------- | -------------- |
| Authorization | Bearer `{JWT}` |

### **Request Body**

```json
{
  "name": "Updated Task Name",
  "description": "Updated task description.",
  "status": "IN_PROGRESS",
  "priority": "MEDIUM",
  "starting_timestamp": "2025-05-01T12:00:00",
  "ending_timestamp": "2025-05-02T20:00:00"
}
```
> Fields are optional. Send only what needs to be updated.

### **Responses**

#### ✅ 200 OK

```json
{
  "id": "task-uuid",
  "name": "Updated Task Name",
  "description": "Updated task description.",
  "status": "IN_PROGRESS",
  "priority": "MEDIUM",
  "starting_timestamp": "2025-05-01T12:00:00",
  "ending_timestamp": "2025-05-02T20:00:00"
}
```

#### 🔐 401 Unauthorized

```json
{
  "error": "Unauthorized. Token missing or invalid."
}
```

#### ❌ 404 Not Found

```json
{
  "error": "Task not found."
}
```

## <mark>Delete Task</mark>

### **Endpoint**

```http
DELETE /task-go/v1/workspaces/{workspaceId}/groups/{groupId}/tasks/{taskId}
```

### **Path Parameters**

| Parameter     | Description                         |
| ------------- | ----------------------------------- |
| `workspaceId` | UUID of the workspace to be managed |
| `groupId`     | UUID of the group to be managed     |
| `taskId`      | UUID of the task to be managed      |

### **Headers**

| Header        | Value          |
| ------------- | -------------- |
| Authorization | Bearer `{JWT}` |

### **Responses**

#### ✅ 204 No Content

```json
{
  "message": "Task deleted successfully."
}
```

#### 🔐 401 Unauthorized

```json
{
  "error": "Unauthorized. Token missing or invalid."
}
```

#### ❌ 404 Not Found

```json
{
  "error": "Task not found."
}
```

## <mark>Get Task by ID</mark>

### **Endpoint**

```http
GET /task-go/v1/workspaces/{workspaceId}/groups/{groupId}/tasks/{taskId}
```

### **Path Parameters**

| Parameter     | Description                         |
| ------------- | ----------------------------------- |
| `workspaceId` | UUID of the workspace to be managed |
| `groupId`     | UUID of the group to be managed     |
| `taskId`      | UUID of the task to be managed      |

### **Headers**

| Header        | Value          |
| ------------- | -------------- |
| Authorization | Bearer `{JWT}` |

### **Responses**

#### ✅ 200 OK

```json
{
  "id": "task-uuid",
  "name": "Setup project",
  "description": "Initialize Git repo and base structure",
  "status": "NOT_STARTED",
  "priority": "HIGH",
  "starting_timestamp": "2025-05-01T10:00:00",
  "ending_timestamp": "2025-05-02T18:00:00",
  "owner": {
    "id": "user-uuid",
    "username": "najat-mansour",
    "firstName": "Najat",
    "lastName": "Mansour",
    "image_url": "https://example.com/image.jpg"
  },
  "subtasks": [
    {
      "id": "subtask-uuid",
      "name": "Create GitHub repo",
      "description": "Set up the GitHub repository",
      "status": "NOT_STARTED",
      "priority": "MEDIUM",
      "starting_timestamp": "2025-05-01T11:00:00",
      "ending_timestamp": "2025-05-01T12:00:00"
    }
  ]
}
```

#### 🔐 401 Unauthorized

```json
{
  "error": "Unauthorized. Token missing or invalid."
}
```

#### ❌ 404 Not Found

```json
{
  "error": "Task not found."
}
```