package by.etc.samodumkina.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.controller.SessionAttributeName;

public class IndexPageFilter implements Filter {
	
	private final static String FALSE = "false";

    public IndexPageFilter() {}

	public void destroy() {}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String isRegistration = (String) ((HttpServletRequest)request).getSession(true).getAttribute(SessionAttributeName.IS_REGISTRATION_FORM);
		
		if (isRegistration == null) {
			((HttpServletRequest)request).getSession(true).setAttribute(SessionAttributeName.IS_REGISTRATION_FORM, FALSE);
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}
