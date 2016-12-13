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
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.oracle.tna.domain.User;
import com.oracle.tna.exception.UserException;
import com.oracle.tna.service.UserService;

@Namespace("/user")
@ParentPackage(value="struts-default")
@Action("personalInfoAction")
@Controller
@Scope("singleton")
@Results({
	@Result(name="success",location="/user/Index.jsp"),
	@Result(name="input",location="/user/PersonalInfo.jsp")
})
public class PersonalInfoAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String confirmpassword;
	private String realname;
	private String idnumber;
	private String telphone;
	
	@Resource
	UserService userSvc;

	public PersonalInfoAction() {
		super();
	}
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		try{
			if (!password.equals(confirmpassword)) {
				this.addActionError("passwordmap.error");
				return INPUT;
			}
			userSvc.update(new User(user.getUid(), username, password, realname, idnumber, telphone));
		}catch(UserException e) {
			return INPUT;
		}
		
		return SUCCESS;
	}

	@RequiredStringValidator(key="username.error")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@RequiredStringValidator(key="password.error",trim=true,shortCircuit=true)
	@StringLengthFieldValidator(key="passwordlength.error",maxLength="10",minLength="6")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@RequiredStringValidator(key="password.error",trim=true,shortCircuit=true)
	@StringLengthFieldValidator(key="passwordlength.error",maxLength="10",minLength="6")
	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	@RequiredStringValidator(key="realname.error")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@RequiredStringValidator(key="idnumber.error")
	public String getIdnumber() {
		return idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	@RequiredStringValidator(key="telphone.error")
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

}
