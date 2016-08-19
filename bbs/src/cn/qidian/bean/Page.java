package cn.qidian.bean;

import java.util.List;

public class Page {
     private Integer pageNow;
     private Integer pageSize;
     private Integer pageCount;
     private Integer allCount;
     private Integer pageUp;
     private Integer pageNext;
//     private List<Topic> topicList;
     private List<Reply> replyList;
//     private Integer typeid;
     private Integer topicid;
	public Integer getPageNow() {
		return pageNow;
	}
	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getAllCount() {
		return allCount;
	}
	public void setAllCount(Integer allCount) {
		this.allCount = allCount;
	}
	public Integer getPageUp() {
		return pageUp;
	}
	public void setPageUp(Integer pageUp) {
		this.pageUp = pageUp;
	}
	public Integer getPageNext() {
		return pageNext;
	}
	public void setPageNext(Integer pageNext) {
		this.pageNext = pageNext;
	}
/*	public List<Topic> getTopicList() {
		return topicList;
	}
	public void setTopicList(List<Topic> topicList) {
		this.topicList = topicList;
	}*/
	
	public List<Reply> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}
/*	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}*/
	public Integer getTopicid() {
		return topicid;
	}
	public void setTopicid(Integer topicid) {
		this.topicid = topicid;
	}
	
     
}
