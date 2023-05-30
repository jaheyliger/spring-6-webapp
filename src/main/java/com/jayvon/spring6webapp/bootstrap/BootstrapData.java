package com.jayvon.spring6webapp.bootstrap;

import com.jayvon.spring6webapp.domain.Author;
import com.jayvon.spring6webapp.domain.Book;
import com.jayvon.spring6webapp.domain.Publisher;
import com.jayvon.spring6webapp.repositories.AuthorRepository;
import com.jayvon.spring6webapp.repositories.BookRepository;
import com.jayvon.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Dirven Design");
        ddd.setIsbn("123456");

        Publisher dsw = new Publisher();
        dsw.setPublisherName("DSW");
        dsw.setAddress("123 Main St");
        dsw.setCity("Union");
        dsw.setState("NJ");
        dsw.setZip("07205");

        Publisher dswSaved = publisherRepository.save(dsw);
        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        ericSaved.getBooks().add(dddSaved);
        dddSaved.setPublisher(dswSaved);

        authorRepository.save(ericSaved);
        bookRepository.save(dddSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());
    }
}
