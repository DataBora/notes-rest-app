package com.bizanaliza.app.notes.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @Column
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference // Indicates this side is the "child" side
    private User user;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "note_tag",  // Name of the intermediate table
            joinColumns = @JoinColumn(name = "note_id"),  // Foreign key in intermediate table for Note
            inverseJoinColumns = @JoinColumn(name = "tag_id")  // Foreign key in intermediate table for Tag
    )
    private Set<Tag> tags = new HashSet<>();

    public Note() {
    }

    public Note(long id, String title, String content, Priority priority, User user, Set<Tag> tags) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.priority = priority;
        this.user = user;
        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
