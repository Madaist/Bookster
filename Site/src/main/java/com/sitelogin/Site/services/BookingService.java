package com.sitelogin.Site.services;

import com.sitelogin.Site.domain.Book;
import com.sitelogin.Site.domain.Booking;
import com.sitelogin.Site.domain.CompositePK;
import com.sitelogin.Site.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookingRepository bookingRepository;

    public void deleteBooking(int userId, int bookId){
        bookingRepository.deleteFromBooking(userId, bookId);
    }


    public boolean borrowBook(String title, HttpSession session) throws Exception {
        Book book = bookService.findBookByTitle(title);
        if(book.getCantity() > 0){
            Booking booking= new Booking(new CompositePK((Integer)session.getAttribute("userId"), book.getBookId()));
            addBooking(booking);
            int cantity = book.getCantity();
            book.setCantity(--cantity);
            bookService.addBook(book);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean returnBook(String title, HttpSession session){
        Book book = bookService.findBookByTitle(title);
        int userId = (Integer)session.getAttribute("userId");
        Booking booking = findBooking(book.getBookId(), userId);

        if(booking == null)
            return false;
        else {
            deleteBooking(userId, book.getBookId());
            int cantity = book.getCantity();
            book.setCantity(++cantity);
            bookService.addBook(book);
        }
        return true;
    }

    public void addBooking(Booking booking) throws Exception {
        try {
            bookingRepository.save(booking);
        } catch (Exception exception) {
            throw new Exception("could not save the booking ");
        }
    }

    public Booking findBooking(int bookId, int userId){
        Booking booking = null;
        Optional<Booking> optionalBooking = bookingRepository.findBookingByIds(bookId, userId);
        if(optionalBooking.isPresent())
            booking = optionalBooking.get();
        return booking;
    }

    public boolean checkBookingForUser(int userId){
      /*Optional<Booking> optionalBooking = bookingRepository.findBookingByUserId(userId);
      if(optionalBooking.isPresent())
          return true;
      else return false;*/
      int a = 1;
      List<Integer> user = bookingRepository.findUserInBooking(userId);
      return user == null;

    }


}
