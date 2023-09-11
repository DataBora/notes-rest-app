package com.bizanaliza.app.notes.Controler;

import com.bizanaliza.app.notes.Exception.Note.*;
import com.bizanaliza.app.notes.Model.Note;
import com.bizanaliza.app.notes.Model.Priority;
import com.bizanaliza.app.notes.Response.NoteResponseHandler;
import com.bizanaliza.app.notes.Service.NoteService;
import com.bizanaliza.app.notes.Utils.PriorityDescription;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    //REST
    //get for returning all users notes
    @GetMapping(value = "/user/{id}")
    public List<Note> getAllNotes(@PathVariable("id") long user_id) {
        return noteService.getAllNotes(user_id);
    }

    //get for returning note by id and user id
    @GetMapping(value = "/users/{user_id}/notes/{note_id}")
    public ResponseEntity<Object> getNote(
            @PathVariable("user_id") long user_id,
            @PathVariable("note_id") long note_id
    ) {
        try {
            Note note = noteService.getNote(note_id, user_id);
            return NoteResponseHandler.responseBuilder("Note retrieved successfully", HttpStatus.OK, note);
        } catch (NoteNotFoundException e) {
            // Handle the case where the note is not found
            return NoteResponseHandler.responseBuilder("Note not found", HttpStatus.NOT_FOUND, null);
        }
    }

    //get for partial title matching
    @GetMapping(value = "/search/notes")
    public ResponseEntity<Object> searchNotesByPartialTitle(@RequestParam("title") String title) {
        List<Note> matchingNotes = noteService.findNoteByPartialTitleMatching(title);

        return ResponseEntity.ok(matchingNotes);
    }

    //get for search by priorities
    @GetMapping(value = "/search/priority")
    public ResponseEntity<Object> searchByPriority(@RequestParam("priority") String priorityStr) {
        try {
            // Check if the provided priority string matches any enum values
            Priority priority = Priority.valueOf(priorityStr.toUpperCase());

            List<Note> notes = noteService.findByPriority(priority);

            return ResponseEntity.ok(notes);
        } catch (IllegalArgumentException e) {
            // Handle the case where the provided priority is not valid
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid priority: " + priorityStr + " Options are: 'HIGH', 'MEDIUM', 'LOW'");
        } catch (NotePriorityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //post for creating the note
    @PostMapping(value = "/create_note/{user_id}")
    public ResponseEntity<Object> createNote(@RequestBody Note note,
                                             @PathVariable("user_id") long user_id){
        try {
            noteService.createNote(note, user_id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Note is successfully created!");
        }catch (NoteTitleTooLongException e) {
            return NoteResponseHandler.responseBuilder("Note title cant be bigger than 30 characters",HttpStatus.FORBIDDEN, null);
        }catch (NoteContentIsTooLongException e){
            return NoteResponseHandler.responseBuilder("Note content is bigger than 1000 characters",HttpStatus.FORBIDDEN,null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create the note: " + e.getMessage());
        }
    }

    //put for updating the note
    @PutMapping(value = "/update_note/note/{id}/user/{user_id}")
    public ResponseEntity<Object> updateNote(@PathVariable("id") long id,
                                             @PathVariable("user_id") long user_id,
                                             @RequestBody Note note){
        try{
            noteService.updateNote(id, note,user_id);
            return ResponseEntity.status(HttpStatus.OK).body("Note updated successfully!");
        } catch (NoteNotFoundException e){
            return NoteResponseHandler.responseBuilder("Note with that ID does not exist.", HttpStatus.NOT_FOUND, null);
        } catch (NoteUnauthorizedException e){
            return NoteResponseHandler.responseBuilder("Unauthorized to update the note.", HttpStatus.FORBIDDEN, null);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update the note: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete_note/note/{id}/user/{user_id}")
    public ResponseEntity<Object> deleteNote(@PathVariable("id") long id,
                                             @PathVariable("user_id") long user_id){
        try{
            noteService.deleteNote(id, user_id);
            return ResponseEntity.status(HttpStatus.OK).body("Note deleted successfully!");
        } catch (NoteNotFoundException e){
            return NoteResponseHandler.responseBuilder("Note with that ID does not exist.", HttpStatus.NOT_FOUND, null);
        } catch (NoteUnauthorizedException e){
            return NoteResponseHandler.responseBuilder("Unauthorized to delete the note.", HttpStatus.FORBIDDEN, null);
        }
    }

}
