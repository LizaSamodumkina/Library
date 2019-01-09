package by.etc.samodumkina.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.etc.samodumkina.service.exception.ServiceException;
import by.etc.samodumkina.service.factory.ServiceFactory;

public class AdminOrderStoryPageFilter implements Filter {
	private final static String COMMAND = "TAKE_ADMIN_ORDER_STORY";
	private final static Logger log = LogManager.getLogger(AdminOrderStoryPageFilter.class);

    public AdminOrderStoryPageFilter() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		try {
			ServiceFactory.getInstance().getCommand(COMMAND).execute((HttpServletRequest)request);
		} catch (ServiceException e) {
			log.error(e.getStackTrace());
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}
