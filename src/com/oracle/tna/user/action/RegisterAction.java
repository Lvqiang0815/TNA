package com.oracle.tna.user.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.oracle.tna.domain.User;
import com.oracle.tna.exception.UserException;
import com.oracle.tna.service.UserService;

@Namespace("/user")
@ParentPackage(value="struts-default")
@Action(value="registerAction")
@Controller //将RegisterAction类标注为SpringBean
@Scope("prototype")
@Results({
	@Result(name="success",location="/user/Index.jsp"),
	@Result(name="input",location="/user/registration.jsp")
})
public class RegisterAction extends ActionSupport {
	public RegisterAction(){
		System.out.println("00000000000000000000000");
	}

	private static final long serialVersionUID = 1L;
	
	@Resource
	private UserService userSvc;
	
	private String username;
	private String password;
	private String confirmpassword;
	private String realname;
	private String idnumber;
	private String telphone;
	
	@Override
	public String execute() {
		System.out.println("11111111111111111111");
		User user = new User(1, username, password, realname, idnumber, telphone);
		try {
			if (!password.equals(confirmpassword)) {
				this.addActionError("passwordmap.error");
				return INPUT;
			}
			userSvc.register(user);
		} catch(UserException e) {
			this.addActionError(this.getText("user.regist.error",new String[]{e.getMessage()}));
			return INPUT;
		}
		
		//获取request对象
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("user", user);// 将user对象存入session作用于中
		
		return SUCCESS;
	}

	@RequiredFieldValidator(key="username.error")
	public String getUsername() {
		System.out.println("username=" + username);
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@RequiredFieldValidator(key="password.error")
	public String getPassword() {
		System.out.println("password=" + password);
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@RequiredFieldValidator(key="comfirmpassword.error")
	public String getConfirmpassword() {
		System.out.println("confirmpassword=" + confirmpassword);
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	@RequiredFieldValidator(key="realname.error")
	public String getRealname() {
		System.out.println("realname=" + realname);
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public void setRealName(String realname) {
		this.realname = realname;
	}

	@RequiredStringValidator(key="idnumber.error")
	public String getIdnumber() {
		System.out.println("idnumber=" + idnumber);
		return idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	@RequiredFieldValidator(key="telphone.error")
	public String getTelphone() {
		System.out.println("telphone=" + telphone);
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

}
