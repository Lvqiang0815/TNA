package com.oracle.tna.admin.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;
import com.oracle.tna.domain.Item;
import com.oracle.tna.service.ItemService;

@Namespace("/admin")
@ParentPackage(value="struts-default")
@Action("dispItemAction")
@Result(name="success",location="/admin/ModifyItem.jsp")
public class DispItemAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	@Resource
	ItemService itemSvc;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		int qid = Integer.parseInt(request.getParameter("qid"));
		Item item = itemSvc.findItemById(qid);
		request.setAttribute("item", item);
		return SUCCESS;
	}

}
