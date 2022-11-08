package TestDrivenSpringBoot.library.service;

import TestDrivenSpringBoot.entity.Book;
import TestDrivenSpringBoot.repository.BookRepository;
import TestDrivenSpringBoot.repository.MemoryBookRepository;
import TestDrivenSpringBoot.service.BookService;
import TestDrivenSpringBoot.service.dto.Books;
import TestDrivenSpringBoot.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static TestDrivenSpringBoot.service.impl.BookServiceImpl.toDto;
import static org.assertj.core.api.Assertions.*;

public class BookServiceTest {

    private BookRepository bookRepository = new MemoryBookRepository();

    private BookService bookService;


    @BeforeEach
    public void before() {
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    public void ifNoBooksPassedThenEmptyListIsReturned() {
        assertThat(bookService.addBooks(Books.empty()).isEmpty());
    }

    @Test
    public void ifParisOfTitleAndAuthorArePassedThenTheyAreCreatedAndStored() {
        Book first = new Book("The first", "author");
        Book second = new Book("The second", "another author");
        assertThat(bookRepository.save(first)).isEqualTo(first);
        assertThat(bookRepository.save(second)).isEqualTo(second);
        // Mock Repository 로는 when 사용 가능하지만, 아마 memoryBookRepository 구현체라 when 이 실행되지않는 것으로 보임
        //when(bookRepository.save(first)).thenReturn(first);
        //when(bookRepository.save(second)).thenReturn(second);

        Map<String, String> books = new HashMap<>();
        books.put("The first", "author");
        books.put("The second", "another author");
        assertThat(bookService.addBooks(Books.fromMap(books)))
                .contains(toDto(first), toDto(second));
    }

    @Nested
    class FindByAuthorTests {
        @Test
        void ifNoBooksFoundForAuthorThenReturnEmptyList() {
            assertThat(bookRepository.findByAuthor("a")).isEmpty();
        }

        @Test
        void ifAuthorIsEmptyThenThrowException() {
            assertThatThrownBy(() -> bookService.findBooksByAuthor("\t \n"))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void ifAuthorIsNullThenThrowException() {
            assertThatThrownBy(() -> bookService.findBooksByAuthor(null))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
