package com.ericsson.bookstore.persistence.service;

import java.util.List;

import javax.ejb.Local;

import com.ericsson.bookstore.persistence.entity.Book;
import com.ericsson.bookstore.persistence.entity.trunk.BookCategory;
import com.ericsson.bookstore.persistence.exception.PersistenceServiceException;

@Local
public interface BookService {

	boolean exists(String isbn) throws PersistenceServiceException;

	Book create(String isbn, String author, String title, int numberOfPages, double price, BookCategory category) throws PersistenceServiceException;

	Book read(Long id) throws PersistenceServiceException;

	Book read(String isbn) throws PersistenceServiceException;

	List<Book> readAll() throws PersistenceServiceException;

	List<Book> read(BookCategory category) throws PersistenceServiceException;

	Book update(String isbn, String author, String title, int numberOfPages, double price, BookCategory category) throws PersistenceServiceException;

	void delete(String isbn) throws PersistenceServiceException;

}
