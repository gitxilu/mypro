package cn.qidian.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EncodingFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest sreq, ServletResponse sres,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		  HttpServletRequest request = (HttpServletRequest)sreq;
		  HttpServletResponse response = (HttpServletResponse)sres;
		  RequestProxy requestProxy = new RequestProxy(request);
		  HttpServletRequest hsr = requestProxy.getRequestProxy();
		  chain.doFilter(hsr, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}

class RequestProxy{
	private HttpServletRequest request;
	public RequestProxy(HttpServletRequest request){
		this.request = request;
	}
	public HttpServletRequest getRequestProxy(){
		return (HttpServletRequest)Proxy.newProxyInstance(
				RequestProxy.class.getClassLoader(), 
				request.getClass().getInterfaces(), 
				new InvocationHandler(){

					public Object invoke(
							Object proxy,
							Method method,
							Object[] args) throws Throwable {
						// TODO Auto-generated method stub
						Object returnValue = "";
						String methodName = method.getName();
						if(methodName.equals("getParameter")){
							String methodType = request.getMethod();
						    if("GET".equalsIgnoreCase(methodType)){
						    	String temp = (String) method.invoke(request, args);
						    	byte[] bt = temp.getBytes("ISO8859-1");
						    	returnValue = new String(bt,"utf-8");
						    }else if("POST".equalsIgnoreCase(methodType)){
						    	request.setCharacterEncoding("utf-8");
						    	returnValue = method.invoke(request, args);
						    }
						  }else{
							  returnValue = method.invoke(request, args);
						  }
						return returnValue;
					}
					
				});
		
	}
}
