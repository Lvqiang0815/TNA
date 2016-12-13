package com.oracle.tna.user.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.oracle.tna.domain.Item;
import com.oracle.tna.domain.Score;
import com.oracle.tna.domain.User;
import com.oracle.tna.service.ExamService;

@Namespace("/user")
@ParentPackage(value="struts-default")
@Action("examAction")
@Results({
	@Result(name="success",location="/user/ShowScore.jsp"),
	@Result(name="input",location="/user/Exam.jsp")
})
public class ExamAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	@Resource
	ExamService examSvc;
	
	// 从Exam.jsp中获取的Item集合
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();
	@SuppressWarnings("unchecked")
	List<Item> items = (List<Item>) session.getAttribute("items");
	
	User user = (User)session.getAttribute("user");
	
	List<String> userAnswers;// 保存用户的答案
	
	/**
	 *从Exam.jsp中获取radion按钮的name，即获取用户选择的答案
	 */	
	public ExamAction(){
		userAnswers = new ArrayList<String>();
		for (int i = 0; i < items.size(); i++) {
			userAnswers.add(request.getParameter(items.get(i).getQid()+"")+"");
		}
		request.setAttribute("userAnswers", userAnswers);
		
		System.out.println("答案个数：" + userAnswers.size());
		System.out.println("888888888888888888");
		
	}
	
	@Override
	public String execute() throws Exception {
		
		for (int i = 0; i < userAnswers.size(); i++) {
			System.out.println("ooooooooo"+userAnswers.get(i));
		}
		Score score = null;
		try {
			score = examSvc.countScore(items,userAnswers,user);
			System.out.println("---------------");
		} catch(NullPointerException e) {
			return INPUT;
		}	
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("score", score);
		
		//return INPUT;
		/*for (Item item : items) {
			System.out.println(item.getQid());
		}*/
		System.out.println(items);
		System.out.println(userAnswers);
		for (Item item : items) {
			System.out.println("ZZZZ:" + item.getAnswer());
		}
		return SUCCESS;
	}
	

	@RequiredFieldValidator(key="user.answers.error")
	public List<String> getUserAnswers() {
		return userAnswers;
	}

	public void setUserAnswers(List<String> userAnswers) {
		this.userAnswers = userAnswers;
		for (int i = 0; i < userAnswers.size(); i++) {
			System.out.println(userAnswers.get(i));
		}
	}

}
