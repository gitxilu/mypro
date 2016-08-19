package cn.qidian.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CopyrightFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest sreq, ServletResponse sres,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest) sreq;
        HttpServletResponse response = (HttpServletResponse) sres;
        MyResponse myResponse = new MyResponse(response);
        chain.doFilter(request, myResponse);
        byte[] bt = myResponse.getBuffer();
        String content = new String(bt,"utf-8");
        content+="<center><div  style='position:absolute;margin-top:2%;margin-left:30%'><font size='2' color='gray'>Copyright:版权归起点工作室所有     2014.12.03</font></div></center>";
	      response.setContentType("text/html;charset=utf-8");
	      response.getWriter().write(content);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	
}

class MyResponse extends HttpServletResponseWrapper {
	private HttpServletResponse response;
    private PrintWriter pw;
    private ByteArrayOutputStream bout = new ByteArrayOutputStream();
	public MyResponse(HttpServletResponse response) {
		super(response);
		this.response = response;
	}
	public byte[] getBuffer(){
		if(pw!=null){
			pw.flush();
		}
		return bout.toByteArray();
	}
    public PrintWriter getWriter(){
    	try {
			pw = new PrintWriter(new OutputStreamWriter(bout,"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return pw;
    }
    
}
