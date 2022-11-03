package TestDrivenSpringBoot.service.impl;

import TestDrivenSpringBoot.entity.Book;
import TestDrivenSpringBoot.repository.BookRepository;
import TestDrivenSpringBoot.service.BookService;
import TestDrivenSpringBoot.service.dto.BookDto;
import TestDrivenSpringBoot.service.dto.Books;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author Alimenkou Mikalai
 */
@Slf4j
@Service
public class BookServiceImpl implements BookService {
    private final TestDrivenSpringBoot.repository.BookRepository BookRepository;
    private final ConcurrentMap<String, List<Book>> cache = new ConcurrentHashMap<>();

    public BookServiceImpl(BookRepository BookRepository) {
        this.BookRepository = BookRepository;
    }

    @Override
    public List<BookDto> addBooks(Books books) {
        log.info("Adding books: {}" + books);
        return toDto(books.stream()
                .map(entry -> new Book(entry.getKey(), entry.getValue()))
                .map(BookRepository::save));
    }

    @Override
    public List<BookDto> findBooksByAuthor(String author) {
        log.info("Try to find books by author: {}", author);
        Assert.hasText(author, "Author is empty!");
        String normalizedAuthor = normalizeAuthorName(author);
        Stream<Book> books = cache.computeIfAbsent(normalizedAuthor, BookRepository::findByAuthor).stream();
        return toDto(books);
    }

    @Override
    public List<BookDto> findAllBooks() {
        log.info("Finding all books");
        return toDto(BookRepository.findAll().stream());
    }

    private String normalizeAuthorName(String authorName) {
        return isSingleWord(authorName) ? splitOnFirstAndLastNames(authorName) : authorName;
    }

    private boolean isSingleWord(String correctAuthor) {
        return !StringUtils.containsWhitespace(correctAuthor);
    }

    private String splitOnFirstAndLastNames(String author) {
        String[] parts = author.split(" ");
        String firstName = parts[0];
        if (parts.length == 1) {
            return firstName;
        }
        return String.join(" ", firstName, parts[parts.length-1]);
    }

    private List<BookDto> toDto(Stream<Book> books) {
        return books.map(BookServiceImpl::toDto)
                .collect(toList());
    }

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getName(), book.getAuthor());
    }
}
