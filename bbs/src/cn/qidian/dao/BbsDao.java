package cn.qidian.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.qidian.bean.Address;
import cn.qidian.bean.Admin;
import cn.qidian.bean.Flow;
import cn.qidian.bean.Reply;
import cn.qidian.bean.Topic;
import cn.qidian.bean.Type;
import cn.qidian.bean.User;
import cn.qidian.util.JdbcUtil;

public class BbsDao {
	
	//回复主题
	public void reply(Reply reply) throws SQLException{
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "insert into reply(name,author,content,topicid) values(?,?,?,?)";
		Object[] params = {reply.getName(),reply.getAuthor(),reply.getContent(),reply.getTopicid()};
		runner.update(sql, params);
	}
	//根据主题编号查看回复数
	public Integer getReplycountBytopicid(Integer topicid) throws SQLException{
		Integer replycount = 0;
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select count(*) from reply where topicid=?";
		Object[] params = {topicid};
		Long temp = (Long) runner.query(sql, new ScalarHandler(), params);
		replycount = temp.intValue();
		return replycount;
	}
	//根据主题编号查看对应所有回复
	public List<Reply> lookReply(Integer topicid) throws SQLException{
		List<Reply> replyList = null;
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select * from reply where topicid=?";
		Object[] params = {topicid};
		replyList = runner.query(sql, new BeanListHandler<Reply>(Reply.class), params);
		return replyList;
	}
	//根据历史日期查流量
	public Flow findFlowByhisdate(Date hisdate) throws SQLException{
		Flow flow = null;
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select * from flow where historydate=?";
		Object[] params = {hisdate};
		flow = runner.query(sql, new BeanHandler<Flow>(Flow.class), params);
		return flow;
	}
	//根据IP查询归属地
	public Address findAddressByip(String ip) throws SQLException{
		Address address = null;
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select * from address where ip=?";
		Object[] params = {ip};
		address = runner.query(sql, new BeanHandler<Address>(Address.class), params);
		return address;
	}
	//发表主题
	public void addTopic(Topic topic) throws SQLException{
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "insert into topic(name,author,content,typeid) values(?,?,?,?)";
		Object[] params = {topic.getName(),topic.getAuthor(),topic.getContent(),topic.getTypeid()};
		try {
			runner.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	//根据版块编号更新点击数
	public void updateClickBytypeid(Integer typeid) throws SQLException{
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "update type set click = click + 1 where typeid=?";
		Object[] params = {typeid};
		runner.update(sql, params);
	}
    //根据版块编号查询对应所有主题
	public List<Topic> findAllTopicBytypeid(Integer typeid) throws SQLException{
		List<Topic> topicList = new ArrayList<Topic>();
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select * from topic where typeid=? order by time desc";
		Object[] params = {typeid};
		topicList = runner.query(sql, new BeanListHandler<Topic>(Topic.class), params);
		return topicList;
	}
	//根据版块编号查最新主题
	public Topic getNewTopicBytypeid(Integer typeid) throws SQLException{
		Topic newtopic = null;
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "SELECT * FROM topic WHERE typeid = ? ORDER BY time DESC";
		Object[] params = {typeid};
		newtopic = runner.query(sql, new BeanHandler<Topic>(Topic.class), params);
		return newtopic;
	}
	//根据版块编号获取主题数
	public Integer getTopicNumBytypeid(Integer typeid) throws SQLException{
		int topicnum = 0;
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select count(*) from topic where typeid=?";
		Object[] params = {typeid};
		Long temp = (Long)runner.query(sql, new ScalarHandler(), params);
		topicnum = temp.intValue();
		return topicnum;
	}
	//根据adminid查询版主
	public Admin findAdminByadminid(Integer adminid) throws SQLException{
		Admin admin = null;
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select * from admin where adminid=?";
		Object[] params = {adminid};
		admin = runner.query(sql, new BeanHandler<Admin>(Admin.class), params);
		return admin;
	}
    //查找所有版块
    public List<Type> findAllType() throws SQLException{
   	 List<Type> typeList = new ArrayList<Type>();
   	 QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
   	 String sql = "select * from type";
   	 typeList = runner.query(sql, new BeanListHandler<Type>(Type.class));
   	 return typeList;
    }
	
	//登录
	public User login(User user) throws Exception{
		User u = null;
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select * from user where username = ? and password = ?";
		Object[] params = {user.getUsername(),user.getPassword()};
		u = runner.query(sql,new BeanHandler<User>(User.class),params);
		return u;
	}
    
     //验证用户是否存在
     public boolean check(String username) throws SQLException{
    	 boolean flag = false;
    	 QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
    	 String sql = "select * from user where username=?";
    	 Object[] params = {username};
    	 User user = runner.query(sql, new BeanHandler<User>(User.class), params);
    	 if(user!=null){
    		 flag = true;
    	 }
    	 return flag;
     }
     
     //用户注册
     public void regist(User user) throws SQLException{
    	 QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
    	 String sql = "insert into user(username,password,gender,email) values(?,?,?,?)";
    	 Object[] params = {user.getUsername(),user.getPassword(),user.getGender(),user.getEmail()};
         runner.update(sql, params);
     }
   
     
}
