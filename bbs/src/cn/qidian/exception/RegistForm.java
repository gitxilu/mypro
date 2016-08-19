package cn.qidian.exception;

import java.util.HashMap;
import java.util.Map;

import cn.qidian.bean.User;

public class RegistForm {
    private String username;
    private String email;
	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}
	Map<String,String> errors = new HashMap<String,String>();
	public Map<String,String> getErrors(){
		return errors;
	}
	
	public boolean validate(User user){
		boolean flag = false;
		username = user.getUsername();
		String password = user.getPassword();
		email = user.getEmail();
		
		if(this.validateUsername(username)&&this.validatePassword(password)&&this.validateEmail(email)){
			flag = true;
		}
		return flag;
	}
	private boolean validateUsername(String username){
		 boolean flag = false;
		 if(username!=null&&username.trim().length()>0){
			 if(username.matches("[\u4E00-\uFA29]+")){
				 flag = true;
			 }else {
				 this.errors.put("username", "<font color='palegodenrod'>用户名必须是中文</font>");
			 }
		 }else{
			 this.errors.put("username", "<font color='palegodenrod'>用户名不能为空</font>");
		 }
		 return flag;
	}
	private boolean validatePassword(String password){
		boolean flag = false;
		if(password!=null&&password.trim().length()>0){
			if(password.matches("[0-9]{6}")){
				flag = true;
			}else{
				this.errors.put("password", "<font color='palegodenrod'>密码必须是0-9的六位数</font>");
			}
		}else{
			this.errors.put("password", "<font color='palegodenrod'>密码必填</font>");
		}
		return flag;
	}
	private boolean validateEmail(String email){
		boolean flag = false;
		if(email!=null&&email.trim().length()>0){
			if(email.matches("\\w+@\\w+(\\.\\w+)+")){
				flag = true;
			}else{
				this.errors.put("email", "<font color='palegodenrod'>邮箱格式不符</font>");
			}
		}else{
			this.errors.put("email", "<font color='palegodenrod'>邮箱必填</font>");
		}
		return flag;
	}
}
