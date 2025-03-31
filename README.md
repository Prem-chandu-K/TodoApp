# TodoApp - A Simple Task Management Application

## Overview
TodoApp is a Spring Boot-based task management application that allows users to create, update, and manage their daily tasks efficiently. The application includes authentication features to ensure secure access and personalized task management.

## Features
- ✅ User Authentication (Sign Up, Login)
- ✅ Create, Update, Delete, and View Todos
- ✅ Secure API with Spring Security
- ✅ MySQL Database Integration
- ✅ Postman as Client
- ✅ Token-Based Authentication
- ✅ Error Handling and Security Measures

## Technology Stack
- **Backend:** Spring Boot, Spring Security, JPA, MySQL
- **Client:** Postman (for API interaction)
- **Tools:** GitHub (for version control)

## API Endpoints

### Authentication Endpoints
| Method | Endpoint         | Description        |
|--------|-----------------|--------------------|
| POST   | /auth/login     | User login        |
| POST   | /auth/register  | User registration |

### Todo Endpoints
| Method | Endpoint         | Description        |
|--------|-----------------|--------------------|
| GET    | /api/todos      | Get all todos     |
| POST   | /api/todos      | Create a new todo |
| PUT    | /api/todos/{id} | Update a todo     |
| DELETE | /api/todos/{id} | Delete a todo     |

## Process Implementation

### User Registration
#### Request
```json
POST /auth/register
{
  "name": "John Doe",
  "email": "john@doe.com",
  "password": "password"
}
```
#### Response
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"
}
```
- Validates user details, ensures email uniqueness, and securely hashes passwords before storage.

### User Login
#### Request
```json
POST /auth/login
{
  "email": "john@doe.com",
  "password": "password"
}
```
#### Response
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"
}
```

### Create a To-Do Item
#### Request
```json
POST /api/todos
{
  "title": "Buy groceries",
  "description": "Buy milk, eggs, and bread"
}
```
- Requires authentication via an Authorization header.
- Responds with 401 Unauthorized if token is missing or invalid.

#### Response
```json
{
  "id": 1,
  "title": "Buy groceries",
  "description": "Buy milk, eggs, and bread"
}
```

### Update a To-Do Item
#### Request
```json
PUT /api/todos/1
{
  "title": "Buy groceries",
  "description": "Buy milk, eggs, bread, and cheese"
}
```
- Requires authentication.
- Ensures only the creator can update their own to-do item.
- Responds with 403 Forbidden if unauthorized.

#### Response
```json
{
  "id": 1,
  "title": "Buy groceries",
  "description": "Buy milk, eggs, bread, and cheese"
}
```

### Delete a To-Do Item
#### Request
```json
DELETE /api/todos/1
```
- Requires authentication and authorization.
- Responds with 204 No Content upon successful deletion.

### Get To-Do Items
#### Request
```json
GET /api/todos
```
- Requires authentication.
- Returns all available todos.

#### Response
```json
{
  "data": [
    {
      "id": 1,
      "title": "Buy groceries",
      "description": "Buy milk, eggs, bread"
    },
    {
      "id": 2,
      "title": "Pay bills",
      "description": "Pay electricity and water bills"
    }
  ]
}
```

## Notes
- The logout functionality is currently tested using **Postman** as there is no dedicated logout page.
- Implements JWT for authentication and security.

