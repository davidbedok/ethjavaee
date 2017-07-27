package com.ericsson.lottery.webservice.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ericsson.lottery.ejbservice.domain.EventStub;
import com.ericsson.lottery.ejbservice.exception.AdaptorException;
import com.ericsson.lottery.ejbservice.facade.LiveLotteryFacade;

@WebServlet("/NewDraw")
public class NewDrawServlet extends HttpServlet {

	private static final long serialVersionUID = 3883046058049780338L;

	private static final Logger LOGGER = Logger.getLogger(NewDrawServlet.class);

	private static final String STREAM_NAME = "lottery";
	private static final long TICK_DELAY = 1000;

	@EJB
	private LiveLotteryFacade liveFacade;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Start new Draw servlet");
		response.setContentType("text/event-stream");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Connection", "keep-alive");

		final PrintWriter writer = response.getWriter();

		try {
			while (true) {
				Future<EventStub> eventFuture = this.liveFacade.draw();
				do {
					Thread.sleep(TICK_DELAY);
					LOGGER.info("Tick...");
				} while (!eventFuture.isDone() && !eventFuture.isCancelled());

				if (eventFuture.isDone()) {
					EventStub event = eventFuture.get();
					LOGGER.info("New event: " + event);
					writer.write("event:" + STREAM_NAME + "\n");
					writer.write("data: " + event.printEvent() + "\n\n");
					writer.flush();
				}
			}
		} catch (final InterruptedException | AdaptorException | ExecutionException e) {
			LOGGER.error(e, e);
		}
	}

}
