# Statistics

## <mark>Get User Statistics</mark>

### **Endpoint**

```http
GET /api/statistics/{userId}
```

### **Request Headers**

| Header        | Value          |
| ------------- | -------------- |
| Authorization | Bearer `<JWT>` |

### **Path Parameters**

| Parameter | Type   | Description                     |
| --------- | ------ | ------------------------------- |
| `userId`  | string | UUID of the user to retrieve statistics for. |

### **Possible Responses**

#### ✅ 200 OK

```json
{
  "workspaces": [
    {
      "workspaceId": "string",
      "workspaceName": "string",
      "memberCount": "number"
    }
  ],
  "invitedWorkspacesCount": "number",
  "createdTasksCount": "number",
  "assignedTasksCount": "number"
}
```

#### ⚠️ 400 Bad Request

```json
{
  "error": "Invalid userId provided."
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