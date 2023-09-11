package com.bizanaliza.app.notes.Service;

import com.bizanaliza.app.notes.Model.Note;
import com.bizanaliza.app.notes.Model.Tag;

import java.util.List;

public interface TagService {

    public void createTag(Tag tag, long note_id);
    public void deleteTag(long id, long note_id);
    public List<Tag> findAllTagsByNoteId(long note_id);
    public List<Tag> findAllTags();
    public List<Tag> findTagByPartialMatching(String value);


}
