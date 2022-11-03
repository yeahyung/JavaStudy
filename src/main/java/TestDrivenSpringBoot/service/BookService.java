package TestDrivenSpringBoot.service;

import TestDrivenSpringBoot.service.dto.BookDto;
import TestDrivenSpringBoot.service.dto.Books;

import java.util.List;

/**
 * @author Alimenkou Mikalai
 */
public interface BookService {
    List<BookDto> addBooks(Books books);

    List<BookDto> findBooksByAuthor(String author);

    List<BookDto> findAllBooks();
}
