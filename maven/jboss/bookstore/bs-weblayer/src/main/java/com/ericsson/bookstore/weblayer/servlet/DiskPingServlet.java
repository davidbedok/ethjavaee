package com.ericsson.bookstore.weblayer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ericsson.bookstore.diskservice.DiskReader;
import com.ericsson.bookstore.diskservice.exception.DiskServiceException;
import com.ericsson.diskstore.ejbserviceclient.domain.DiskStub;

@WebServlet("/DiskPing")
public class DiskPingServlet extends HttpServlet {

	private static final long serialVersionUID = -7058255202709402208L;

	private static final Logger LOGGER = Logger.getLogger(DiskPingServlet.class);

	@EJB
	private DiskReader reader;

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Get Disk");
		response.setCharacterEncoding("UTF-8");
		final PrintWriter out = response.getWriter();
		try {
			DiskStub diskRemote = this.reader.getDisk("WAM124");
			LOGGER.info("Remote: " + diskRemote);
			out.println(diskRemote.toString());
			DiskStub diskLocal = this.reader.getDisk("WAM124");
			LOGGER.info("Local: " + diskLocal);
			out.println(diskLocal.toString());
		} catch (final DiskServiceException e) {
			LOGGER.error(e, e);
			out.println(e.getLocalizedMessage());
		}
		out.close();
	}

}
