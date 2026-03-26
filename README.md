# Library Management System with Auth

This is a personal project developed for educational purposes to practice building a secure REST API. It serves as a library management backend where books can be managed through a structured authentication and authorization flow.

## Technologies
* **Java 25**
* **Spring Boot 4.0.5**
* **Spring Security**
* **Spring Data JPA**
* **H2 Database** (In-memory for development)
* **Lombok**
* **Maven**

---

## How to Compile and Run

1. **Prerequisites**: Ensure you have **JDK 25** and **Maven** installed and configured in your environment.
2. **Clone the repository**:
   
   ```bash
   git clone https://github.com/AndreGGomes/Library-With-Auth.git
   ```
4. **Compile and Install dependecies**
   
   ```bash
   mvn clean install
   ```
5. **Run the application**
   
   ```bash
   mvn spring-boot:run
   ```
## Security & Roles

- Public: Can view the book list without logging in.

- Admin: Required for any state-changing operations (Create, Update, Delete).

## Initial Admin Credentials

The project is initialized via data.sql with default admin:

- **username**: Admin
- **Password**: admin123 (encripted via BCrypt)

  > **Note on Security:** The administrator credentials (username and password) are **not hardcoded** in the source code. They are securely retrieved from the database. The password stored in the database is encrypted using the **BCrypt** hashing algorithm, ensuring that plain-text passwords are never exposed.

## REST API Endpoints

| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :--- |
| **GET** | `/books/get` | Returns a list of all registered books | No |
| **POST** | `/books/save` | Registers a new book in the library | **Yes (ADMIN)** |
| **PUT** | `/books/update` | Updates an existing book's details | **Yes (ADMIN)** |
| **DELETE** | `/books/delete/{id}` | Removes a book from the database by ID | **Yes (ADMIN)** |

## Request Examples (JSON Body)

1. **Register a New Book (POST)**

Endpoint: `/books/save`

Auth: Basic Auth required.

```json
{
    "title": "Clean Code",
    "author": "Robert C. Martin",
    "ISBN": "978-8550804606"
}
```
2. **Update an Existing Book (PUT)**

Endpoint: `/books/update`

Auth: Basic Auth required.

Note: The id field is mandatory for updates.

```json
{
    "id": 1,
    "title": "Clean Code - Revised Edition",
    "author": "Robert C. Martin",
    "ISBN": "978-8550804606"
}
```
3. **Delete a Book (DELETE)**

Endpoint: `/books/delete/{id}`

Example: `/books/delete/1`

Auth: Basic Auth required.

4. **Get all books (GET)**
   
Endpoint: /books/get

Auth: No auth required.

---

## Testing the API
The endpoints were tested and validated using **Postman**. 

To test the administrative routes (POST, PUT, DELETE), make sure to:
1. Select the **Basic Auth** tab in Postman.
2. Enter the credentials: `Admin` / `admin123`.
3. Set the Body to **raw** and format to **JSON** for the requests that require it.


  
