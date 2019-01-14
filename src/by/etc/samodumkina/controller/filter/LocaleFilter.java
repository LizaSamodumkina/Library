package by.etc.samodumkina.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.controller.RequestParameterName;
import by.etc.samodumkina.controller.SessionAttributeName;

public class LocaleFilter implements Filter {

    public LocaleFilter() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;   
	    if (req.getParameter(RequestParameterName.SESSION_LOCALE) != null) {
	    	req.getSession().setAttribute(SessionAttributeName.LOCALE, req.getParameter(RequestParameterName.SESSION_LOCALE));
	    }
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}
