package com.ericsson.booklight.ejbservice.facade;

import java.util.List;

import javax.ejb.Local;

import com.ericsson.booklight.ejbservice.domain.BookCriteria;
import com.ericsson.booklight.ejbservice.domain.BookStub;
import com.ericsson.booklight.ejbservice.exception.FacadeException;

@Local
public interface BookFacade {

	BookStub getBook(String isbn) throws FacadeException;

	List<BookStub> getBooks(BookCriteria criteria) throws FacadeException;

}
