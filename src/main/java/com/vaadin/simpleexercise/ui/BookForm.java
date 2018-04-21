package com.vaadin.simpleexercise.ui;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.event.ShortcutAction;
import com.vaadin.simpleexercise.library.book.Book;
import com.vaadin.simpleexercise.service.LibraryService;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@UIScope
@Component
public class BookForm extends CustomComponent {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private GridLayout gridLayout;

    private Layout layout;
    private HorizontalLayout buttons = new HorizontalLayout();
    private TextField title;
    private TextField author;
    private TextField year;
    private Label header;
    private Button addBook;
    private Button deleteBook;
    private Binder<Book> binder = new Binder<>(Book.class);


    public BookForm() {

    }


    @PostConstruct
    public void init() {
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
        addBook.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        buttons.addComponent(addBook);
        layout.addComponent(buttons);

        bindFields();
        addBook.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        addBook.addClickListener(e -> {
            libraryService.saveBook(binder.getBean());
            gridLayout.updateGrid();
        });


    }

    public void bindFields() {
        binder.forField(year).withConverter(new StringToIntegerConverter("Must enter a year")).bind(Book::getYear, Book::setYear);
        binder.bindInstanceFields(this);
        binder.setBean(new Book());
    }

    public void createButtonDeleteBook() {
        deleteBook = new Button("Delete");
        buttons.addComponent(deleteBook);
        deleteBook.setClickShortcut(ShortcutAction.KeyCode.DELETE);
        deleteBook.addClickListener(e -> {
            libraryService.deleteBook(gridLayout.createSelectionMode().getValue().getId());
            Notification.show("id = " + gridLayout.createSelectionMode().getValue().getId());
        });
    }
}
