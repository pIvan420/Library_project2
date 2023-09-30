package ru.pivan.Project2Boot.dao;//package ru.pivan.dao;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//import ru.pivan.models.Book;
//import ru.pivan.models.Person;
//
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class PersonDAO {
//
//    private final JdbcTemplate jdbcTemplate; //подключаю драйвер
//
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> index(){
//        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Person show(int id){
//        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},
//                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
//    }
//
//    public Person show(String full_name){
//        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name=?", new Object[]{full_name},
//                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
//    }
//
//    public void save(Person person){
//        jdbcTemplate.update("INSERT INTO Person(full_name, birth_year) VALUES(?, ?)",
//                person.getFull_name(), person.getBirth_year());
//    }
//
//    public void update(int id, Person person){
//        jdbcTemplate.update("UPDATE Person SET full_name=?, birth_year=? WHERE id=?",
//                person.getFull_name(), person.getBirth_year(), id);
//    }
//
//    public void delete(int id){
//        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
//    }
//
//    public List<Book> get_books(int id){
//        return jdbcTemplate.query("SELECT name, author, publish_year " +
//                "FROM Book WHERE person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
//    }
//}
