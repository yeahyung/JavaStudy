package TestDrivenSpringBoot.repository;

import TestDrivenSpringBoot.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Alimenkou Mikalai
 */
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(String author);

    Optional<Book> findByName(String name);
}
