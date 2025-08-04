# Statistics

## <mark>Get User Statistics</mark>

### **Endpoint**

```http
GET /task-go/v1/statistics/{userId}
```

### **Request Headers**

| Header        | Value          |
|---------------|----------------|
| Authorization | Bearer `<JWT>` |

### **Path Parameters**

| Parameter | Type   | Description                                  |
|-----------|--------|----------------------------------------------|
| `userId`  | string | UUID of the user to retrieve statistics for. |

### **Possible Responses**

#### ✅ 200 OK

```json
{
  "workspaces": {
    "ownedCount": 5,
    "viewedCount": 3
  },
  "assignedTasks": {
    "notStarted": 3,
    "inProgress": 2,
    "pending": 0,
    "finished": 2,
    "total": 7
  }
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