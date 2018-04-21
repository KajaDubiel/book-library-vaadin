package com.vaadin.simpleexercise.library;

import com.vaadin.simpleexercise.library.book.Book;
import com.vaadin.simpleexercise.library.book.BookDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

public class BookDaoTestSuite {
    @Autowired
    BookDao bookDao;

    @Test
    public void testAddBooks() {
        //Given
        Book book1 = new Book("W pustyni i w puszczy", "Henryk Sienkiewicz", 1988);
        Book book2 = new Book("Pan Tadeusz", "Adam Mickiewicz", 1973);
        Book book3 = new Book("Lalka", "Bolesław Prus", 1976);
        Book book4 = new Book("Czysty kod", "Uncle Bob", 2009);
        //When
        bookDao.save(book1);
        bookDao.save(book2);
        bookDao.save(book3);
        bookDao.save(book4);
        //Then CleanUp
        bookDao.delete(book1);
        bookDao.delete(book2);
        bookDao.delete(book3);
        bookDao.delete(book4);
    }

    @Test
    public void testDeleteBookByID() {
        //Given
        Book book1 = new Book("Jakas ksiazka", "Jakis autor", 1978);
        bookDao.save(book1);
        long id = book1.getId();
        //When
        bookDao.deleteById(id);
        //Then
    }
}
