# Workspaces

## <mark>Create Workspace</mark>

### **Endpoint**

```http
POST /task-go/v1/workspaces/
```

### **Headers**

| Header        | Value          |
|---------------|----------------|
| Authorization | Bearer `{JWT}` |

### **Request Body**

```json
{
  "ownerId": "user-uuid",
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
|---------------|---------------------------------|
| `workspaceId` | UUID of the workspace to update |

### **Headers**

| Header        | Value          |
|---------------|----------------|
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
"message": "Workspace updated successfully."
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

## <mark>Get All Workspaces for an Owner</mark>

### **Endpoint**

```http
GET /task-go/v1/users/workspaces/owner/{ownerId}
```

### **Path Parameter**

| Parameter | Description      |
|-----------|------------------|
| `ownerId` | UUID of the user |

### **Headers**

| Header        | Value          |
|---------------|----------------|
| Authorization | Bearer `{JWT}` |

### **Responses**

#### ✅ 200 OK

```json
{
  "id": "workspace-uuid",
  "name": "My Workspace",
  "description": "This is my main project workspace.",
  "viewers": [
    {
      "id": "user-uuid",
      "username": "najat-mansour",
      "firstName": "Najat",
      "lastName": "Mansour",
      "email": "mansournajat7@gmail.com",
      "birthdate": "2003-01-28",
      "gender": "FEMALE",
      "address": {
        "country": "Palestine",
        "city": "Nablus",
        "town": "",
        "street": ""
      },
      "createdAt": "2025-05-02T18:00:00",
      "app_rate": 5
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
          "startingTimestamp": "2025-05-01T10:00:00",
          "endingTimestamp": "2025-05-02T18:00:00",
          "isFavorite": false,
          "assignedTo": {
            "id": "user-uuid",
            "username": "najat-mansour",
            "firstName": "Najat",
            "lastName": "Mansour",
            "email": "mansournajat7@gmail.com",
            "birthdate": "2003-01-28",
            "gender": "FEMALE",
            "address": {
              "country": "Palestine",
              "city": "Nablus",
              "town": "",
              "street": ""
            },
            "createdAt": "2025-05-02T18:00:00",
            "app_rate": 5
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
  ]
}
```

#### 🔐 401 Unauthorized

```json
{
  "error": "Unauthorized. Token missing or invalid."
}
```

#### ❌ 404 Not Found - User

```json
{
  "error": "User not found."
}
```

#### ❌ 404 Not Found - Workspaces

```json
{
  "error": "No workspaces found for this user."
}
```

## <mark>Get All Workspaces for a Viewer</mark>

### **Endpoint**

```http
GET /task-go/v1/users/workspaces/viewer/{viewerId}
```

### **Path Parameter**

| Parameter  | Description      |
|------------|------------------|
| `viewerId` | UUID of the user |

### **Headers**

| Header        | Value          |
|---------------|----------------|
| Authorization | Bearer `{JWT}` |

### **Responses**

#### ✅ 200 OK

```json
{
  "id": "workspace-uuid",
  "name": "My Workspace",
  "description": "This is my main project workspace.",
  "viewers": [
    {
      "id": "user-uuid",
      "username": "najat-mansour",
      "firstName": "Najat",
      "lastName": "Mansour",
      "email": "mansournajat7@gmail.com",
      "birthdate": "2003-01-28",
      "gender": "FEMALE",
      "address": {
        "country": "Palestine",
        "city": "Nablus",
        "town": "",
        "street": ""
      },
      "createdAt": "2025-05-02T18:00:00",
      "app_rate": 5
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
          "startingTimestamp": "2025-05-01T10:00:00",
          "endingTimestamp": "2025-05-02T18:00:00",
          "isFavorite": false,
          "assignedTo": {
            "id": "user-uuid",
            "username": "najat-mansour",
            "firstName": "Najat",
            "lastName": "Mansour",
            "email": "mansournajat7@gmail.com",
            "birthdate": "2003-01-28",
            "gender": "FEMALE",
            "address": {
              "country": "Palestine",
              "city": "Nablus",
              "town": "",
              "street": ""
            },
            "createdAt": "2025-05-02T18:00:00",
            "app_rate": 5
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
  ]
}
```

#### 🔐 401 Unauthorized

```json
{
  "error": "Unauthorized. Token missing or invalid."
}
```

#### ❌ 404 Not Found - User

```json
{
  "error": "User not found."
}
```

#### ❌ 404 Not Found - Workspaces

```json
{
  "error": "No workspaces found for this user."
}
```

## <mark>Get Workspace by ID</mark>

### **Endpoint**

```http
GET /task-go/v1/workspaces/{workspaceId}
```

### **Path Parameter**

| Parameter     | Description           |
|---------------|-----------------------|
| `workspaceId` | UUID of the workspace |

### **Headers**

| Header        | Value          |
|---------------|----------------|
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
      "lastName": "Mansour"
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
          "startingTimestamp": "2025-05-01T10:00:00",
          "endingTimestamp": "2025-05-02T18:00:00",
          "isFavorite": false,
          "assignedTo": {
            "id": "user-uuid",
            "username": "najat-mansour",
            "firstName": "Najat",
            "lastName": "Mansour",
            "email": "mansournajat7@gmail.com",
            "birthdate": "2003-01-28",
            "gender": "FEMALE",
            "address": {
              "country": "Palestine",
              "city": "Nablus",
              "town": "",
              "street": ""
            },
            "createdAt": "2025-05-02T18:00:00",
            "app_rate": 5
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
|---------------|-----------------------|
| `workspaceId` | UUID of the workspace |

### **Headers**

| Header        | Value          |
|---------------|----------------|
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
POST /task-go/v1/workspaces/{workspaceId}/viewers
```

### **Path Parameter**

| Parameter     | Description           |
|---------------|-----------------------|
| `workspaceId` | UUID of the workspace |

### **Headers**

| Header        | Value          |
|---------------|----------------|
| Authorization | Bearer `{JWT}` |

### **Request Body**

```json
{
  "viewerId": "user-id-to-add"
}
```

### **Responses**

#### ✅ 201 Created

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

## <mark>Delete User from Workspace</mark>

### **Endpoint**

```http
DELETE /task-go/v1/workspaces/{workspaceId}/viewers/{viewerId}
```

### **Path Parameter**

| Parameter     | Description                |
|---------------|----------------------------|
| `workspaceId` | UUID of the workspace      |
| `viewerId`    | UUID of the user to remove |

### **Headers**

| Header        | Value          |
|---------------|----------------|
| Authorization | Bearer `{JWT}` |

### **Responses**

#### ✅ 204 No Content

```json
{
  "message": "User deleted from workspace successfully."
}
```

####  ️⚠️ 400 Bad Request – Mismatching

```json
{
  "error": "The user is not a member of this workspace."
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