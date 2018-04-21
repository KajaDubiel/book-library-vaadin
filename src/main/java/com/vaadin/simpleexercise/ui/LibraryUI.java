package com.vaadin.simpleexercise.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;


@SpringUI
public class LibraryUI extends UI {

    @Autowired
    private BookForm bookForm;

    @Autowired
    private GridLayout gridLayout;

    private HorizontalLayout mainLayout;
    private VerticalLayout verticalLayout = new VerticalLayout();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setMainLayout();
        setHeader();
        loadBooks();
        addBookForm();
    }

    public void setMainLayout() {
        mainLayout = new HorizontalLayout();
        setContent(mainLayout);

    }

    public void setHeader() {
        Label header = new Label("Library");
        header.setStyleName(ValoTheme.LABEL_H1);
        verticalLayout.addComponent(header);
        mainLayout.addComponent(verticalLayout);
        verticalLayout.setComponentAlignment(header, Alignment.TOP_CENTER);
        header.addStyleName(ValoTheme.LABEL_H1);
    }


    public void loadBooks() {
        verticalLayout.addComponent(gridLayout);
        verticalLayout.setMargin(new MarginInfo(false, true, false, true));
    }

    public void addBookForm() {
        mainLayout.addComponent(bookForm);
        mainLayout.setComponentAlignment(bookForm, Alignment.MIDDLE_CENTER);
    }

}
