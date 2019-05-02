package com.sitelogin.Site.repositories;

import com.sitelogin.Site.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    @Transactional
    @Query(value = "SELECT * from [bookster].[dbo].book_tbl b WHERE b.title = :title", nativeQuery = true)
    Optional<Book> findBookByTitle(@Param("title") String title);


}
