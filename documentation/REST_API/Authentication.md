# Authentication

## <mark>Login</mark>

### **Endpoint**

```http
POST /task-go/v1/auth/login
```

### **Request Body**

```json
{
  "username": "najat-mansour",
  "password": "Najat@Mansour28"
}
```

### **Possible Responses**

#### ✅ 200 OK

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE3MDM3MjkzNzIsImV4cCI6MTcwMzczNjU3Mn0.oU3fsini19DS_BKpJ_4LZ3xENPiL4rMSrYCdJCQ1C04"
}
```

#### ⚠️ 400 Bad Request

```json
{
  "error": "Invalid username or password."
}
```

## <mark>Email Verification</mark>

### **Endpoint**

```http
POST /task-go/v1/auth/email-verification
```

### **Request Body**

```json
{
  "email": "mansournajat7@gmail.com"
}
```

### **Possible Responses**

#### ✅ 200 OK

```json
{
  "code": "1234"
}
```

#### ⚠️ 400 Bad Request

```json
{
  "error": "Email address not found."
}
```

## <mark>Forgot Password</mark>

### **Endpoint**

```http
POST /task-go/v1/auth/forgot-password
```

### **Request Body**

```json
{
  "username": "najat-mansour"
}
```

### **Possible Responses**

#### ✅ 200 OK

```json
{
  "message": "Password reset instructions sent to the user's email."
}
```

#### ⚠️ 400 Bad Request

```json
{
  "error": "Invalid or missing username."
}
```

#### ❌ 404 Not Found

```json
{
  "error": "User not found with this username."
}
```