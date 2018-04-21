package com.vaadin.simpleexercise.ui;


import com.vaadin.simpleexercise.library.book.Book;
import com.vaadin.simpleexercise.service.LibraryService;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.SingleSelect;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@UIScope
@Component
public class GridLayout extends CustomComponent {

    @Autowired
    private LibraryService libraryService;

    private Grid<Book> bookGrid = new Grid<>(Book.class);
    private VerticalLayout verticalLayout = new VerticalLayout();
    private List<Book> books = new ArrayList<>();


    public GridLayout() {

    }

    @PostConstruct
    public void init() {
        createMainLayout();
        createGrid();
    }

    public void createMainLayout() {
        setCompositionRoot(verticalLayout);
    }

    public void createGrid() {
        bookGrid = new Grid<>(Book.class);
        verticalLayout.addComponent(bookGrid);
        books = libraryService.getAllBooks();
        bookGrid.setItems(books);
        bookGrid.removeAllColumns();
        bookGrid.addColumn(Book::getTitle).setCaption("TITLE");
        bookGrid.addColumn(Book::getAuthor).setCaption("AUTHOR");
        bookGrid.addColumn(Book::getYear).setCaption("YEAR");

        verticalLayout.addComponent(bookGrid);

    }

    public void updateGrid() {
        bookGrid.removeAllColumns();
        books = libraryService.getAllBooks();
        bookGrid.setItems(books);
        bookGrid.removeAllColumns();
        bookGrid.addColumn(Book::getTitle).setCaption("TITLE");
        bookGrid.addColumn(Book::getAuthor).setCaption("AUTHOR");
        bookGrid.addColumn(Book::getYear).setCaption("YEAR");
    }

    public SingleSelect<Book> createSelectionMode() {
        SingleSelect<Book> bookSelection = bookGrid.asSingleSelect();
        return bookSelection;
    }

}
