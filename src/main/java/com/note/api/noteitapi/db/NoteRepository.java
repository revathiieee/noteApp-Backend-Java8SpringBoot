package com.note.api.noteitapi.db;

import com.note.api.noteitapi.model.Note;
import com.note.api.noteitapi.model.NoteBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NoteRepository extends JpaRepository<Note, UUID> {

    List<Note> findAllByNoteBook(NoteBook noteBook);
}
