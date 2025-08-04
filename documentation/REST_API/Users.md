# Users

## <mark>Registration (Sign Up)</mark>

### **Endpoint**

```http
POST /task-go/v1/users/
```

### **Request Body**

```json
{
  "username": "najat-mansour",
  "password": "Najat#Mansour28",
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
  }
}
```

### **Possible Responses**

#### ✅ 201 Created

```json
{
  "message": "User registered successfully."
}
```

#### ⚠️ 400 Bad Request

```json
{
  "error": "Weak Password."
}
```

#### ⚠️ 409 Conflict

```json
{
  "error": "Username or email already exists."
}
```

## <mark>Update User Information</mark>

### **Endpoint**

```http
PATCH /task-go/v1/users/{id}
```

### **Headers**

| Header        | Value          |
|---------------|----------------|
| Authorization | Bearer `{JWT}` |

### **Path Parameters**

| Parameter | Type   | Description                     |
|-----------|--------|---------------------------------|
| `id`      | string | UUID of the user to be updated. |

### **Request Body**

```json
{
  "password": "Najat#Mansour28",
  "firstName": "Najat",
  "lastName": "Mansour",
  "birthdate": "2003-01-28",
  "gender": "FEMALE",
  "address": {
    "country": "Palestine",
    "city": "Nablus",
    "town": "",
    "street": ""
  },
  "app_rate": 5
}
```

> All fields are optional.

### **Possible Responses**

#### ✅ 200 OK

```json
{
  "message": "User information updated successfully."
}
```

#### ⚠️ 400 Bad Request

```json
{
  "error": "Invalid input format."
}
```

#### ⚠️ 400 Bad Request

```json
{
  "error": "Weak Password."
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

## <mark>Get All Users</mark>

### **Endpoint**

```http
GET /task-go/v1/users/
```

### **Headers**

| Header        | Value          |
|---------------|----------------|
| Authorization | Bearer `<JWT>` |

### **Possible Responses**

#### ✅ 200 OK

```json
[
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
]
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
  "error": "No users found."
}
```

## <mark>Get User by ID</mark>

### **Endpoint**

```http
GET /task-go/v1/users/id/{id}
```

### **Headers**

| Header        | Value          |
|---------------|----------------|
| Authorization | Bearer `<JWT>` |

### **Path Parameters**

| Parameter | Type   | Description       |
|-----------|--------|-------------------|
| `id`      | string | UUID of the user. |

### **Possible Responses**

#### ✅ 200 OK

```json
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

## <mark>Get User by Username</mark>

### **Endpoint**

```http
GET /task-go/v1/users/username/{username}
```

### **Headers**

| Header        | Value          |
|---------------|----------------|
| Authorization | Bearer `<JWT>` |

### **Path Parameters**

| Parameter  | Type   | Description                   |
|------------|--------|-------------------------------|
| `username` | string | Username of the user to find. |

### **Possible Responses**

#### ✅ 200 OK

```json
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
  "error": "User not found with this username."
}
```

## <mark>Get User by Email</mark>

### **Endpoint**

```http
GET /task-go/v1/users/email/{email}
```

### **Headers**

| Header        | Value          |
|---------------|----------------|
| Authorization | Bearer `<JWT>` |

### **Path Parameters**

| Parameter | Type   | Description        |
|-----------|--------|--------------------|
| `email`   | string | Email of the user. |

### **Possible Responses**

#### ✅ 200 OK

```json
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
  "error": "User not found with this email."
}
```
