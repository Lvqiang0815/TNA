package com.oracle.tna.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oracle.tna.domain.User;

//@WebFilter("/user/Exam.jsp")
public class RepeatExamFilter implements Filter {
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		//当前用户未考过试，放行
		if (user == null) {
			chain.doFilter(request, response);
			return;
		}
		//当前用户考过试
		//得到当前访问的资源
		//String requestURL = req.getRequestURL().toString();
		
		request.getRequestDispatcher("/user/ee.jsp").forward(request, resp);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
