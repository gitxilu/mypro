package cn.qidian.bean;

import java.sql.Timestamp;

public class Reply {
   private Integer replyid;
   private String name;
   private String author;
   private String content;
   private Timestamp time;
   private Topic topic;
   private Integer topicid;
public Integer getReplyid() {
	return replyid;
}
public void setReplyid(Integer replyid) {
	this.replyid = replyid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Timestamp getTime() {
	return time;
}
public void setTime(Timestamp time) {
	this.time = time;
}
public Topic getTopic() {
	return topic;
}
public void setTopic(Topic topic) {
	this.topic = topic;
}
public Integer getTopicid() {
	return topicid;
}
public void setTopicid(Integer topicid) {
	this.topicid = topicid;
}
   
   
}
