package com.note.api.noteitapi.api;

import com.note.api.noteitapi.Mapper;
import com.note.api.noteitapi.api.viewmodel.NotebookViewModel;
import com.note.api.noteitapi.db.NoteBookRepository;
import com.note.api.noteitapi.model.NoteBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notebook")
@CrossOrigin
public class NoteBookController {

    @Autowired
    private NoteBookRepository noteBookRepository;

    @Autowired
    private Mapper mapper;

    @GetMapping("/all")
    public List<NotebookViewModel> getAllNoteBooks(){
        List<NotebookViewModel> notebookList = noteBookRepository.findAll().stream()
                                                .map(noteBook -> mapper.convertToNoteBookModel(noteBook))
                                                .collect(Collectors.toList());

        return notebookList;
    }

    @GetMapping("/{notebookId}")
    public NotebookViewModel getNoteBookById(@PathVariable String notebookId) {
        NotebookViewModel notebookModel = mapper.convertToNoteBookModel(noteBookRepository.findById(UUID.fromString(notebookId)).get());
        return notebookModel;
    }


    @PostMapping("/save")
    public NoteBook saveNoteBook(@RequestBody NotebookViewModel notebookViewModel, BindingResult bindingResult) throws ValidationException {
        if(bindingResult.hasErrors()){
            throw new ValidationException("NoteBook Content has Errors");
        }
        NoteBook noteBook = mapper.convertToNoteBookEntity(notebookViewModel);
        noteBookRepository.save(noteBook);

        return noteBook;
    }

    @DeleteMapping("{id}")
    public void deleteNoteBoodById(@PathVariable String id){
        noteBookRepository.deleteById(UUID.fromString(id));
    }
}
