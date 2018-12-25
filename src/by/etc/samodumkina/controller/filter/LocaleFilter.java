package by.etc.samodumkina.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LocaleFilter implements Filter {

    public LocaleFilter() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;   
	    if (req.getParameter("sessionLocale") != null) {
	    	req.getSession().setAttribute("local", req.getParameter("sessionLocale"));
	    }
	    
	    System.out.println("LocaleFilter");
		
		chain.doFilter(request, response);
		
		System.out.println("end locale filter");
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}
