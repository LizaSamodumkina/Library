package by.etc.samodumkina.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;
import by.etc.samodumkina.service.factory.ServiceFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 214591732476537869L;
	
	private final static Logger log = LogManager.getLogger(Controller.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
		Command command = ServiceFactory.getInstance().getCommand(commandName);
		
		String page = null;
		try {
			page = command.execute(request);
		} catch (ServiceException e) {
			log.error(e.getMessage());
			page = JSPPageName.ERROR_PAGE;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}else {
			errorMessageDirectluFromResponse(response);
		}
	}
	
	private void errorMessageDirectluFromResponse(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.getWriter().println("ERROR");
	}

}
