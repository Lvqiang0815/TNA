package com.oracle.tna.admin.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.oracle.tna.domain.Item;
import com.oracle.tna.exception.ItemException;
import com.oracle.tna.service.ItemService;

@Namespace("/admin")
@ParentPackage(value="struts-default")
@Action("modifyItemAction")
@Results({
		@Result(name="success",location="/admin/ItemList.jsp"),
		@Result(name="input",location="/admin/ModifyItem.jsp")
})
public class ModifyItemAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	@Resource
	ItemService itemSvc;

	private String question;
	private String a;
	private String b;
	private String c;
	private String d;
	private char answer;

	public ModifyItemAction() {
		super();
	}
	
	
	@Override
	public String execute() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		int qid = Integer.parseInt(request.getParameter("qid").trim());
		System.err.println(">>>>>qid="+qid);
		try {
			itemSvc.modifyItemById(new Item(qid, question, a, b, c, d, answer));
		} catch (ItemException e) {
			this.addActionError(this.getText("修改不成功", new String[]{e.getMessage()}));
			return INPUT;
		}
		
		return SUCCESS;
	}
	

	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	@RequiredFieldValidator(key="requiredanswer.error",shortCircuit=true)
	@FieldExpressionValidator(key="answer.error",fieldName="answer",expression="answer=='A'||answer=='B'||answer=='C'||answer=='C'")
	public char getAnswer() {
		return answer;
	}

	public void setAnswer(char answer) {
		this.answer = answer;
	}

}
