package com.ericsson.booklight.weblayer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ericsson.booklight.ejbservice.domain.BookStub;
import com.ericsson.booklight.ejbservice.exception.FacadeException;
import com.ericsson.booklight.ejbservice.facade.BookFacade;

@WebServlet("/BookPing")
public class BookPingServlet extends HttpServlet {

	private static final long serialVersionUID = -7058255202709402208L;

	@EJB
	private BookFacade facade;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		final PrintWriter out = response.getWriter();
		try {
			final BookStub book = this.facade.getBook("978-0441172719");
			out.println(book.toString());
		} catch (final FacadeException e) {
			out.println(e.getLocalizedMessage());
		}
		out.close();
	}

}
