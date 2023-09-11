package com.bizanaliza.app.notes.Repository;

import com.bizanaliza.app.notes.Model.Note;
import com.bizanaliza.app.notes.Model.Priority;
import com.bizanaliza.app.notes.Utils.PriorityDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("select n from Note n where n.id = :id AND n.user.id = :user_id")
    Optional<Note> findByIdAndUserId(@Param("id") long id, @Param("user_id") long user_id);

    @Query("select n from Note n where n.user.id = :user_id")
    List<Note> findByUserId(@Param("user_id") long user_id);

    @Query("select n from Note n where n.title like concat('%', :title, '%')")
    List<Note> findNoteByPartialTitleMatching(@Param("title") String title);

    @Query("select n from Note n where n.priority = :priority")
    List<Note> findByPriority(Priority priority);


}
