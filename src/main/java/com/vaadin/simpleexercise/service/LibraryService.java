package com.vaadin.simpleexercise.service;

import com.vaadin.simpleexercise.library.book.Book;
import com.vaadin.simpleexercise.library.book.BookDao;
import com.vaadin.simpleexercise.library.book.Library;
import com.vaadin.simpleexercise.library.book.LibraryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private LibraryDao libraryDao;

    public LibraryService() {

    }


    public void saveBook(Book book) {
        libraryDao.findAll().forEach(l -> l.getBooks().add(book));
        book.setLibrary(libraryDao.findOne(1L));
        bookDao.save(book);
    }

    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        libraryDao.findAll().forEach(l -> l.getBooks().forEach(b -> bookList.add(b)));
        return bookList;
    }

    public void deleteBook(long bookId) {
        bookDao.deleteById(bookId);
    }

}
