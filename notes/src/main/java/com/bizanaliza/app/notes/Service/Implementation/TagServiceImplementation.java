package com.bizanaliza.app.notes.Service.Implementation;

import com.bizanaliza.app.notes.Exception.Note.NoteNotFoundException;
import com.bizanaliza.app.notes.Exception.Tag.TagAlreadyExistsException;
import com.bizanaliza.app.notes.Exception.Tag.TagNotFoundException;
import com.bizanaliza.app.notes.Exception.Tag.TagTooLongException;
import com.bizanaliza.app.notes.Model.Note;
import com.bizanaliza.app.notes.Model.Tag;
import com.bizanaliza.app.notes.Repository.NoteRepository;
import com.bizanaliza.app.notes.Repository.TagRepository;
import com.bizanaliza.app.notes.Service.NoteService;
import com.bizanaliza.app.notes.Service.TagService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TagServiceImplementation implements TagService {

    private final TagRepository tagRepository;
    private final NoteRepository noteRepository;

    public TagServiceImplementation(TagRepository tagRepository, NoteRepository noteRepository) {
        this.tagRepository = tagRepository;
        this.noteRepository = noteRepository;



    }

    //BusinessLogic
    @Override
    @Transactional
    public void createTag(Tag tag, long note_id) {

        // Retrieve the note based on the note ID
        Note note = noteRepository.findById(note_id)
                .orElseThrow(() -> new NoteNotFoundException("Note not found"));


        if (tag.getValue().length() > 20) {
            throw new TagTooLongException("Tag exceeds 20 characters");
        }
        // Check if the tag already exists for the note
        if (note.getTags().contains(tag)) {
            throw new TagAlreadyExistsException("Tag already exists for the note");
        }

        // Associate the tag with the note
        tag.getNotes().add(note); // Add the Note to the Tag's Set of notes
        note.getTags().add(tag); // Add the Tag to the Note's Set of tags

        // Save both the tag and the note
        tagRepository.save(tag);
        noteRepository.save(note);


    }

    @Override
    @Transactional
    public void deleteTag(long tag_id, long note_id) {
        Note note = noteRepository.findById(note_id)
                .orElseThrow(() -> new NoteNotFoundException("Note not found with ID: " + note_id));

        Tag tag = tagRepository.findById(tag_id)
                .orElseThrow(() -> new TagNotFoundException("Tag not found with ID: " + tag_id));

        if (!note.getTags().contains(tag)) {
            throw new TagNotFoundException("Tag not associated with the specified note.");
        }

        // Remove the association between the note and the tag in the note_tag table
        note.getTags().remove(tag);
        tag.getNotes().remove(note);

        // Save the changes to the database
        noteRepository.save(note);
        tagRepository.save(tag);
    }

    @Override
    public List<Tag> findAllTagsByNoteId(long note_id) {
        return tagRepository.findByNoteId(note_id);
    }

    @Override
    public List<Tag> findAllTags() {
        return tagRepository.findAllTags();
    }

    @Override
    public List<Tag> findTagByPartialMatching(String value) {
        List<Tag> matchingTags = tagRepository.findTagByPartialTitleMatching(value);

        if (matchingTags.isEmpty()) {
            throw new TagNotFoundException("No tags found by partial matching.");
        }

        return matchingTags;
    }
}
