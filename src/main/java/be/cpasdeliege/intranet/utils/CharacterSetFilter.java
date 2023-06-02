package be.cpasdeliege.intranet.utils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class CharacterSetFilter implements Filter {
	
	public void init(FilterConfig filterConfig) throws ServletException {
        // Méthode init si nécessaire
    }
	
	public void doFilter( ServletRequest request, ServletResponse response, FilterChain next) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		next.doFilter(request, response);
	}
	
	public void destroy() {
        // Méthode destroy si nécessaire
    }

}
