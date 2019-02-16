package com.note.api.noteitapi.api.viewmodel;

import javax.validation.constraints.NotNull;

public class NotebookViewModel {

    private String id;

    @NotNull
    private String name;

    private int noOfNotes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoOfNotes() {
        return noOfNotes;
    }

    public void setNoOfNotes(int noOfNotes) {
        this.noOfNotes = noOfNotes;
    }
}
