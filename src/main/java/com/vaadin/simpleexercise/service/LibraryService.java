package com.vaadin.simpleexercise.service;

import com.vaadin.simpleexercise.library.book.Book;
import com.vaadin.simpleexercise.library.book.BookDao;
import com.vaadin.simpleexercise.library.book.Library;
import com.vaadin.simpleexercise.library.book.LibraryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private LibraryDao libraryDao;
    private static LibraryService libraryServiceInstance;

    private LibraryService(){

    }

    public static LibraryService getInstance(){
        if(libraryServiceInstance == null){
            synchronized (LibraryService.class){
                if(libraryServiceInstance == null){
                    libraryServiceInstance = new LibraryService();
                }
            }
        }
        return libraryServiceInstance;
    }

    public void saveBook(Book book){
       // bookDao.save(book);
        libraryDao.findAll().forEach(l -> l.getBooks().add(book));
    }

    public List<Book> getAllBooks(){
        /*List<Book> booksList = new ArrayList<>();
        bookDao.findAll().forEach(b -> booksList.add(b));
        return booksList;*/
        List<Book> bookList = new ArrayList<>();
        libraryDao.findAll().forEach(l -> l.getBooks().forEach(b-> bookList.add(b)));
        return bookList;
    }

}
