
# CASE WORKER TASK MANAGEMENT SYSTEM (CWMS)
# CWMS Backend API Documentation
This project provides a RESTful backend API for the Case Worker Management System (CWMS).

---

## Base URL

```
http://localhost:8080/api/cwtasks
```

---

## API Endpoints (with examples)

###  1. Create a New Task

- **URL**: `/api/cwtasks`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "strTitle": "Test Title",
    "strDescription": "Test Task description goes here",
    "strStatus": "Active",
    "dueDateTime": "2025-04-30T17:00:00"
  }
  ```
- **Response**: `201 Created`
  ```json
  {
    "id": 1,
    "strTitle": "Test Title",
    "strDescription": "Test Task description goes here",
    "strStatus": "ACTIVE",
    "dueDateTime": "2025-04-30T17:00:00"
  }
  ```

---

###  2. Get Task by ID

- **URL**: `/api/cwtasks/{id}`
- **Method**: `GET`
- **Response**: `200 OK`
  ```json
  {
    "id": 1,
    "strTitle": "Test Title",
    "strDescription": "Test description goes here",
    "strStatus": "ACTIVE",
    "dueDateTime": "2025-04-30T17:00:00"
  }
  ```

---

###  3. Get All Tasks

- **URL**: `/api/cwtasks`
- **Method**: `GET`
- **Response**: `200 OK`
  ```json
  [
    {
      "id": 1,
      "strTitle": "First Task",
      "strDescription": "First task description",
      "strStatus": "COMPLETED",
      "dueDateTime": "2025-04-20T15:00:00"
    },
    {
      "id": 2,
      "strTitle": "Second Task",
      "strDescription": "Second task description",
      "strStatus": "IN ACTIVE",
      "dueDateTime": "2025-05-10T11:00:00"
    }
  ]
  ```

---

###  4. Update an Existing Task

- **URL**: `/api/cwtasks/{id}`
- **Method**: `PUT`
- **Request Body**:
  ```json
  {
    "strTitle": "Updated Task Title",
    "strDescription": "Updated description",
    "strStatus": "COMPLETED",
    "dueDateTime": "2025-05-01T12:00:00"
  }
  ```
- **Response**: `200 OK`
  ```json
  {
    "id": 1,
    "strTitle": "Updated Task Title",
    "strDescription": "Updated description",
    "strStatus": "COMPLETED",
    "dueDateTime": "2025-05-01T12:00:00"
  }
  ```

---

###  5. Delete a Task by ID

- **URL**: `/api/cwtasks/{id}`
- **Method**: `DELETE`
- **Response**: `200 OK`
  ```text
  Case worker Task with id {id} deleted successfully
  ```

---

## Status Codes

| Status Code | Meaning                      |
|-------------|-------------------------------|
| 200 OK      | Request successful            |
| 201 Created | Resource successfully created |
| 404 Not Found | Resource does not exist     |
| 400 Bad Request | Invalid input provided    |

---

## Notes

- Cross-Origin requests are allowed from: `http://localhost:4200`
- The API expects and returns JSON (`application/json`).
- Date-time format is ISO-8601 standard (e.g., `"2025-04-30T17:00:00"`).

---

#  Getting Started

To run the project locally:
1. Clone the repository.

2. After cloning the repository, run _(**docker-compose up -d**)_ in the terminal to start the MySQL database container.

   This ensures that the database is properly set up and ready to be used within the Dockerized environment
3. Import into an IDE (like IntelliJ or Eclipse).
4. Run the `CwmsBackendApplication` class.
5. API will be available at: `http://localhost:8080/api/cwtasks`


---

# 📢 Author

- Developed by MF Shabbir
  
---


