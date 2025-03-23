Here’s the updated GitHub Markdown version with proper formatting:

# JWT Security Overview
This package implements **JWT-based authentication** in Spring Boot.

## 🔹 Key Components

### `JwtService`
- Generates, parses, and validates JWT tokens.  
- Uses a secret key (`JWT_SECRET`) from `application.properties`.  
- Implements token expiration and validation logic.  

### `JwtFilter`
- Extracts JWT from the `Authorization` header.  
- Validates the token and authenticates the user.  

## 🔹 How to Use

### 1️⃣ Set the `JWT_SECRET` in `application.properties`
```ini
JWT_SECRET=your-secret-key
```

### 2️⃣ Add the `Authorization` header when making API requests
```http
Authorization: Bearer your_jwt_token
```

## 🔹 Common Issues & Fixes

### ❌ Error: `JWT_SECRET` not found?
✅ Ensure it's correctly set in `application.properties`.

### ❌ Token expired?
✅ Check the expiration time in `JwtService`.