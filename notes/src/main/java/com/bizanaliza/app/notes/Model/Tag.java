package com.bizanaliza.app.notes.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags")
@JsonIgnoreProperties("notes")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String value;

    @ManyToMany(mappedBy = "tags")  // Refers to the "tags" field in the Note entity
    private Set<Note> notes = new HashSet<>();

    public Tag() {
    }

    public Tag(long id, String value, Set<Note> notes) {
        this.id = id;
        this.value = value;
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }
}
