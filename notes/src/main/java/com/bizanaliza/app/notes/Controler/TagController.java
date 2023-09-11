package com.bizanaliza.app.notes.Controler;

import com.bizanaliza.app.notes.Exception.Note.NoteNotFoundException;
import com.bizanaliza.app.notes.Exception.Tag.TagNotFoundException;
import com.bizanaliza.app.notes.Exception.Tag.TagTooLongException;
import com.bizanaliza.app.notes.Model.Note;
import com.bizanaliza.app.notes.Model.Tag;
import com.bizanaliza.app.notes.Model.User;
import com.bizanaliza.app.notes.Response.NoteResponseHandler;
import com.bizanaliza.app.notes.Response.TagResponseHandler;
import com.bizanaliza.app.notes.Service.NoteService;
import com.bizanaliza.app.notes.Service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class TagController {

    TagService tagService;

    public TagController(TagService tagService, NoteService noteService) {
        this.tagService = tagService;
    }

    //REST
    //get to return all tags
    @GetMapping(value = "/tags")
    public ResponseEntity<Object> findAllTags() {
        try {
            List<Tag> tags = tagService.findAllTags();
            return ResponseEntity.ok(tags);
        } catch (TagNotFoundException e) {
            return TagResponseHandler.responseBuilder("Tag not found", HttpStatus.NOT_FOUND, null);
        }
    }

    //get tags by partial matching
    @GetMapping("/tags/search")
    public ResponseEntity<Object> findTagByPartialMatching(@RequestParam("value") String value) {
        try {
            List<Tag> tags = tagService.findTagByPartialMatching(value);
            return ResponseEntity.ok(tags);
        } catch (TagNotFoundException e) {
            return TagResponseHandler.responseBuilder("Tag not found", HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to retrieve tags: " + e.getMessage());
        }
    }

    //get tag by note id
    @GetMapping("/tags/note/{note_id}")
    public ResponseEntity<Object> findAllTagsByNoteId(@PathVariable("note_id") long note_id) {
        try {
            List<Tag> tags = tagService.findAllTagsByNoteId(note_id);
            return ResponseEntity.ok(tags);
        } catch (TagNotFoundException e) {
            return TagResponseHandler.responseBuilder("Tag not found", HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to retrieve tags: " + e.getMessage());
        }
    }

    @PostMapping("/create_tag/{note_id}")
    public ResponseEntity<Object> createTag(@RequestBody Tag tag, @PathVariable("note_id") long note_id){
        try {

            tagService.createTag(tag, note_id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Tag is successfully created!");
        } catch (TagTooLongException e){
            return TagResponseHandler.responseBuilder("Tag exceeds 20 characters", HttpStatus.FORBIDDEN, null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create tag: " + e.getMessage());
        }
    }


    //delete tag by note id and tag id
    @DeleteMapping("/notes/{note_id}/tags/{tag_id}")
    public ResponseEntity<Object> deleteTag(@PathVariable("note_id") long note_id, @PathVariable("tag_id") long tag_id) {
        try {
            tagService.deleteTag(tag_id, note_id);
            return ResponseEntity.ok("Tag deleted successfully.");
        } catch (TagNotFoundException e) {
            return TagResponseHandler.responseBuilder("Tag not found", HttpStatus.NOT_FOUND, null);
        } catch (NoteNotFoundException e) {
            return NoteResponseHandler.responseBuilder("Note not found", HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete tag: " + e.getMessage());
        }
    }



}
