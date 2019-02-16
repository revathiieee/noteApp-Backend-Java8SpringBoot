package com.note.api.noteitapi.db;

import com.note.api.noteitapi.model.NoteBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NoteBookRepository extends JpaRepository<NoteBook, UUID> {
}
