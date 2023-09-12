# RESTful Notes Application

## Technologies Used
- **Programming Languages:** Java (version 20)
- **Spring Framework:** Spring Boot (version 3.1.3)
- **Database:** MySQL
- **Development Tools:** IntelliJ IDEA, Maven
- **Dependencies and Libraries:**
  - Spring Data JPA
  - Spring Web
  - Hibernate
- **Version Control:** Git ([GitHub Repository](https://github.com/DataBora/notes-rest-app))
- **Deployment:** Local 8080
- **JSON Serialization:** Data is exchanged in JSON format, providing a lightweight and platform-independent means of data transfer.

### Key Endpoints

- `/notes`: Manages notes (CRUD operations).
- `/tags`: Manages tags (CRUD operations).
- `/users`: Manages users (CRUD operations).
- `/users/{userId}/notes/{noteId}/tags`: Endpoint for creating tags associated with a specific note.
- `/notes/{noteId}/tags`: Endpoint for retrieving tags associated with a specific note.

- **Database Schema:**

![SemaZaNotes](https://github.com/DataBora/notes-rest-app/assets/94956337/399047bc-9474-463d-be55-b1e697eb274b)
