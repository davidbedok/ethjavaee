package com.ericsson.bookstore.ejbservice.converter;

import java.util.List;

import javax.ejb.Local;

import com.ericsson.bookstore.ejbservice.domain.BookStub;
import com.ericsson.bookstore.persistence.entity.Book;

@Local
public interface BookConverter {

	BookStub to(Book book);

	List<BookStub> to(List<Book> books);

}
