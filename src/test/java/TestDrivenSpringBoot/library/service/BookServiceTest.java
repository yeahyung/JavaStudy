package TestDrivenSpringBoot.library.service;

import TestDrivenSpringBoot.repository.BookRepository;
import TestDrivenSpringBoot.repository.MemoryBookRepository;
import TestDrivenSpringBoot.service.BookService;
import TestDrivenSpringBoot.service.dto.Books;
import TestDrivenSpringBoot.service.impl.BookServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class BookServiceTest {

    private BookRepository bookRepository = new MemoryBookRepository();

    private BookService bookService;


    @Before
    public void before() {
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    public void ifNoBooksPassedThenEmptyListIsReturned() {
        Assertions.assertThat(bookService.addBooks(Books.empty()).isEmpty());
    }

}
