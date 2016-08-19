package cn.qidian.util;


import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.qidian.bean.Page;
import cn.qidian.bean.Topic;

public class Demo {
     public static void main(String[] args) throws Exception{
         List<Topic> currentpage = null;
         Integer start = 0;
         Integer size = 4;
         QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
         String sql = "select * from topic order by topicid asc limit ?,?";
         Object[] params = {start,size};
         currentpage = runner.query(sql, new BeanListHandler<Topic>(Topic.class), params);
    	for(Topic topic:currentpage){
    		System.out.println(topic.getAuthor());
    	}
    	
     }
}
