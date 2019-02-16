package com.note.api.noteitapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class NoteBook {
    @Id
    private UUID id;
    private String name;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "noteBook",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Note> notes;

    protected NoteBook() {
        this.id = UUID.randomUUID();
        this.notes = new ArrayList<>();
    }

    public NoteBook(String name) {
        this();
        this.name = name;
    }

    public NoteBook(String id, String name) {
        this();
        if(id != null){
            this.id = UUID.fromString(id);
        }
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public int getNoOfNotes(){
        return this.notes.size();
    }
}
