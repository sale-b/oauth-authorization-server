package com.fon.web;

import com.fon.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {

    @GetMapping("/books")
    public Book[] getBooks() {
        return new Book[]{
                new Book("Title Book 1",
                        "Author Name 1",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis sollicitudin diam at pellentesque dignissim. Fusce ac dignissim urna. Aliquam mattis, velit ut porttitor lobortis, eros dui vestibulum nibh, ut posuere augue elit eget eros. Cras viverra fringilla justo nec gravida. Nunc non faucibus neque.",
                        "https://resource-server:8090/books/img/book-cover.jpg"),
                new Book(
                        "Title Book 2",
                        "Author Name 2",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis sollicitudin diam at pellentesque dignissim. Fusce ac dignissim urna. Aliquam mattis, velit ut porttitor lobortis, eros dui vestibulum nibh, ut posuere augue elit eget eros. Cras viverra fringilla justo nec gravida. Nunc non faucibus neque.",
                        "https://resource-server:8090/books/img/book-cover2.jpg"),
                new Book(
                        "Title Book 3",
                        "Author Name 3",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis sollicitudin diam at pellentesque dignissim. Fusce ac dignissim urna. Aliquam mattis, velit ut porttitor lobortis, eros dui vestibulum nibh, ut posuere augue elit eget eros. Cras viverra fringilla justo nec gravida. Nunc non faucibus neque.",
                        "https://resource-server:8090/books/img/book-cover.jpg")};
    }
}