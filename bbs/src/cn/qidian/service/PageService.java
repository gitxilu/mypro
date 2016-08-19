package cn.qidian.service;

import java.sql.SQLException;
import java.util.List;

import cn.qidian.bean.Page;
import cn.qidian.bean.Reply;
import cn.qidian.bean.Topic;
import cn.qidian.dao.BbsDao;
import cn.qidian.dao.PageDao;

public class PageService {
	   public Page getCurrentReply(Integer pageNow,Integer topicid) throws SQLException{
		   PageDao pageDao = new PageDao();
		   Page page  = new Page();
		   //����ҳ���С
		   page.setPageSize(3);
		   page.setTopicid(topicid);
		   page.setAllCount(pageDao.getReplyCount(topicid));
		   //����ҳ��
		   Integer pageCount = 0;
		   if(page.getAllCount()%page.getPageSize()==0){
			   pageCount = page.getAllCount()/page.getPageSize();
		   }else {
			   pageCount = page.getAllCount()/page.getPageSize()+1;
		   }
		   page.setPageCount(pageCount);
		   //���õ�ǰҳ
		   if(pageNow<=0){
			   pageNow = 1;
		   }else if(pageNow>=page.getPageCount()){
			   pageNow = page.getPageCount();
		   }
		   page.setPageNow(pageNow);
		   //������һҳ
		   Integer pageUp = 0;
		   pageUp = page.getPageNow()-1;
		   if(pageUp<=0){
			   pageUp = page.getPageNow();
		   }
		   page.setPageUp(pageUp);
		   //������һҳ
		   Integer pageNext = 0;
		   pageNext = page.getPageNow()+1;
		   if(pageNext>=page.getPageCount()){
			   pageNext = page.getPageCount();
		   }
		   page.setPageNext(pageNext);
		   //���õ�ǰ��¼
		   Integer start = (page.getPageNow()-1)*page.getPageSize();
		   Integer size = page.getPageSize();

		   if(page.getAllCount()>0){
			   List<Reply> replyList = pageDao.getCurrentReply(topicid, start, size);
			   page.setReplyList(replyList);
		   }
		   return page;
	   }
	
/*   public Page getCurrentTopic(Integer pageNow,Integer typeid) throws SQLException{
	   PageDao pageDao = new PageDao();
	   BbsDao bbsDao = new BbsDao();
	   Page page  = new Page();
	   //����ҳ���С
	   page.setPageSize(3);
	   page.setTypeid(typeid);
	   page.setAllCount(pageDao.getTopicCount(typeid));
	   //����ҳ��
	   Integer pageCount = 0;
	   if(page.getAllCount()%page.getPageSize()==0){
		   pageCount = page.getAllCount()/page.getPageSize();
	   }else {
		   pageCount = page.getAllCount()/page.getPageSize()+1;
	   }
	   page.setPageCount(pageCount);
	   //���õ�ǰҳ
	   if(pageNow<=0){
		   pageNow = 1;
	   }else if(pageNow>=page.getPageCount()){
		   pageNow = page.getPageCount();
	   }
	   page.setPageNow(pageNow);
	   //������һҳ
	   Integer pageUp = 0;
	   pageUp = page.getPageNow()-1;
	   if(pageUp<=0){
		   pageUp = page.getPageNow();
	   }
	   page.setPageUp(pageUp);
	   //������һҳ
	   Integer pageNext = 0;
	   pageNext = page.getPageNow()+1;
	   if(pageNext>=page.getPageCount()){
		   pageNext = page.getPageCount();
	   }
	   page.setPageNext(pageNext);
	   //���õ�ǰ��¼
	   Integer start = (page.getPageNow()-1)*page.getPageSize();
	   Integer size = page.getPageSize();

	   if(page.getAllCount()>0){
		   List<Topic> topicList = pageDao.getCurrentTopic(typeid,start, size);
		   for(Topic topic:topicList){
			   Integer replycount = bbsDao.getReplycountBytopicid(topic.getTopicid());
			   topic.setReplycount(replycount);
		   }
		   
		   page.setTopicList(topicList);
	   }
	   return page;
   }*/
	
}
