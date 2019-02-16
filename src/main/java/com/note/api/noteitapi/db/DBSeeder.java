package com.note.api.noteitapi.db;

import com.note.api.noteitapi.model.Note;
import com.note.api.noteitapi.model.NoteBook;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * This component will only execute (and get instantiated) if the
 * property noteit.db.recreate is set to true in the
 * application.properties file
 */
@Component
@ConditionalOnProperty(name = "noteit.db.recreate",havingValue = "true")
public class DBSeeder implements CommandLineRunner {

    private NoteRepository noteRepository;
    private NoteBookRepository noteBookRepository;

    public DBSeeder(NoteRepository noteRepository, NoteBookRepository noteBookRepository) {
        this.noteRepository = noteRepository;
        this.noteBookRepository = noteBookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //Delete both Repository
        this.noteRepository.deleteAll();
        this.noteBookRepository.deleteAll();

        NoteBook defaultNoteBook = new NoteBook("Default");
        this.noteBookRepository.save(defaultNoteBook);

        NoteBook quotesNoteBook = new NoteBook("Hello");
        this.noteBookRepository.save(quotesNoteBook);

        Note defaultNote = new Note("Colouring Book","Kids Entertainment",defaultNoteBook);
        this.noteRepository.save(defaultNote);

        Note helloNote = new Note("Cooking Book","Adults Entertainment",quotesNoteBook);
        this.noteRepository.save(helloNote);

        System.out.println("Initialized  Note database");
    }
}
