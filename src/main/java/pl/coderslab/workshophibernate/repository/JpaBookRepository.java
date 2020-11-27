package pl.coderslab.workshophibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.workshophibernate.entity.Book;

@Repository
public interface JpaBookRepository extends JpaRepository<Book, Long> {

}
