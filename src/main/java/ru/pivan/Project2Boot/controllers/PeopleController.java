package ru.pivan.Project2Boot.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.pivan.Project2Boot.models.Person;
import ru.pivan.Project2Boot.services.PeopleServices;
import ru.pivan.Project2Boot.util.PersonValidator;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleServices peopleServices;

    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PeopleServices peopleServices, PersonValidator personValidator) {
        this.peopleServices = peopleServices;
        this.personValidator = personValidator;
    }

    @GetMapping() // отправляется запрос на страницу с пользователями
    public String index(Model model){
        model.addAttribute("people", peopleServices.findAll());
        System.out.println("2");
        return "people/index";
    }

    @GetMapping("/{id}") // отправляется запрос на страницу с пользователем
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleServices.findOne(id));
        return "people/show";
    }

    @GetMapping("/new") // отправляется get запрос на страницу people/new с формой заполнения
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping() // из формы выполняется post запрос на страницу people и вызывается этот метод
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()){
            return "people/new";
        }
        peopleServices.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit") // выполняется запрос на страницу с формой по изменению данных
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleServices.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}") // из формы выполняется запрос на изменение данных
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult, @PathVariable("id") int id){
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()){
            return "people/edit";
        }
        peopleServices.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}") // выполняется delete запрос на страницу с id, происходит удаление
    public String delete(@PathVariable("id") int id){
        peopleServices.delete(id);
        return "redirect:/people";
    }
}
