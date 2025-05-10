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
PATCH /task-go/v1/workspaces/{workspaceId}/groups/{groupId}
```

### **Request Headers**

| Header        | Value          |
| ------------- | -------------- |
| Authorization | Bearer `<JWT>` |

### **Path Parameters**

| Parameter     | Type   | Description           |
| ------------- | ------ | --------------------- |
| `workspaceId` | string | UUID of the workspace |
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
  "error": "Group not found in workspace."
}
```

## <mark>Delete Group</mark>

### **Endpoint**

```http
DELETE /task-go/v1/workspaces/{workspaceId}/groups/{groupId}
```

### **Request Headers**

| Header        | Value          |
| ------------- | -------------- |
| Authorization | Bearer `<JWT>` |

### **Path Parameters**

| Parameter     | Type   | Description           |
| ------------- | ------ | --------------------- |
| `workspaceId` | string | UUID of the workspace |
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
GET /task-go/v1/workspaces/{workspaceId}/groups/{groupId}
```

### **Request Headers**

| Header        | Value          |
| ------------- | -------------- |
| Authorization | Bearer `<JWT>` |

### **Path Parameters**

| Parameter     | Type   | Description           |
| ------------- | ------ | --------------------- |
| `workspaceId` | string | UUID of the workspace |
| `groupId`     | string | UUID of the group     |

### **Possible Responses**

#### ✅ 200 OK

```json
{
  "id": "b2e7129b-124f-4e9e-a52c-e1c215ff21a2",
  "name": "Development Team",
  "description": "Handles all development tasks",
  "createdAt": "2025-04-28T14:00:00",
  "tasks": [
    {
      "id": "t1",
      "title": "Setup project",
      "description": "Initialize Git repo and base structure",
      "owner": {
        "id": "u1",
        "username": "najat-mansour",
        "firstName": "Najat",
        "lastName": "Mansour"
      },
      "subtasks": [
        {
          "id": "st1",
          "title": "Create GitHub repo",
          "description": "Set up the GitHub repository"
        },
        ...
      ]
    },
    ...
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