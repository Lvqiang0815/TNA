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
import com.oracle.tna.service.ItemService;

@Namespace("/admin")
@ParentPackage(value="struts-default")
@Action("deleteItemAction")
@Results({
		@Result(name="success",location="/admin/ItemList.jsp"),
})
public class DeleteItemAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	@Resource
	ItemService itemSvc;

	public DeleteItemAction() {
		super();
	}
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		int qid = Integer.parseInt(request.getParameter("qid").trim());
		itemSvc.deleteItemById(itemSvc.findItemById(qid));
		return SUCCESS;
	}

}
