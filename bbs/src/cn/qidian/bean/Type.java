package cn.qidian.bean;


public class Type {
    private Integer typeid;
    private String name;
    private String imagepath;
    private Integer click;
    private Integer adminid;
    private Admin admin;
    private Integer topicnum;
    private Topic newtopic;
    
	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public Integer getClick() {
		return click;
	}
	public void setClick(Integer click) {
		this.click = click;
	}
	public Integer getAdminid() {
		return adminid;
	}
	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Integer getTopicnum() {
		return topicnum;
	}
	public void setTopicnum(Integer topicnum) {
		this.topicnum = topicnum;
	}
	public Topic getNewtopic() {
		return newtopic;
	}
	public void setNewtopic(Topic newtopic) {
		this.newtopic = newtopic;
	}
    
}
