package com.sitelogin.Site.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompositePK implements Serializable {

    @Column(name="user_id")
    private int userId;

    @Column(name="book_id")
    private int bookId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public CompositePK(int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public CompositePK(){};

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompositePK)) return false;
        CompositePK that = (CompositePK) o;
        return userId == that.userId &&
                bookId == that.bookId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, bookId);
    }
}
