package com.sitelogin.Site.services;

import com.sitelogin.Site.domain.Book;
import com.sitelogin.Site.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public void addBooksToDatabase(){
        Scanner sc = null;
        try {
            sc = new Scanner(new File("books.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String header = sc.nextLine();
        String row = null;
       while(sc.hasNext()){
           row = sc.nextLine();
           String[] columns =  row.split("\\s*,\\s*");
           Book book = new Book(columns[0], columns[1], columns[2], Integer.parseInt(columns[3]), Integer.parseInt(columns[4]));
           bookRepository.save(book);

       }
    }

    public Book findBookByTitle(String title) {
        Book book = new Book();
        Optional<Book> optionalBook = bookRepository.findBookByTitle(title);
        if (optionalBook.isPresent())
            book = optionalBook.get();

        return book;
    }


    public void addBook(Book book){
        bookRepository.save(book);
    }

}
