package com.oracle.tna.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.oracle.tna.domain.Item;

@Repository("itemDAO")
@Scope("singleton")
public class ItemDAO {
	
	@Resource
	HibernateTemplate hbTemplate;
	
	/**
	 * 插入题目
	 */
	public void insert(Item item){
		hbTemplate.save(item);
	}
	
	/**
	 * 查询所有题目
	 * return List<Item>
	 */
	@SuppressWarnings("unchecked")
	public List<Item> retriveItem(){
		return (List<Item>)hbTemplate.find("from Item");
	}
	
	/**
	 * 根据qid查询题目
	 */
	@SuppressWarnings("unchecked")
	public Item findItemById(int qid){
		List<Item> items = hbTemplate.find("from Item where qid=?",new Object[]{qid});
		return items.get(0);
	}
	
	/**
	 * 根据qid修改题目
	 */
	public void updateItemById(Item item){
		hbTemplate.update("Item", item);
	}
	
	/**
	 * 根据qid删除指定Item
	 */
	public void deleteItemById(Item item){
		hbTemplate.delete("Item",item);
	}

}
