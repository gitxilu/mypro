package cn.qidian.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import cn.qidian.bean.Address;
import cn.qidian.bean.Admin;
import cn.qidian.bean.Flow;
import cn.qidian.bean.Reply;
import cn.qidian.bean.Topic;
import cn.qidian.bean.Type;
import cn.qidian.bean.User;
import cn.qidian.dao.BbsDao;
import cn.qidian.exception.NameExistsException;
import cn.qidian.exception.NameOrPasswordErrorException;
import cn.qidian.util.WebUtil;

public class BbsService{   
    private BbsDao bbsDao = new BbsDao(); 
    
    //主题回复
    public void reply(Reply reply) throws SQLException{
    	if(reply!=null){
    		bbsDao.reply(reply);
    	}
    }
    //根据对应编号查询所有主题
    public List<Topic> findAllTopicBytypeid(Integer typeid) throws SQLException{
        List<Topic> topicList = bbsDao.findAllTopicBytypeid(typeid);
        for(Topic topic:topicList){
        	Integer replycount = bbsDao.getReplycountBytopicid(topic.getTopicid());
        	topic.setReplycount(replycount);
        }
        return topicList;
    }
    
    //根据主题编号查看对应所有回复
    public List<Reply> lookReply(Integer topicid) throws SQLException{
    	List<Reply> replyList = bbsDao.lookReply(topicid);
    	return replyList;
    }
    //根据历史日期查询流量
    public Flow findFlowByhisdate(Date hisdate) throws SQLException{
    	Flow flow = bbsDao.findFlowByhisdate(hisdate);
    	return flow;
    }
    //根据IP查询归属地
    public Address findAddressByip(String ip) throws SQLException{
    	
		Address address = bbsDao.findAddressByip(ip);
        if(address==null){
        	address = new Address();
        	address.setIp(ip);
        	address.setLocation("北海道");
        }
        return address;

    }
    //发表主题
    public void addTopic(Topic topic) throws SQLException{
    	if(topic!=null){
		  bbsDao.addTopic(topic);
    	}
    }
    //根据版块编号更新点击数
    public void updateClickBytypeid(Integer typeid) throws SQLException{
    	
    	bbsDao.updateClickBytypeid(typeid);
    }

    //根据点击查询版块
    public List<Type> findAllTypeByclick(List<Type> typeList){
    	List<Type> typeDesc = new ArrayList<Type>();
    	for(Type type:typeList){
    		typeDesc.add(type);
    	}
    	Collections.sort(typeDesc, new Comparator<Type>(){
            public int compare(Type t1,Type t2){
            	if(t1.getClick()<t2.getClick()){
            		return 1;
            	}else if(t1.getClick()>t2.getClick()){
            		return -1;
            	}else{
            		return 0;
            	}
            } 		
       	});
    	return typeDesc;
    }
	//查找所有版块
	public List<Type> findAllType() throws SQLException{
			List<Type> typeList = bbsDao.findAllType();
			for(Type type:typeList){
				Admin admin = bbsDao.findAdminByadminid(type.getAdminid());
				type.setAdmin(admin);
				Integer topicnum = bbsDao.getTopicNumBytypeid(type.getTypeid());
				type.setTopicnum(topicnum);
        	    Topic newtopic = bbsDao.getNewTopicBytypeid(type.getTypeid());
				type.setNewtopic(newtopic);
			}
			return typeList;
	}
    
    //登录
	public User login(User user) throws Exception {
		//将明密码转成MD5密码
		String passwordMD5 = WebUtil.encodeByMD5(user.getPassword());
		user.setPassword(passwordMD5);
	    return bbsDao.login(user);
	}
	//注册
	public void regist(User user) throws Exception{
		try {
			boolean flag = bbsDao.check(user.getUsername());
			if(!flag){
				String passwordMD5 = WebUtil.encodeByMD5(user.getPassword());
				user.setPassword(passwordMD5);
				bbsDao.regist(user);
				
			}else{
				throw new NameExistsException();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
}
