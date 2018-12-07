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

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*response.setContentType("text/html");
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		PrintWriter out = response.getWriter();
		out.append("your login: " + login);
		out.append("<br/> your password: " + password);*/
		
		String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
		Command command = ServiceFactory.getInstance().getCommand(commandName);
		
		String page = null;
		try {
			page = command.execute(request);
		} catch (ServiceException e) {
			page = JSPPageName.ERROR_PAGE;
		}
		
		//request.setAttribute("login", request.getParameter("login"));
		//request.setAttribute("password", request.getParameter("password"));
		
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
