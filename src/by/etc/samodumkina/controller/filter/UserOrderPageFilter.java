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

import by.etc.samodumkina.bean.Order;
import by.etc.samodumkina.service.exception.ServiceException;
import by.etc.samodumkina.service.factory.ServiceFactory;

public class UserOrderPageFilter implements Filter {
	
	private final static String COMMAND = "TAKE_USER_ORDERS";
	private final static String LIST = "list";
	
	private final static Logger log = LogManager.getLogger(UserOrderPageFilter.class);

    public UserOrderPageFilter() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			List<Order> orders = ServiceFactory.getInstance().getCommand(COMMAND).execute((HttpServletRequest)request);
			
			request.setAttribute(LIST, orders);
		} catch (ServiceException e) {
			log.error(e.getStackTrace());
		}
		
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig fConfig) throws ServletException {}

}
