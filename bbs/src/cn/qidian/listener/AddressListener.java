package cn.qidian.listener;

import java.sql.SQLException;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.qidian.bean.Address;
import cn.qidian.service.BbsService;

public class AddressListener implements ServletRequestListener {

	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub

	}

	public void requestInitialized(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
        try {
			HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
			HttpSession session = request.getSession();
			Address address = (Address) session.getAttribute("address");
			if(address==null){
				String ip = request.getRemoteAddr();
				BbsService bbsService = new BbsService();
				address = bbsService.findAddressByip(ip);
				session.setAttribute("address", address);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
