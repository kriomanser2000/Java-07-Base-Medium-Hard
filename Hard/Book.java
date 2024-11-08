import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Flow;
import java.util.stream.Collectors;

public class Book
{
    String title;
    double price;
    Author author;
    Publisher publisher;
    public Book(String title, double price, Author author, Publisher publisher)
    {
        this.title = title;
        this.price = price;
        this.author = author;
        this.publisher = publisher;
    }
    public double getPrice()
    {
        return price;
    }
    public Author getAuthor()
    {
        return author;
    }
    public Publisher getPublisher()
    {
        return publisher;
    }
    class Author
    {
        String name;
        int age;
        public Author(String name, int age)
        {
            this.name = name;
            this.age = age;
        }
        public String getName()
        {
            return name;
        }
    }
    class Publisher
    {
        String name;
        String country;
        public Publisher(String name, String country)
        {
            this.name = name;
            this.country = country;
        }
        public String getCountry()
        {
            return country;
        }
    }
    public void main(String[] args)
    {
        Author author1 = new Author("Author 1", 55);
        Author author2 = new Author("Author 2", 50);
        Author author3 = new Author("Author 3", 45);
        Publisher publisher1 = new Publisher("Pub 1", "Ukraine");
        Publisher publisher2 = new Publisher("Pub 2", "Poland");
        Book book1 = new Book("Book 1", 60, author1, publisher1);
        Book book2 = new Book("Book 2", 40, author2, publisher1);
        Book book3 = new Book("Book 3", 55, author3, publisher2);
        Book book4 = new Book("Book 4", 70, author1, publisher2);
        List<Book> books = Arrays.asList(book1, book2, book3, book4);
        Map<String, List<Author>> authorsByPublisherCountry = books.stream()
                .filter(book -> book.getPrice() > 50)
                .collect(Collectors.groupingBy(
                        book -> book.getPublisher().getCountry(),
                        Collectors.mapping(
                                book -> book.getAuthor(),
                                Collectors.toList()
                        )
                ));
        authorsByPublisherCountry.forEach((country, authors) ->
        {
            System.out.println("Country: " + country);
            authors.forEach(author -> System.out.println("Author: " + author.getName()));
        });
    }
}
