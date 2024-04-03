package com.pranshu.LearningsDemo.cascadeFetch;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    @Fetch(FetchMode.SELECT)
    private List<Book> books;

    public Author() {
    }

    public Author(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }
}

// Cascading the query execution to child objects
// CascadeType.ALL -> PERSSIST(save) + MERGE (update)
// CascadeType.REMOVE -> Books aren't saved in DB, only Author is saved