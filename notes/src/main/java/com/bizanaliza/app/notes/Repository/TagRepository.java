package com.bizanaliza.app.notes.Repository;

import com.bizanaliza.app.notes.Model.Note;
import com.bizanaliza.app.notes.Model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("SELECT t FROM Tag t JOIN t.notes n WHERE n.id = :note_id AND t.id = :id")
    Optional<Tag> findByIdAndNoteId(@Param("id") long id, @Param("note_id") long note_id);

    @Query("SELECT t FROM Tag t JOIN t.notes n WHERE n.id = :note_id")
    List<Tag> findByNoteId(@Param("note_id") long note_id);

//    @Query("select t from Tag t where t.value like concat('%', :value, '%')")
    @Query("SELECT t FROM Tag t WHERE t.value LIKE %:value%")
    List<Tag> findTagByPartialTitleMatching(@Param("value") String value);

    @Query("select t from Tag t")
    List<Tag> findAllTags();
}
