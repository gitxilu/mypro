package cn.qidian.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DoorFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest hreq = (HttpServletRequest) request;
		HttpServletResponse hres = (HttpServletResponse) response;
		String referer = hreq.getHeader("referer");
//		System.out.println(referer);
		if(referer!=null){
			chain.doFilter(hreq, hres);
		}else{
			hres.setContentType("text/html;charset=utf-8");
			hres.getWriter().write(""+"<script>"+"window.alart('Äã»¹Î´µÇÂ¼');"+
					"window.locathion.href='http://localhost:8080/bbs/jsp/welcome.jsp'"+
					"<script>");
		}

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
