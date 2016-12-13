package com.oracle.tna.admin.action;

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
import com.oracle.tna.domain.Admin;
import com.oracle.tna.exception.AdminException;
import com.oracle.tna.service.AdminService;

@Namespace("/admin")
@ParentPackage(value="struts-default")
@Action("adminLoginAction")
@Controller
@Scope("prototype")
@Results({
	@Result(name="success",location="/admin/AdminIndex.jsp"),
	@Result(name="input",location="/admin/AdminLogin.jsp")
})
public class AdminLoginAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String validatecode;
	
	@Resource
	AdminService adminSvc;

	public AdminLoginAction() {
	}
	
	@Override
	public String execute() {
		System.out.println("username>>>>>>>>>>>>>>>>>>>>>>>>>:::4::" );
		System.out.println(password);
		System.out.println(validatecode);
		Admin admin = null;
		try {
			admin = adminSvc.findAdmin(username, password);
		} catch (AdminException e) {
			this.addActionError(this.getText("admin.login.error",new String[]{e.getMessage()}));
			return INPUT;
		}
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("admin", admin);
		
		return SUCCESS;
	}

	@RequiredStringValidator(key="username.error")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
		System.out.println(">>>>>>>>>>>>>>>.........1" + username);
	}

	@RequiredStringValidator(key="password.error",trim=true,shortCircuit=true)
	@StringLengthFieldValidator(key="passwordlength.error",maxLength="10",minLength="4")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		System.out.println(">>>>>>>>>>>>>>>>.........22222222222" + password);
	}

	@RequiredStringValidator(key="validate.error")
	public String getValidatecode() {
		return validatecode;
	}

	public void setValidatecode(String validatecode) {
		this.validatecode = validatecode;
		System.out.println("adminloginaction>>>>>>>>>>>>>>>3" + validatecode);
	}

}
