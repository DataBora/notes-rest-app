# RESTful Application for Notes

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

### Endpoints
- **User Endpoints:**<br>
POST /create : Creates a new user.<br>
PUT /update/{id} : Updates the user information for the specified user ID.<br>
DELETE /delete/{id} : Deletes the user with the specified user ID.<br>
GET /users : Retrieves a list of all users.<br>
GET /users/{id} : Retrieves user details for the specified user ID.<br>
- **Note endpoints:**<br>
POST /create : Creates a new note.<br>
PUT /update/{id} : Updates the note information for the specified note ID.<br>
DELETE /delete/{id} : Deletes the note with the specified note ID.<br>
GET /notes : Retrieves a list of all notes.<br>
GET /notes/{id} : Retrieves note details for the specified note ID.<br>
- **Tag endpoints:**<br>
POST /create_tag/{note_id} : Creates a new tag and associates it with the specified note ID.<br>
DELETE /notes/{note_id}/tags/{tag_id} : Deletes the tag with the specified tag ID associated with the specified note ID.<br>
GET /tags : Retrieves a list of all tags.<br>
GET /tags/search : Searches for tags by partial matching based on a search query.<br>
GET /tags/note/{note_id} : Retrieves tags associated with the specified note ID.<br>

- **Database Schema:**

![SemaZaNotes](https://github.com/DataBora/notes-rest-app/assets/94956337/399047bc-9474-463d-be55-b1e697eb274b)
