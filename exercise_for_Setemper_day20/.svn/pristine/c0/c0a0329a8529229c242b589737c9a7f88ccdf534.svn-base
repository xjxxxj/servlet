package com.xjx.dao;

import java.util.List;

import com.xjx.pojo.Customer;
import com.xjx.pojo.CustomerCondition;
import com.xjx.pojo.Page;

public interface CustomerDao {
	/**
	 * 添加
	 * @param customer
	 */
	public void insert(Customer customer) ;
	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Integer id) ;
	/**
	 * 查询一个
	 * @param id
	 * @return
	 */
	public Customer findById(Integer id) ;
	/**
	 * 修改
	 * @param customer
	 */
	public void update(Customer customer) ;
	/**
	 * 根据id集合删除多个数据
	 * @param idList
	 */
	public void deleteList(List<Integer> idList);
	/**
	 * 条件查询结果进行分页
	 * @param condition
	 * @param page
	 * @return
	 */
	public List<Customer> findListCustomer(CustomerCondition condition, Page page);
}
