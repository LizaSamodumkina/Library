package by.etc.samodumkina.controller.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.etc.samodumkina.bean.Book;
import by.etc.samodumkina.service.CommandName;
import by.etc.samodumkina.service.exception.ServiceException;
import by.etc.samodumkina.service.factory.ServiceFactory;

public class LikedBookPageFilter implements Filter {
	private final static String ATTRIBUTE_NAME = "list";
	
	private final static Logger log = LogManager.getLogger(LikedBookPageFilter.class);

    public LikedBookPageFilter() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			List<Book> books = ServiceFactory.getInstance().getCommand(CommandName.GET_USER_LIKED_BOOKS.name()).execute((HttpServletRequest)request);
			request.setAttribute(ATTRIBUTE_NAME, books);
		} catch (ServiceException e) {
			log.error(e.getStackTrace());
		}
		
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {}

}
