# Learnings from Demo

### DB Inheritance strategies
- @MappedSuperclass <br>
No table for parent class, but individual for each child class.
- @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) <br>
Similar to MappedSuperclass with difference that the superclass is now also an entity.
- @Inheritance(strategy = InheritanceType.JOINED) <br>
Maps each class of the inheritance hierarchy to its own database table. It contains the columns shared by all classes.
- @Inheritance(strategy = InheritanceType.SINGLE_TABLE) <br>
Maps all entities of the inheritance hierarchy to the same database table.

### CASCADE
- ALL -> All operations are cascaded 
- PERSIST -> save operations are cascaded 
- MERGE -> updates are cascaded 
- REMOVE -> deletes are cascaded 
- REFRESH -> refreshing data from DB operations are cascaded 
- DETACH -> detachment of object with EntityManager are cascaded

Author -> 3 books <br>
Author (save) : Author + 3 books <br>
Separate DB operation -> Added a new book <br>
REFRESH the Author -> Query for Author again :
Brings the latest author object with 4 books

Author -> Detach : Treat like POJO & not Entity,
JPA CRUD operations are not applicable anymore

### FetchType [ JPA level ]
- EAGER -> loads an object with all the dependent objects
- LAZY -> loads an object only, not the dependent objects

### FetchMode [ Hibernate level ]
- SELECT -> gets overridden with FetchType [Eager or Lazy] <br>
    - Eager loading : Fetches the data using SELECT queries
    - Lazy loading : Nothing gets fetched

- JOIN -> never overridden always Eager

- SUBSELECT -> gets overridden with FetchType [Eager or Lazy] {only used with collection} 
  - Eager loading : Fetches the data using SELECT queries
  - Lazy loading : Nothing gets fetched

EAGER Loading -> query the dependent data using SELECT or JOINS <br>
@Fetch(FetchMode.SELECT) -> select queries <br>
@Fetch(FetchMode.JOIN) -> join queries

Which 1 is better? It depends. <br>
Ex : Author <br>
1. List\<Book> - 10
2. List\<Publisher> - 5 
3. List\<Event> - 12 
4. List\<Employee> - 3
- Eager loading : SELECT -> author <br>
N select queries for N items { N + 1 }
- Combine into a single table - cached : Query only once.<br>
**Subqueries aren't cached, but JOINs are. so, default is JOIN (So, better & default)**

In general, best idea is to use LAZY loading + call queries out of N, when required.
Just be careful of LazyInitializationException (fetch only attributes available)

### N + 1 problem
I am just querying for author, 
but N more queries are fired automatically. (Not good).
Solved with EAGER loading + JOIN.
