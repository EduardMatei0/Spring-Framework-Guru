package com.eduardmatei.spring5webapp.bootstrap;

import com.eduardmatei.spring5webapp.model.Author;
import com.eduardmatei.spring5webapp.model.Book;
import com.eduardmatei.spring5webapp.model.Publisher;
import com.eduardmatei.spring5webapp.repositories.AuthorRepository;
import com.eduardmatei.spring5webapp.repositories.BookRepository;
import com.eduardmatei.spring5webapp.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {


    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    @Autowired
    public DevBootstrap(AuthorRepository authorRepository,
                        BookRepository bookRepository,
                        PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {


        // Eric
        Author eric = new Author("Eric", "Evans");
        Publisher harper = new Publisher("Harper Collins", "Downtown");
        Book ddd = new Book("Domain Driven Design", "1234", harper);

        settingRelanshiopBetweenEntities(harper, eric, ddd);

        // Rod

        Publisher worx = new Publisher("Worx Adams", "Centre");
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23545", worx);

        settingRelanshiopBetweenEntities(worx, rod, noEJB);
    }

    private void settingRelanshiopBetweenEntities(Publisher publisher, Author author, Book book) {

        book.setPublisher(publisher);
        author.addBook(book);
        book.addAuthor(author);

        saveEntities(author, book, publisher);
    }

    private void saveEntities(Author author, Book book, Publisher publisher) {

        publisherRepository.save(publisher);
        authorRepository.save(author);
        bookRepository.save(book);

    }

}
