# Workspaces

## <mark>Create Workspace</mark>

### **Endpoint**

```http
POST /task-go/v1/workspaces
```

### **Headers**

| Header        | Value          |
| ------------- | -------------- |
| Authorization | Bearer `{JWT}` |

### **Request Body**

```json
{
  "name": "My Workspace",
  "description": "This is my main project workspace."
}
```

### **Responses**

#### ✅ 201 Created

```json
{
  "message": "Workspace created successfully."
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
  "error": "Workspace name is required."
}
```

## <mark>Update Workspace Info</mark>

### **Endpoint**

```http
PATCH /task-go/v1/workspaces/{workspaceId}
```

### **Path Parameter**

| Parameter     | Description                     |
| ------------- | ------------------------------- |
| `workspaceId` | UUID of the workspace to update |

### **Headers**

| Header        | Value          |
| ------------- | -------------- |
| Authorization | Bearer `{JWT}` |

### **Request Body**

```json
{
  "name": "Updated Workspace Name",
  "description": "Updated workspace description."
}
```
> Fields are optional. Send only what needs to be updated.

### **Responses**

#### ✅ 200 OK

```json
{
  "id": "workspace-uuid",
  "name": "Updated Workspace Name",
  "description": "Updated workspace description."
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
  "error": "Workspace not found with this ID."
}
```

## <mark>Get All Workspaces for a User</mark>

### **Endpoint**

```http
GET /task-go/v1/users/{userId}/workspaces
```

### **Path Parameter**

| Parameter | Description      |
| --------- | ---------------- |
| `userId`  | UUID of the user |

### **Headers**

| Header        | Value          |
| ------------- | -------------- |
| Authorization | Bearer `{JWT}` |

### **Responses**

#### ✅ 200 OK

```json
{
  "id": "workspace-uuid",
  "name": "My Workspace",
  "description": "This is my main project workspace.",
  "members": [
    {
      "id": "user-uuid",
      "username": "najat-mansour",
      "firstName": "Najat",
      "lastName": "Mansour",
      "image_url": "https://example.com/image.jpg"
    }
  ],
  "groups": [
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
  "error": "User not found."
}
```

## <mark>Get Workspace by ID</mark>

### **Endpoint**

```http
GET /task-go/v1/workspaces/{workspaceId}
```

### **Path Parameter**

| Parameter     | Description           |
| ------------- | --------------------- |
| `workspaceId` | UUID of the workspace |

### **Headers**

| Header        | Value          |
| ------------- | -------------- |
| Authorization | Bearer `{JWT}` |

### **Responses**

#### ✅ 200 OK

```json
{
  "id": "workspace-uuid",
  "name": "My Workspace",
  "description": "This is my main project workspace.",
  "members": [
    {
      "id": "user-uuid",
      "username": "najat-mansour",
      "firstName": "Najat",
      "lastName": "Mansour",
      "image_url": "https://example.com/image.jpg"
    }
  ],
  "groups": [
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
  "error": "Workspace not found."
}
```


## <mark>Delete Workspace</mark>

### **Endpoint**

```http
DELETE /task-go/v1/workspaces/{workspaceId}
```

### **Path Parameter**

| Parameter     | Description           |
| ------------- | --------------------- |
| `workspaceId` | UUID of the workspace |

### **Headers**

| Header        | Value          |
| ------------- | -------------- |
| Authorization | Bearer `{JWT}` |

### **Responses**

#### ✅ 204 No Content

```json
{
  "message": "Workspace deleted successfully."
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

## <mark>Add User to Workspace</mark>

### **Endpoint**

```http
POST /task-go/v1/workspaces/{workspaceId}/users
```

### **Path Parameter**

| Parameter     | Description           |
| ------------- | --------------------- |
| `workspaceId` | UUID of the workspace |

### **Headers**

| Header        | Value          |
| ------------- | -------------- |
| Authorization | Bearer `{JWT}` |

### **Request Body**

```json
{
  "userId": "user-id-to-add"
}
```

### **Responses**

#### ✅ 200 OK

```json
{
  "message": "User added to workspace successfully."
}
```

#### 🔐 401 Unauthorized

```json
{
  "error": "Unauthorized. Token missing or invalid."
}
```

#### ❌ 404 Not Found – Workspace

```json
{
  "error": "Workspace not found."
}
```

#### ❌ 404 Not Found – User

```json
{
  "error": "User not found."
}
```

#### ⚠️ 409 Conflict

```json
{
  "error": "User is already a member of this workspace."
}
```