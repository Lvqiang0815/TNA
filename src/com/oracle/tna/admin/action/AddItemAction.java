package com.oracle.tna.admin.action;

import javax.annotation.Resource;

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
@Action("addItemAction")
@Results({
		@Result(name="success",location="/admin/ItemList.jsp"),
		@Result(name="input",location="/admin/AddItem.jsp")
})
public class AddItemAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String question;
	private String a;
	private String b;
	private String c;
	private String d;
	private char answer;
	
	@Resource
	ItemService itemSvc;

	public AddItemAction() {
		super();
	}

	
	@Override
	public String execute() throws Exception {
		Item item = new Item(question, a, b, c, d, answer);
		try {
			itemSvc.insertItem(item);
		} catch (ItemException e) {
			this.addActionError(this.getText("添加失败",new String[]{e.getMessage()}));
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
