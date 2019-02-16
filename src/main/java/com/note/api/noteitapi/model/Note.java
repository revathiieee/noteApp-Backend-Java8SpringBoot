package com.note.api.noteitapi.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.UUID;

@Entity
public class Note {

    @Id
    private UUID id;
    private String title;
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private NoteBook noteBook;

    private Date lastModifiedOn;

    protected Note() {
        this.id = UUID.randomUUID();
        this.lastModifiedOn = new Date();
    }

    public Note(String title, String text, NoteBook noteBook) {
        this();
        this.title = title;
        this.text = text;
        this.noteBook = noteBook;
    }

    public Note(String id, String title, String text, NoteBook noteBook) {
        this(title,text,noteBook);
        if (id != null) {
            this.id = UUID.fromString(id);
        }

    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public NoteBook getNoteBook() {
        return noteBook;
    }

    public void setNoteBook(NoteBook noteBook) {
        this.noteBook = noteBook;
    }

    public Date getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }
}
