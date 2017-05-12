package com.ericsson.bookstore.ejbservice.facade;

import java.util.List;

import javax.ejb.Local;

import com.ericsson.bookstore.ejbservice.domain.BookCriteria;
import com.ericsson.bookstore.ejbservice.domain.BookStub;
import com.ericsson.bookstore.ejbservice.exception.FacadeException;

@Local
public interface BookFacade {

	BookStub getBook(String isbn) throws FacadeException;

	List<BookStub> getBooks(BookCriteria criteria) throws FacadeException;

}
