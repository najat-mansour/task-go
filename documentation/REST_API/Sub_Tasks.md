# Sub-Tasks

## <mark>Create Sub-Task</mark>

### **Endpoint**

```http
POST /task-go/v1/workspaces/groups/tasks/{taskId}/subtasks
```

### **Path Parameters**

| Parameter     | Description                         |
| ------------- | ----------------------------------- |
| `taskId`      | UUID of the task to be managed      |


### **Headers**

| Header        | Value          |
| ------------- | -------------- |
| Authorization | Bearer `{JWT}` |

### **Request Body**

```json
{
  "name": "Create GitHub repo",
  "description": "Set up the GitHub repository",
  "status": "NOT_STARTED",
  "priority": "MEDIUM",
  "startingTimestamp": "2025-05-01T11:00:00",
  "endingTimestamp": "2025-05-01T12:00:00"
}
```

### **Responses**

#### ✅ 201 Created

```json
{
  "message": "Sub-task created successfully."
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

## <mark>Update Sub-Task</mark>

### **Endpoint**

```http
PATCH /task-go/v1/workspaces/groups/tasks/subtasks/{subtaskId}
```

### **Path Parameters**

| Parameter     | Description                         |
| ------------- | ----------------------------------- |
| `subtaskId`   | UUID of the sub-task to be managed  |

### **Headers**

| Header        | Value          |
| ------------- | -------------- |
| Authorization | Bearer `{JWT}` |

### **Request Body**

```json
{
  "name": "Updated Sub-Task Name",
  "description": "Updated sub-task description.",
  "status": "IN_PROGRESS",
  "priority": "HIGH",
  "startingTimestamp": "2025-05-01T12:30:00",
  "endingTimestamp": "2025-05-01T13:30:00"
}
```
> Fields are optional. Send only what needs to be updated.

### **Responses**

#### ✅ 200 OK

```json
{
  "message": "Sub-task updated successfully."
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
  "error": "Sub-task not found."
}
```

## <mark>Delete Sub-Task</mark>

### **Endpoint**

```http
DELETE /task-go/v1/workspaces/groups/tasks/subtasks/{subtaskId}
```

### **Path Parameters**

| Parameter     | Description                         |
| ------------- | ----------------------------------- |
| `subtaskId`   | UUID of the sub-task to be managed  |

### **Headers**

| Header        | Value          |
| ------------- | -------------- |
| Authorization | Bearer `{JWT}` |

### **Responses**

#### ✅ 204 No Content

```json
{
  "message": "Sub-task deleted successfully."
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
  "error": "Sub-task not found."
}
```

## <mark>Get Sub-Task by ID</mark>

### **Endpoint**

```http
GET /task-go/v1/workspaces/groups/tasks/subtasks/{subtaskId}
```

### **Path Parameters**

| Parameter     | Description                         |
| ------------- | ----------------------------------- |
| `subtaskId`   | UUID of the sub-task to be managed  |

### **Headers**

| Header        | Value          |
| ------------- | -------------- |
| Authorization | Bearer `{JWT}` |

### **Responses**

#### ✅ 200 OK

```json
{
  "id": "subtask-uuid",
  "name": "Create GitHub repo",
  "description": "Set up the GitHub repository",
  "status": "NOT_STARTED",
  "priority": "MEDIUM",
  "startingTimestamp": "2025-05-01T11:00:00",
  "endingTimestamp": "2025-05-01T12:00:00"
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
  "error": "Sub-task not found."
}
```