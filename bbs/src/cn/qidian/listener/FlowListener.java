package cn.qidian.listener;

import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cn.qidian.bean.Flow;
import cn.qidian.service.BbsService;

public class FlowListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent hse) {
		// TODO Auto-generated method stub
         try {
			HttpSession session = hse.getSession();
			 ServletContext context = session.getServletContext();
			 Integer todayFlow = (Integer) context.getAttribute("todayFlow");
			 synchronized(context){
				 if(todayFlow==null){
					 todayFlow = 1;
				 }else{
					 todayFlow++;
				 }
				 context.setAttribute("todayFlow", todayFlow);
			 }
			 BbsService bbsService = new BbsService();
			 Calendar cl = Calendar.getInstance();
			 cl.add(Calendar.DATE, -1);
			 Flow flow = bbsService.findFlowByhisdate(cl.getTime());
			 context.setAttribute("yesterdayFlow", flow.getCount());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sessionDestroyed(HttpSessionEvent hse) {
		// TODO Auto-generated method stub

	}

}
