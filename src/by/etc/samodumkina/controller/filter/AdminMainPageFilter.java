package by.etc.samodumkina.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.etc.samodumkina.controller.JSPPageName;
import by.etc.samodumkina.controller.RequestParameterName;
import by.etc.samodumkina.controller.SessionAttributeName;
import by.etc.samodumkina.service.exception.ServiceException;
import by.etc.samodumkina.service.factory.ServiceFactory;

public class AdminMainPageFilter implements Filter {
	private final static String COMMAND_NAME = "NEED_SEND_OUT_BOOKS_TO_READING_ROOM";
	private final static String ALTERNATIVE_COMMAND_NAME = "NEED_SEND_OUT_BOOKS_TO_HOME";
	
	private final static Logger log = LogManager.getLogger(AdminMainPageFilter.class);
	
    public AdminMainPageFilter() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String user = (String) ((HttpServletRequest)request).getSession().getAttribute(SessionAttributeName.USER_NAME);
		if (user == null) {
			((HttpServletResponse)response).sendRedirect(JSPPageName.REDIRECT_MAIN_PAGE.getURL());
		} else {
		
			try {
				//внутри List уже будет вставлен как атрибут request
				if (request.getParameter(RequestParameterName.COMMAND_NAME) != null && 
						!request.getParameter(RequestParameterName.COMMAND_NAME).equals(ALTERNATIVE_COMMAND_NAME) && 
						!request.getParameter(RequestParameterName.COMMAND_NAME).equals(COMMAND_NAME)) {
					ServiceFactory.getInstance().getCommand(COMMAND_NAME).execute((HttpServletRequest)request);
				}
			} catch (ServiceException e) {
				log.error(e.getStackTrace());
			}
		}
		
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig fConfig) throws ServletException {}

}
