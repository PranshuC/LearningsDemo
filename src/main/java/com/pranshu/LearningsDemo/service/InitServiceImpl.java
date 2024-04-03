package com.pranshu.LearningsDemo.service;

import com.pranshu.LearningsDemo.demo.Author;
import com.pranshu.LearningsDemo.demo.AuthorRepo;
import com.pranshu.LearningsDemo.demo.Book;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitServiceImpl implements InitService {
    private AuthorRepo authorRepo;

    public InitServiceImpl(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    /**
     * @Transactional
     * Ensures data consistency by treating a method as a single unit of work
     * and handles transaction lifecycles automatically
     * Ex : fetches all the books along with the author (even with FetchType.LAZY)
     * LazyInitializationException will result in rollback, so tries not to fail
     */
    @Override
    public void initialise() {
        Author author = new Author("Ashok Kumar", null);

        Book book1 = new Book("Book1", author);
        Book book2 = new Book("Book2", author);
        Book book3 = new Book("Book3", author);
        author.setBooks(List.of(book1, book2, book3));

        authorRepo.save(author);
        // cascade ALL -> If we save author, all dependent books will be saved

        Author savedAuthor = authorRepo.findById(1).get();
        // FetchType.EAGER -> Fetches all the books (LEFT JOIN) along with the author
        // FetchType.LAZY -> Fetches only the author (no JOINs), not bring dependent books

        List<Book> books = savedAuthor.getBooks();
        System.out.println(books);
        /** FetchType.LAZY -> LazyInitializationException : failed to lazily initialize a collection of role
         * Books are not fetched. But, if needed, further query :
         * List<Book> books = bookRepository.findByAuthor_Id(savedAuthor.getId());
         * savedAuthor.setBooks(books);
        */

    }
}
/**
* FetchMode.SELECT + FetchType.EAGER-> 2 separate queries are fired (1 for author, 1 for books)
* FetchMode.SELECT + FetchType.LAZY-> Anyways only 1 query, for author
* (FetchMode.SUBSELECT is kinda same as above)
* FetchMode.JOIN -> Default for FetchType.EAGER
*/