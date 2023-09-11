package com.bizanaliza.app.notes.Service.Implementation;

import com.bizanaliza.app.notes.Exception.Note.*;
import com.bizanaliza.app.notes.Model.Note;
import com.bizanaliza.app.notes.Model.Priority;
import com.bizanaliza.app.notes.Model.User;
import com.bizanaliza.app.notes.Repository.NoteRepository;
import com.bizanaliza.app.notes.Service.NoteService;
import com.bizanaliza.app.notes.Utils.PriorityDescription;
import com.bizanaliza.app.notes.Utils.PriorityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImplementation implements NoteService {

    NoteRepository noteRepository;

    public NoteServiceImplementation(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    //BUSINESS LOGIC
    @Override
    public void createNote(Note note, long user_id) {
        if (note.getTitle().length() > 30) {
            throw new NoteTitleTooLongException("Note title is longer than 30 characters.");
        }
        if (note.getContent().length() > 1000) {
            throw new NoteContentIsTooLongException("Note content exceeds 1000 characters");
        }
        note.setUser(new User(user_id));
        noteRepository.save(note);
    }

    @Override
    public void updateNote(long id, Note note, long user_id) {

        Optional<Note> existingNoteOptional = noteRepository.findById(id);

        if (existingNoteOptional.isEmpty()) {
            throw new NoteNotFoundException("Note with the ID: " + id + " is not found");
        }

        Note existingNote = existingNoteOptional.get();

        if (existingNote.getUser().getId() != user_id) {
            throw new NoteUnauthorizedException("Note is not associate with user: " + user_id);
        }

        existingNote.setTitle(note.getTitle());
        existingNote.setContent(note.getContent());

        noteRepository.save(existingNote);

    }

    @Override
    public void deleteNote(long id, long user_id) {

        Optional<Note> existingNoteOptional = noteRepository.findById(id);

        if (existingNoteOptional.isEmpty()) {
            throw new NoteNotFoundException("Note with the ID: " + id + " is not found");
        }

        Note existingNote = existingNoteOptional.get();

        if (existingNote.getUser().getId() != user_id) {
            throw new NoteUnauthorizedException("Note is not associate with user: " + user_id);
        }

        noteRepository.deleteById(id);
    }

    @Override
    public Note getNote(long id, long user_id) {

        Optional<Note> noteOptional = noteRepository.findByIdAndUserId(id, user_id);

        if (noteOptional.isEmpty()) {
            throw new NoteNotFoundException("Note with ID " + id + " not found for user " + user_id);
        }

        return noteOptional.get();
    }

    @Override
    public List<Note> getAllNotes(long user_id) {

        List<Note> userNotes = noteRepository.findByUserId(user_id);

        if (userNotes.isEmpty()) {
            throw new NoteNotFoundException("No notes found for user with ID " + user_id);
        }
        return userNotes;
    }

    @Override
    public List<Note> findNoteByPartialTitleMatching(String title) {

        List<Note> matchingNotes = noteRepository.findNoteByPartialTitleMatching(title);

        if (matchingNotes.isEmpty()) {
            // Return null or an appropriate response based on your requirements
            throw new NoteNotFoundException("There is no note like that in here.");
        }
        return matchingNotes;

    }

    @Override
    public List<Note> findByPriority(Priority priority) {
        List<Note> notes = noteRepository.findByPriority(priority);
        if (notes.isEmpty()) {
            throw new NotePriorityNotFoundException("No notes found with priority: " + priority);
        }
        return notes;
    }
}
