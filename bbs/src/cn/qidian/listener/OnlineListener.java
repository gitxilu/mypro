package cn.qidian.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class OnlineListener implements HttpSessionAttributeListener {

	public void attributeAdded(HttpSessionBindingEvent hsbe) {
		// TODO Auto-generated method stub
          String name = hsbe.getName();
          if("user".equals(name)){
        	  HttpSession session = hsbe.getSession();
        	  ServletContext context = session.getServletContext();
        	  Integer online = (Integer) context.getAttribute("online");
        	  synchronized (context){
        		 if(online==null){
        			 online = 1;
        		 } else{
        			 online++;
        		 }
        		 context.setAttribute("online", online);
        	  }
          }
	}

	public void attributeRemoved(HttpSessionBindingEvent hsbe) {
		// TODO Auto-generated method stub
          HttpSession session = hsbe.getSession();
          ServletContext context = session.getServletContext();
          Integer online = (Integer) context.getAttribute("online");
          if(online!=null&&online>0){
              synchronized (context){
            	  online--;
            	  context.setAttribute("online", online);
              }
          }
	}

	public void attributeReplaced(HttpSessionBindingEvent hsbe) {
		// TODO Auto-generated method stub

	}

}
