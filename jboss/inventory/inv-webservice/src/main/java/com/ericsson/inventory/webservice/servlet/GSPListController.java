package com.ericsson.inventory.webservice.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ericsson.inventory.ejbservice.exception.AdaptorException;
import com.ericsson.inventory.ejbservice.facade.InventoryFacade;
import com.ericsson.inventory.persistence.domain.InventoryItem;
import com.ericsson.inventory.persistence.domain.InventoryType;
import com.ericsson.inventory.webservice.common.ListAttribute;
import com.ericsson.inventory.webservice.common.Page;

@WebServlet("/GSPList")
public class GSPListController extends HttpServlet implements ListAttribute {

	private static final long serialVersionUID = 1190531898097507410L;

	private static final Logger LOGGER = Logger.getLogger(GSPListController.class);

	@EJB
	private InventoryFacade facade;

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Get All Inventories by type");
		try {
			final List<InventoryItem> inventories = this.facade.getInventories(InventoryType.BOOK);
			request.setAttribute(ATTR_INVENTORIES, inventories);
		} catch (final AdaptorException e) {
			LOGGER.error(e, e);
		}
		final RequestDispatcher view = request.getRequestDispatcher(Page.GROOVY_SERVER_PAGES.getServerPage());
		view.forward(request, response);
	}

}
