package afn.xloop.booktracker_cnsd23;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Book {
    private Integer id;
    private String title;
    private String author;
    private int year;
    private int pages;

    public Book() {}

    public Book(int id, String title, String author, int year, int pages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
    }
}
