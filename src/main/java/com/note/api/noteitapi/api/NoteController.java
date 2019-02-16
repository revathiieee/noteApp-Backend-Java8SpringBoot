package com.note.api.noteitapi.api;

import com.note.api.noteitapi.Mapper;
import com.note.api.noteitapi.api.viewmodel.NoteViewModel;
import com.note.api.noteitapi.db.NoteBookRepository;
import com.note.api.noteitapi.db.NoteRepository;
import com.note.api.noteitapi.model.Note;
import com.note.api.noteitapi.model.NoteBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/notes")
@CrossOrigin
public class NoteController {

    @Autowired
    private NoteBookRepository noteBookRepository;
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private Mapper mapper;

    @GetMapping("/all")
    public List<NoteViewModel> getAllNotes(){

        List<NoteViewModel> noteViewModelList = noteRepository.findAll().stream()
                            .map(note -> mapper.convertToNoteModel(note))
                            .collect(Collectors.toList());

        return noteViewModelList;
    }

    @GetMapping("/byid/{id}")
    public NoteViewModel getNoteById(@PathVariable String id){
        Note note = noteRepository.findById(UUID.fromString(id)).orElse(null);

        if(note == null)
        {
            throw new EntityNotFoundException("Particular Note is not found based on Note Id");
        }

        NoteViewModel noteViewModel = mapper.convertToNoteModel(note);
        return noteViewModel;
    }

    @GetMapping("/byNoteBookId/{notebookId}")
    public List<NoteViewModel> getAllNotesByNotebookId(@PathVariable String notebookId){
        Optional<NoteBook> noteBook = noteBookRepository.findById(UUID.fromString(notebookId));
        List<Note> noteList = new ArrayList<>();

        if(noteBook.isPresent()){
            noteList = noteRepository.findAllByNoteBook(noteBook.get());
        }

        List<NoteViewModel> noteViewModelList = noteList.stream()
                                                .map(note -> mapper.convertToNoteModel(note))
                                                .collect(Collectors.toList());

        return noteViewModelList;
    }

    @PostMapping("/save")
    public Note saveNote(@RequestBody NoteViewModel noteModel, BindingResult bindingResult) throws ValidationException{
        if(bindingResult.hasErrors()){
            throw new ValidationException("Note Model has Errors.");
        }

        Note note = mapper.convertToNoteEntity(noteModel);
        noteRepository.save(note);

        return note;
    }

    @DeleteMapping("{id}")
    public void deleteNote(@PathVariable String id){
        noteRepository.deleteById(UUID.fromString(id));
    }

}
