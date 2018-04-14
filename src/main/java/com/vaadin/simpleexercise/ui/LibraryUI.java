package com.vaadin.simpleexercise.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.simpleexercise.library.book.Book;
import com.vaadin.simpleexercise.library.book.Library;
import com.vaadin.simpleexercise.library.book.LibraryDao;
import com.vaadin.simpleexercise.service.LibraryService;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringUI
public class LibraryUI extends UI {
    private HorizontalLayout mainLayout;
    private Grid<Book> grid = new Grid(Book.class);
    @Autowired
    private LibraryService libraryService;
    private VerticalLayout verticalLayout = new VerticalLayout();
    //private MyView myView;
    //@Autowired
   // private LibraryDao libraryDao;
   // private Library library = new Library();
    BookForm bookForm = new BookForm();
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setMainLayout();
        setHeader();
        loadBooks();
        addBookForm();

       // gridLayout.loadGrid();
        //myView = new MyView();
        //mainLayout.addComponent(myView);
    }

    public void setMainLayout(){
        mainLayout = new HorizontalLayout();
        setContent(mainLayout);

    }

    public void setHeader(){
        Label header = new Label("Library");
        header.setStyleName(ValoTheme.LABEL_H1);
        verticalLayout.addComponent(header);
        mainLayout.addComponent(verticalLayout);
        verticalLayout.setComponentAlignment(header, Alignment.TOP_CENTER);
        header.addStyleName(ValoTheme.LABEL_H1);
    }

    private void loadBooks() {
        grid = new Grid<>(Book.class);
        verticalLayout.addComponent(grid);
        List<Book> books = libraryService.getAllBooks();
        grid.setItems(books);
        grid.removeAllColumns();
        grid.addColumn(Book::getTitle).setCaption("TITLE");
        grid.addColumn(Book::getAuthor).setCaption("AUTHOR");
        grid.addColumn(Book::getYear).setCaption("YEAR");

        mainLayout.addComponent(verticalLayout);
        //mainLayout.setComponentAlignment(grid, Alignment.TOP_CENTER);
    }

    public void addBookForm(){
        mainLayout.addComponent(bookForm);
        mainLayout.setComponentAlignment(bookForm, Alignment.MIDDLE_CENTER);
    }

    public void updateGrid(){

    }


}
