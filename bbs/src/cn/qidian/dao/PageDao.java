package cn.qidian.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.qidian.bean.Reply;
import cn.qidian.bean.Topic;
import cn.qidian.util.JdbcUtil;

public class PageDao {
/*    public Integer getTopicCount(Integer typeid) throws SQLException{
    	Integer allCount = 0;
    	QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
    	String sql = "select count(*) from topic where typeid=?";
    	Object[] params = {typeid};
    	Long temp = (Long) runner.query(sql, new ScalarHandler(),params);
    	allCount = temp.intValue();
    	return allCount;
    	
    }
    public List<Topic> getCurrentTopic(Integer typeid,Integer start,Integer size) throws SQLException{
       List<Topic> currentTopic = null;
       QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
       String sql = "select * from topic where typeid=? limit ?,?";
       Object[] params = {typeid,start,size};
       currentTopic = runner.query(sql, new BeanListHandler<Topic>(Topic.class), params);
       return currentTopic;
    }*/
    public Integer getReplyCount(Integer topicid) throws SQLException{
    	Integer allCount = 0;
    	QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
    	String sql = "select count(*) from reply where topicid=?";
    	Object[] params = {topicid};
    	Long temp = (Long)runner.query(sql, new ScalarHandler(), params);
    	allCount = temp.intValue();
    	return allCount;
    }
    public List<Reply> getCurrentReply(Integer topicid,Integer start,Integer size) throws SQLException{
    	List<Reply> currentReply = null;
    	QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
    	String sql = "select * from reply where topicid=? limit ?,?";
    	Object[] params = {topicid,start,size};
    	currentReply = runner.query(sql, new BeanListHandler<Reply>(Reply.class), params);
        return currentReply;    	
    }
}
