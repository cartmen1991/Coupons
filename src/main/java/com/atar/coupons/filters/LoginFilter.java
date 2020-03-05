package com.atar.coupons.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atar.coupons.internal.UserLoginData;
import com.atar.coupons.logic.CacheController;


@Component
public class LoginFilter implements Filter {

	@Autowired
	private CacheController cacheController;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		String pageRequested = req.getRequestURL().toString();

		if (pageRequested.endsWith("/login")) {
			chain.doFilter(request, response);
			return;
		}

		if (pageRequested.endsWith("/customers") && req.getMethod().toString().equals("POST")) {
			chain.doFilter(request, response);
			return;
		}

//		String token = request.getParameter("token");
		String token = req.getHeader("Authorization");
		UserLoginData userLoginData = (UserLoginData) cacheController.get(token);

		if(userLoginData != null) {
			request.setAttribute("userLoginData", userLoginData);
			// you are logged in - all is good.
			// move forward to the next filter in chain
			chain.doFilter(request, response);
			return;		
		}

		HttpServletResponse res = (HttpServletResponse) response;
		// you are not logged in.
		res.setStatus(401);
	}





	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}





}
