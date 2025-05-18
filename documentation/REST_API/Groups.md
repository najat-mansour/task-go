# Groups

## <mark>Create Group</mark>

### **Endpoint**

```http
POST /task-go/v1/workspaces/{workspaceId}/groups
```

### **Request Headers**

| Header        | Value          |
| ------------- | -------------- |
| Authorization | Bearer `<JWT>` |

### **Path Parameters**

| Parameter     | Type   | Description           |
| ------------- | ------ | --------------------- |
| `workspaceId` | string | UUID of the workspace |

### **Request Body**

```json
{
  "name": "Development Team",
  "description": "Handles all development tasks"
}
```

### **Possible Responses**

#### ✅ 201 Created

```json
{
  "message": "Group created successfully."
}
```

#### ⚠️ 400 Bad Request

```json
{
  "error": "Invalid input format."
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
  "error": "Workspace not found."
}
```

## <mark>Update Group</mark>

### **Endpoint**

```http
PATCH /task-go/v1/workspaces/groups/{groupId}
```

### **Request Headers**

| Header        | Value          |
| ------------- | -------------- |
| Authorization | Bearer `<JWT>` |

### **Path Parameters**

| Parameter     | Type   | Description           |
| ------------- | ------ | --------------------- |
| `groupId`     | string | UUID of the group     |

### **Request Body**

```json
{
  "name": "Updated Group Name",
  "description": "Updated description."
}
```
> All fields are optional.

### **Possible Responses**

#### ✅ 200 OK

```json
{
  "message": "Group updated successfully."
}
```

#### ⚠️ 400 Bad Request

```json
{
  "error": "Invalid input format."
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
  "error": "Group not found."
}
```

## <mark>Delete Group</mark>

### **Endpoint**

```http
DELETE /task-go/v1/workspaces/groups/{groupId}
```

### **Request Headers**

| Header        | Value          |
| ------------- | -------------- |
| Authorization | Bearer `<JWT>` |

### **Path Parameters**

| Parameter     | Type   | Description           |
| ------------- | ------ | --------------------- |
| `groupId`     | string | UUID of the group     |

### **Possible Responses**

#### ✅ 200 OK

```json
{
  "message": "Group deleted successfully."
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
  "error": "Group not found in workspace."
}
```

## <mark>Get Group by ID</mark>

### **Endpoint**

```http
GET /task-go/v1/workspaces/groups/{groupId}
```

### **Request Headers**

| Header        | Value          |
| ------------- | -------------- |
| Authorization | Bearer `<JWT>` |

### **Path Parameters**

| Parameter     | Type   | Description           |
| ------------- | ------ | --------------------- |
| `groupId`     | string | UUID of the group     |

### **Possible Responses**

#### ✅ 200 OK

```json
{
  "id": "group-uuid",
  "name": "Development Team",
  "color": "#123456",
  "description": "Handles all development tasks",
  "tasks": [
    {
      "id": "task-uuid",
      "name": "Setup project",
      "description": "Initialize Git repo and base structure",
      "status": "NOT_STARTED",
      "priority": "HIGH",
      "isFavorite": false,
      "startingTimestamp": "2025-05-01T10:00:00",
      "endingTimestamp": "2025-05-02T18:00:00",
      "assignedTo": {
        "id": "user-uuid",
        "username": "najat-mansour",
        "firstName": "Najat",
        "lastName": "Mansour"
      },
      "subtasks": [
        {
          "id": "subtask-uuid",
          "name": "Create GitHub repo",
          "description": "Set up the GitHub repository",
          "status": "NOT_STARTED",
          "priority": "MEDIUM",
          "startingTimestamp": "2025-05-01T11:00:00",
          "endingTimestamp": "2025-05-01T12:00:00"
        }
      ]
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
  "error": "Group not found in workspace."
}
```