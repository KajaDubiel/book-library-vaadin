package com.vaadin.simpleexercise.library;

import com.vaadin.simpleexercise.library.book.Book;
import com.vaadin.simpleexercise.library.book.BookDao;
import com.vaadin.simpleexercise.library.book.Library;
import com.vaadin.simpleexercise.library.book.LibraryDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryDaoTestSuite {
    @Autowired
    LibraryDao libraryDao;

    @Autowired
    BookDao bookDao;

    @Test
    public void addBookToLibrary(){
        //Given
        Book book1 = new Book("W pustyni i w puszczy", "Henryk Sienkiewicz", 1988);
        Book book2 = new Book("Pan Tadeusz", "Adam Mickiewicz", 1973);
        Book book3 = new Book("Lalka", "Bolesław Prus", 1976);
        Book book4 = new Book("Czysty kod", "Uncle Bob", 2009);


        /*bookDao.save(book1);
        bookDao.save(book2);
        bookDao.save(book3);
        bookDao.save(book4);*/

        Library library = new Library("Library v1");
        library.getBooks().add(book1);
        library.getBooks().add(book2);
        library.getBooks().add(book3);
        library.getBooks().add(book4);

        book1.setLibrary(library);
        book2.setLibrary(library);
        book3.setLibrary(library);
        book4.setLibrary(library);

        //When
        libraryDao.save(library);
    }
}
