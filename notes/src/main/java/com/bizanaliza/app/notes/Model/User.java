package com.bizanaliza.app.notes.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String nickname;
    @Column
    private String username;
    @Column
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference // Indicates this side is the "parent" side // @JsonIgnore
    private Set<Note> notes = new HashSet<>();

    public User() {
    }

    // Constructor that accepts a long ID for notes
    public User(long id) {
        this.id = id;
    }

    public User(long id, String nickname, String username, String password, Set<Note> notes) {
        this.id = id;
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }
}
