package com.oracle.tna.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.oracle.tna.dao.ItemDAO;
import com.oracle.tna.domain.Item;
import com.oracle.tna.exception.ItemException;

@Service("itemService")
@Scope("singleton")
public class ItemService {
	
	@Resource
	ItemDAO itemDAO;
	
	/**
	 * 插入题目
	 */
	public void insertItem(Item item) throws ItemException {
		itemDAO.insert(item);
	}
	
	/**
	 * 查询所有题目
	 * return List<Item>
	 */
	public List<Item> retriveItem(){
		return (List<Item>)itemDAO.retriveItem();
	}
	
	/**
	 * 根据qid查询题目
	 */
	public Item findItemById(int qid){
		return itemDAO.findItemById(qid);
	}
	
	/**
	 * 根据qid修改题目
	 */
	public void modifyItemById(Item item) throws ItemException {
		itemDAO.updateItemById(item);
	}
	
	/**
	 * 根据qid删除指定Item
	 */
	public void deleteItemById(Item item) throws ItemException {
		itemDAO.deleteItemById(item);
	}
	
}
