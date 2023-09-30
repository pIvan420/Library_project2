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
//
//@Component
//public class BookDAO {
//    private final JdbcTemplate jdbcTemplate; //подключаю драйвер
//
//    @Autowired
//    public BookDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Book> index(){
//        return jdbcTemplate.query("SELECT id, name, author, publish_year FROM Book", new BeanPropertyRowMapper<>(Book.class));
//    }
//
//    public Book show(int id){
//        return jdbcTemplate.query("SELECT id, name, author, publish_year FROM Book WHERE id=?", new Object[]{id},
//                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
//    }
//
//    public void save(Book book){
//        jdbcTemplate.update("INSERT INTO Book(name, author, publish_year) VALUES(?, ?, ?)",
//                book.getName(), book.getAuthor(), book.getPublish_year());
//    }
//
//    public void update(int id, Book book){
//        jdbcTemplate.update("UPDATE Book SET name=?, author=?, publish_year=? WHERE id=?",
//                book.getName(), book.getAuthor(), book.getPublish_year(), id);
//    }
//
//    public void delete(int id){
//        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
//    }
//
//    public Person get_reader(int id){
//        return jdbcTemplate.query("SELECT full_name FROM Person join Book on " +
//                "Person.id = Book.person_id WHERE Book.id=?", new Object[]{id},
//                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
//    }
//
//    public void release_book(int id){
//        jdbcTemplate.update("UPDATE Book SET person_id=Null WHERE id=?", id);
//    }
//
//    public void add(int id, int person_id){
//        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", person_id, id);
//    }
//}
