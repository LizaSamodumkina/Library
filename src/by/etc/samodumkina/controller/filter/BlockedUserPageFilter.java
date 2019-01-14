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
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.etc.samodumkina.bean.User;
import by.etc.samodumkina.controller.JSPPageName;
import by.etc.samodumkina.controller.RequestAttributeName;
import by.etc.samodumkina.controller.SessionAttributeName;
import by.etc.samodumkina.service.exception.ServiceException;
import by.etc.samodumkina.service.factory.ServiceFactory;

public class BlockedUserPageFilter implements Filter {
	
	private final static String COMMAND = "BLOKED_USERS";
	
	private final static Logger log = LogManager.getLogger(BlockedUserPageFilter.class);

    public BlockedUserPageFilter() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String user = (String) ((HttpServletRequest)request).getSession().getAttribute(SessionAttributeName.USER_NAME);
		if (user == null) {
			((HttpServletResponse)response).sendRedirect(JSPPageName.REDIRECT_MAIN_PAGE.getURL());
		} else {
			try {
				List<User> users = ServiceFactory.getInstance().getCommand(COMMAND).execute((HttpServletRequest)request);
				
				request.setAttribute(RequestAttributeName.LIST, users);
			} catch (ServiceException e) {
				log.error(e.getStackTrace());
			}
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}
