package ru.pivan.Project2Boot.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.pivan.Project2Boot.models.Person;
import ru.pivan.Project2Boot.services.PeopleServices;

@Component
public class PersonValidator implements Validator {

    private final PeopleServices peopleServices;

    @Autowired
    public PersonValidator(PeopleServices peopleServices) {
        this.peopleServices = peopleServices;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(peopleServices.findByFullName(person.getFullName()) != null){
            errors.rejectValue("full_name", "", "Это имя уже занято");
        }
    }
}

