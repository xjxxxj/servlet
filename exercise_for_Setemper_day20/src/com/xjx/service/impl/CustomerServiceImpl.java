package com.xjx.service.impl;

import java.util.List;

import com.xjx.dao.CustomerDao;
import com.xjx.dao.impl.CustomerDaoImpl;
import com.xjx.pojo.Customer;
import com.xjx.pojo.CustomerCondition;
import com.xjx.pojo.Page;
import com.xjx.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
	private CustomerDao customerDao = new CustomerDaoImpl() ;
	@Override
	public void insertCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerDao.insert(customer);
	}

	@Override
	public void deleteCustomerById(Integer id) {
		// TODO Auto-generated method stub
		customerDao.deleteById(id);
	}

	@Override
	public Customer findCustomerById(Integer id) {
		// TODO Auto-generated method stub
		return customerDao.findById(id);
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerDao.update(customer);
	}

	@Override
	public void deleteCustomerList(List<Integer> idList) {
		// TODO Auto-generated method stub
		customerDao.deleteList(idList) ;
	}

	@Override
	public List<Customer> findListCustomer(CustomerCondition condition, Page page) {
		// TODO Auto-generated method stub
		return customerDao.findListCustomer(condition,page);
	}

}
