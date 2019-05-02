package com.sitelogin.Site.repositories;

import com.sitelogin.Site.domain.Booking;
import com.sitelogin.Site.domain.CompositePK;
import com.sitelogin.Site.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, CompositePK> {

    @Transactional
    @Modifying
    @Query(value="DELETE FROM [bookster].[dbo].booking_tbl WHERE [user_id]=:user_id_param AND [book_id]=:book_id_param", nativeQuery = true)
    void deleteFromBooking(@Param("user_id_param") int user_id,
                          @Param("book_id_param") int book_id);

    @Query(value="INSERT INTO booking_tbl (user_id, book_id) VALUES :user_id_param, :book_id_param", nativeQuery = true)
    void insertBooking(@Param("user_id_param") int user_id,
                       @Param("book_id_param") int book_id);

    @Transactional
    @Query(value = "SELECT * from booking_tbl b WHERE b.book_id = :book_id_param AND " +
                                                     "b.user_id = :user_id_param", nativeQuery = true)
    Optional<Booking> findBookingByIds(@Param("book_id_param") int book_id, @Param("user_id_param") int user_id);

    @Transactional
    @Query(value = "SELECT * from booking_tbl b WHERE b.user_id = :user_id_param", nativeQuery = true)
    Optional<Booking> findBookingByUserId(@Param("user_id_param") int user_id);

    @Transactional
    @Query(value = "SELECT user_id from booking_tbl b WHERE b.user_id = :user_id_param", nativeQuery = true)
    List<Integer> findUserInBooking(@Param("user_id_param") int user_id);
}
