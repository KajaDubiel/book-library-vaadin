package com.vaadin.simpleexercise.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.simpleexercise.library.book.Book;
import com.vaadin.simpleexercise.library.book.BookDao;
import com.vaadin.simpleexercise.library.book.Library;
import com.vaadin.simpleexercise.library.book.LibraryDao;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.List;

@SpringUI
public class LibraryUI extends UI {
    private VerticalLayout mainLayout;
    private Grid<Book> grid;
    private BookDao bookDao;
    private LibraryDao libraryDao;
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setMainLayout();
        setHeader();
        loadBooks();
    }

    public void setMainLayout(){
        mainLayout = new VerticalLayout();
        setContent(mainLayout);
    }

    public void setHeader(){
        Label header = new Label("Library");
        header.setStyleName(ValoTheme.LABEL_H1);
        mainLayout.addComponent(header);
        mainLayout.setComponentAlignment(header, Alignment.TOP_CENTER);
        header.addStyleName(ValoTheme.LABEL_H1);
    }

    private void loadBooks() {
        grid = new Grid<>(Book.class);
        mainLayout.addComponent(grid);
        mainLayout.setComponentAlignment(grid, Alignment.TOP_CENTER);
        List<Book> books;
        libraryDao.findAll().forEach(l -> l.getBooks());//????????????!?!?!?!?!?
        //List<Book> books = new ArrayList<>();
       // bookDao.findAll().forEach(b -> books.add(b));
        //grid.setItems(books);
        mainLayout.addComponent(grid);
    }


}
