package com.xjx.service;

import java.util.List;

import com.xjx.pojo.Customer;
import com.xjx.pojo.CustomerCondition;
import com.xjx.pojo.Page;

public interface CustomerService {

	/**
	 * 添加客户
	 * @param customer
	 */
	public void insertCustomer(Customer customer) ;
	/**
	 * 根据id删除客户
	 * @param id
	 */
	public void deleteCustomerById(Integer id) ;
	/**
	 * 根据id查找客户
	 * @param id
	 * @return
	 */
	public Customer findCustomerById(Integer id) ;
	/**
	 * 修改用户信息
	 * @param customer
	 */
	public void updateCustomer(Customer customer) ;
	/**
	 * 删除多个customer
	 * @param idList
	 */
	public void deleteCustomerList(List<Integer> idList);
	/*
	 * 条件+分页查询
	 */
	public List<Customer> findListCustomer(CustomerCondition condition, Page page);
	
}
