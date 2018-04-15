package com.vaadin.simpleexercise.ui;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.simpleexercise.library.book.Book;
import com.vaadin.simpleexercise.service.LibraryService;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@UIScope
@Component
public class BookForm extends CustomComponent {

    @Autowired
    LibraryService libraryService;

    Layout layout;
    HorizontalLayout buttons = new HorizontalLayout();
    TextField title;
    TextField author;
    TextField year;
    Label header;
    Button addBook;
    Button deleteBook;
    Binder<Book> binder = new Binder<>(Book.class);

    public BookForm() {
        createMainLayout();
        createHeader();
        createTextFields();
        createButtonAddBook();
        createButtonDeleteBook();

    }

    public void createMainLayout() {
        layout = new VerticalLayout();
        setCompositionRoot(layout);
    }

    public void createTextFields() {
        title = new TextField("TITLE");
        author = new TextField("AUTHOR");
        year = new TextField("YEAR");
        layout.addComponent(title);
        layout.addComponent(author);
        layout.addComponent(year);
    }

    public void createHeader() {
        header = new Label("Enter new book");
        layout.addComponent(header);
        header.addStyleName(ValoTheme.LABEL_H2);
    }

    public void createButtonAddBook() {
        addBook = new Button("Add");
        buttons.addComponent(addBook);
        layout.addComponent(buttons);

        binder.forField(year).withConverter(new StringToIntegerConverter("Must enter a year")).bind(Book::getYear, Book::setYear);
        binder.bindInstanceFields(this);

        /*addBook.addClickListener(e -> binder.bindInstanceFields(this));
        binder.forField(year).withConverter(new StringToIntegerConverter("Must enter a number")).bind(Book::getYear, Book::setYear);*/

        //Book book = new Book("Sample book", "Sample author", 1876);
        //addBook.addClickListener(e-> libraryService.saveBook(book));
        Notification notification = new Notification("Bean: " + binder.getBean());
        addBook.addClickListener(e -> notification.show("tah" + binder.getBean()));
    }

    public void createButtonDeleteBook() {
        deleteBook = new Button("Delete");
        buttons.addComponent(deleteBook);
        layout.addComponent(buttons);
    }

}
