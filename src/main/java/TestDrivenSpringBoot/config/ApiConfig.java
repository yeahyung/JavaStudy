package TestDrivenSpringBoot.config;


import TestDrivenSpringBoot.repository.BookRepository;
import TestDrivenSpringBoot.repository.MemoryBookRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {
    @Bean
    public BookRepository bookRepository() {
        return new MemoryBookRepository();
    }
}
