package com.ericsson.bookstore.persistence.service;

import java.util.List;

import javax.ejb.Local;

import com.ericsson.bookstore.persistence.entity.Book;
import com.ericsson.bookstore.persistence.entity.trunk.BookCategory;
import com.ericsson.bookstore.persistence.exception.PersistenceServiceException;

@Local
public interface BookSearch {

	List<Book> find(String isbn, String author, String title, Integer numberOfPagesMin, Integer numberOfPagesMax, Double maxPrice, BookCategory category)
			throws PersistenceServiceException;

}
