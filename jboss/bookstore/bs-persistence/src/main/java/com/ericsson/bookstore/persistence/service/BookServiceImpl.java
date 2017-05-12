package com.ericsson.bookstore.persistence.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.ericsson.bookstore.persistence.entity.Book;
import com.ericsson.bookstore.persistence.entity.trunk.BookCategory;
import com.ericsson.bookstore.persistence.exception.PersistenceServiceException;
import com.ericsson.bookstore.persistence.parameter.BookParameter;
import com.ericsson.bookstore.persistence.query.BookQuery;

@Stateless(mappedName = "ejb/bookService")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class BookServiceImpl implements BookService {

	private static final Logger LOGGER = Logger.getLogger(BookServiceImpl.class);

	@PersistenceContext(unitName = "bs-persistence-unit")
	private EntityManager entityManager;

	@Override
	public boolean exists(String isbn) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Check Book by ISBN (" + isbn + ")");
		}
		try {
			return this.entityManager.createNamedQuery(BookQuery.COUNT_BY_ISBN, Long.class).setParameter(BookParameter.ISBN, isbn).getSingleResult() == 1;
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error during counting Books by ISBN (" + isbn + ")! " + e.getLocalizedMessage(), e);
		}
	}

	@Override
	public Book create(String isbn, String author, String title, int numberOfPages, double price, BookCategory category) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Create Book (isbn: " + isbn + ", author: " + author + ", title: " + title + ", numberOfPages: " + numberOfPages + ", price: " + price
					+ ", category: " + category + ")");
		}
		try {
			final Book book = new Book(isbn, author, title, numberOfPages, price, category);
			this.entityManager.persist(book);
			return book;
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error during persisting Book (" + isbn + ")! " + e.getLocalizedMessage(), e);
		}
	}

	@Override
	public Book read(Long id) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get Book by id (" + id + ")");
		}
		Book result = null;
		try {
			result = this.entityManager.createNamedQuery(BookQuery.GET_BY_ID, Book.class).setParameter(BookParameter.ID, id).getSingleResult();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Book by id (" + id + ")! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

	@Override
	public Book read(String isbn) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get Book by ISBN (" + isbn + ")");
		}
		Book result = null;
		try {
			result = this.entityManager.createNamedQuery(BookQuery.GET_BY_ISBN, Book.class).setParameter(BookParameter.ISBN, isbn).getSingleResult();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Book by ISBN (" + isbn + ")! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

	@Override
	public List<Book> readAll() throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get Books");
		}
		List<Book> result = null;
		try {
			result = this.entityManager.createNamedQuery(BookQuery.GET_ALL, Book.class).getResultList();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Books! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

	@Override
	public List<Book> read(BookCategory category) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get Books by Category");
		}
		List<Book> result = null;
		try {
			result = this.entityManager.createNamedQuery(BookQuery.GET_ALL_BY_CATEGORY, Book.class).setParameter(BookParameter.CATEGORY, category)
					.getResultList();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Books! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

	@Override
	public Book update(String isbn, String author, String title, int numberOfPages, double price, BookCategory category) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Update Book (isbn: " + isbn + ", author: " + author + ", title: " + title + ", numberOfPages: " + numberOfPages + ", price: " + price
					+ ", category: " + category + ")");
		}
		try {
			final Book book = this.read(isbn);
			book.setAuthor(author);
			book.setTitle(title);
			book.setNumberOfPages(numberOfPages);
			book.setPrice(price);
			book.setCategory(category);
			return this.entityManager.merge(book);
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when mergning Book! " + e.getLocalizedMessage(), e);
		}
	}

	@Override
	public void delete(String isbn) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Remove Book by ISBN (" + isbn + ")");
		}
		try {
			this.entityManager.createNamedQuery(BookQuery.REMOVE_BY_ISBN).setParameter(BookParameter.ISBN, isbn).executeUpdate();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when removing Book by ISBN (" + isbn + ")! " + e.getLocalizedMessage(), e);
		}
	}

}
