package com.vaadin.simpleexercise.library.book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface LibraryDao extends CrudRepository<Library, Long> {
    //void save(List<Book> books);
}
