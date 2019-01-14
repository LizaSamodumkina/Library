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

import by.etc.samodumkina.controller.JSPPageName;
import by.etc.samodumkina.controller.SessionAttributeName;

public class AddNewBookPageFilter implements Filter {

    public AddNewBookPageFilter() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String user = (String) ((HttpServletRequest)request).getSession().getAttribute(SessionAttributeName.USER_NAME);
		if (user == null) {
			((HttpServletResponse)response).sendRedirect(JSPPageName.REDIRECT_MAIN_PAGE.getURL());
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}
