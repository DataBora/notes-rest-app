package com.bizanaliza.app.notes.Service;

import com.bizanaliza.app.notes.Model.Note;
import com.bizanaliza.app.notes.Model.Priority;
import com.bizanaliza.app.notes.Utils.PriorityDescription;
import org.springframework.stereotype.Service;

import java.util.List;


public interface NoteService {

    public void createNote(Note note, long user_id);
    public void updateNote(long id, Note note, long user_id);
    public void deleteNote(long id, long user_id);
    public Note getNote(long id, long user_id);
    public List<Note> getAllNotes(long user_id);

    public List<Note> findNoteByPartialTitleMatching(String title);

    public List<Note> findByPriority(Priority priority);

}
