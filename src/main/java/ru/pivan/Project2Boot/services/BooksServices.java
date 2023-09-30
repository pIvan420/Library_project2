package ru.pivan.Project2Boot.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pivan.Project2Boot.models.Book;
import ru.pivan.Project2Boot.repositories.BooksRepository;
import ru.pivan.Project2Boot.repositories.PeopleRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksServices {

    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;

    public BooksServices(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll(){
        return booksRepository.findAll();
    }

    public List<Book> findAll(int page, int booksPerPage){
        return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public List<Book> findAll(boolean sort){
        return booksRepository.findAll(Sort.by("publishYear"));
    }

    public List<Book> findAll(int page, int booksPerPage, boolean sort){
        return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("publishYear"))).getContent();
    }

    public List<Book> findByNameStartingWith(String prefix){
        return booksRepository.findByNameStartingWith(prefix);
    }

    public Book findOne(int id){
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook){
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }

    @Transactional
    public void releaseBook(int id){
        booksRepository.findById(id).ifPresent(book -> {
            book.setPerson(null);
            book.setDateReceived(null);
        });
    }

    @Transactional
    public void add(int id, int person_id){
        booksRepository.findById(id).ifPresent(book -> {
            book.setPerson(peopleRepository.findById(person_id).orElse(null));
            book.setDateReceived(new Date());
        });
    }

}
