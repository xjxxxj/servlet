package com.xjx.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.xjx.dao.CustomerDao;
import com.xjx.pojo.Customer;
import com.xjx.pojo.CustomerCondition;
import com.xjx.pojo.Page;
import com.xjx.util.DaoUtil;

public class CustomerDaoImpl implements CustomerDao {
	private QueryRunner queryRunner = new QueryRunner() ;
	@Override
	public void insert(Customer customer) {
		String sql = 
		"insert into customers(name , gender , birthday ,email , phone) values(?,?,?,?,?)";
		Connection connection = DaoUtil.getConnection() ;
		try {
			queryRunner.update(connection, sql, 
			customer.getName(),customer.getGender(),customer.getBirthday(),customer.getEmail(),customer.getPhone());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DaoUtil.close(connection);
		}
	}

	@Override
	public void deleteById(Integer id) {
		String sql = 
				"delete from customers where id=?";
				Connection connection = DaoUtil.getConnection() ;
				try {
					queryRunner.update(connection, sql, id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					DaoUtil.close(connection);
				}
	}

	@Override
	public Customer findById(Integer id) {
		String sql = 
				"select id , name , gender , birthday , email , phone from customers where id=?";
				Connection connection = DaoUtil.getConnection() ;
				try {
					return queryRunner.query(connection, sql, new BeanHandler<>(Customer.class), id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					DaoUtil.close(connection);
				}
		return null;
	}

	@Override
	public void update(Customer customer) {
		String sql = 
				"update customers set name=?,gender=?,birthday=?,email=?,phone=? where id=?";
				Connection connection = DaoUtil.getConnection() ;
				try {
					queryRunner.update
					(connection, sql, customer.getName(),customer.getGender(),customer.getBirthday(),customer.getEmail(),customer.getPhone(),customer.getId());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					DaoUtil.close(connection);
				}
	}
	@Override
	public void deleteList(List<Integer> idList) {
		String sql = 
				"delete from customers where id=?";
		Integer[][] idArry = new Integer[idList.size()][1] ;
		for(int i = 0 ; i < idList.size() ; i ++) {
			idArry[i][0] = idList.get(i) ;
		}
				Connection connection = DaoUtil.getConnection() ;
				try {
					queryRunner.batch(connection, sql, idArry) ;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					DaoUtil.close(connection);
				}
	}

	@Override
	public List<Customer> findListCustomer(CustomerCondition condition, Page page) {
		List<Object> arrs = new ArrayList<>() ;
		Integer pageNum = page.getPageNum() ;
		Integer itemCount = page.getItemCount() ;
		StringBuffer buffer = new StringBuffer("select id , name , gender , birthday , email , phone from customers where 1=1") ;
				Connection connection = DaoUtil.getConnection() ;
				if(condition.getName() != null && !"".equals(condition.getName())) {
					buffer.append(" and name like ?") ;
					arrs.add("%" + condition.getName() + "%") ;
				}
				if(condition.getGender() != null && !"".equals(condition.getGender())) {
					buffer.append(" and gender=?") ;
					arrs.add(condition.getGender()) ;
				}
				if(condition.getMinBirthday() != null) {
					buffer.append(" and birthday >= ?") ;
					arrs.add(condition.getMinBirthday()) ;
				}
				if(condition.getMaxBirthday() != null) {
					buffer.append(" and birthday <= ?") ;
					arrs.add(condition.getMaxBirthday()) ;
				}
				//查询满足该条件的总条数
				String sql2 = "select count(*) from (" + buffer.toString() + ") c_condition" ;
				Integer allItemCount = null;
				try {
					allItemCount = ((Long)queryRunner.query(connection, sql2 , new ScalarHandler<>(),arrs.toArray())).intValue();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//添加分页
				buffer.append(" limit ?,?") ;
				arrs.add((pageNum-1)*itemCount) ;
				arrs.add(itemCount) ;
				try {
					page.setAllItemCount(allItemCount);
					page.setAllPageCount((allItemCount-1)/page.getItemCount() + 1);
					List<Customer> customers =  queryRunner.query(connection, buffer.toString(), new BeanListHandler<>(Customer.class),arrs.toArray());
					return customers ;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					DaoUtil.close(connection);
				}
				return null;
	}

}
