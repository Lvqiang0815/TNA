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
@Action("userLoginAction")
@Controller
@Scope("singleton")
@Results({
	@Result(name="success",location="/user/Index.jsp"),
	@Result(name="input",location="/Login.jsp")
})
public class UserLoginAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String validatecode;
	
	@Resource
	UserService userSvc;

	public UserLoginAction() {
		super();
		System.out.println("username=" + username);
		System.out.println(password);
		System.out.println(validatecode);
	}
	
	@Override
	public String execute() {
		
		User user = null;
		try {
			user = userSvc.findByUs(username, password);
		} catch (UserException e) {
			this.addActionError(this.getText("user.login.error",new String[]{e.getMessage()}));
			return INPUT;
		}
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
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

	@RequiredStringValidator(key="validate.error")
	public String getValidatecode() {
		return validatecode;
	}

	public void setValidatecode(String validatecode) {
		this.validatecode = validatecode;
	}

}
