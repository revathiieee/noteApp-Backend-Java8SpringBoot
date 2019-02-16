package com.note.api.noteitapi;

import com.note.api.noteitapi.api.viewmodel.NoteViewModel;
import com.note.api.noteitapi.api.viewmodel.NotebookViewModel;
import com.note.api.noteitapi.db.NoteBookRepository;
import com.note.api.noteitapi.model.Note;
import com.note.api.noteitapi.model.NoteBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Mapper {

    @Autowired
    private NoteBookRepository noteBookRepository;

   public Note convertToNoteEntity(NoteViewModel noteViewModel){

       NoteBook noteBook = noteBookRepository.findById(UUID.fromString(noteViewModel.getNotebookId())).get();
       Note note = new Note(noteViewModel.getId(),noteViewModel.getTitle(),noteViewModel.getText(),noteBook);
       return note;
   }

   public NoteViewModel convertToNoteModel(Note note){
       NoteViewModel noteViewModel = new NoteViewModel();
       noteViewModel.setId(note.getId().toString());
       noteViewModel.setLastModifiedOn(note.getLastModifiedOn());
       noteViewModel.setTitle(note.getTitle());
       noteViewModel.setText(note.getText());
       noteViewModel.setNotebookId(note.getNoteBook().getId().toString());

       return noteViewModel;
   }

   public NoteBook convertToNoteBookEntity(NotebookViewModel notebookViewModel){
    NoteBook notebook =  new NoteBook( notebookViewModel.getId(),notebookViewModel.getName());
    return notebook;
   }

   public NotebookViewModel convertToNoteBookModel(NoteBook noteBook){
       NotebookViewModel notebookViewModel = new NotebookViewModel();

       notebookViewModel.setId(noteBook.getId().toString());
       notebookViewModel.setName(noteBook.getName());
       notebookViewModel.setNoOfNotes(noteBook.getNotes().size());

       return  notebookViewModel;

   }
}
