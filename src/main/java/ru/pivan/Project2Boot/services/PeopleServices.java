package ru.pivan.Project2Boot.services;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pivan.Project2Boot.models.Book;
import ru.pivan.Project2Boot.models.Person;
import ru.pivan.Project2Boot.repositories.PeopleRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Transactional(readOnly = true)
public class PeopleServices {

    private final PeopleRepository peopleRepository;

    public PeopleServices(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findOne(int id){
        Optional<Person> person = peopleRepository.findById(id);
        person.ifPresent(p -> {
            Hibernate.initialize(p.getBooks());
            for (Book b: p.getBooks()) {
                int daysPassed = (int) TimeUnit.MILLISECONDS.toDays(
                    (new Date()).getTime() - b.getDateReceived().getTime());
                b.setOverdue(daysPassed > 10);
            }
        });
        return person.orElse(null);
    }

    public Person findByFullName(String fullName){
        return peopleRepository.findByFullName(fullName).orElse(null);
    }

    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson){
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }
}
