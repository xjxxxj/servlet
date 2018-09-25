package com.xjx.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xjx.pojo.Customer;
import com.xjx.pojo.CustomerCondition;
import com.xjx.pojo.Page;
import com.xjx.service.CustomerService;
import com.xjx.service.impl.CustomerServiceImpl;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/customerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CustomerService customerService = new CustomerServiceImpl() ;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
        //使用反射实现请求方法的分配
		try {
			Method declaredMethod = this.getClass().getDeclaredMethod(method, HttpServletRequest.class,HttpServletResponse.class) ;
			declaredMethod.invoke(this, request,response) ;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private void deleteChecked(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		List<Integer> idList = new ArrayList<>() ;
		Enumeration<String> names = request.getParameterNames() ;
		while(names.hasMoreElements()) {
			String key = names.nextElement() ;
			if(key.startsWith("Customer_")) {
				idList.add(Integer.valueOf(request.getParameter(key))) ;
			}
		}
		customerService.deleteCustomerList(idList) ;
		response.sendRedirect(request.getContextPath() + "/customerServlet?method=query");
	}
	@SuppressWarnings("unused")
	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 封装查询条件
		//
		String name = request.getParameter("name") ;
		String gender = request.getParameter("gender") ;
		String minBirthday = request.getParameter("minBirthday") ;
		String maxBirthday = request.getParameter("maxBirthday") ;
		CustomerCondition condition = new CustomerCondition() ;
		condition.setName(name);
		condition.setGender(gender);
		if(!"".equals(minBirthday) && minBirthday != null) {
			try {
				condition.setMinBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(minBirthday));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(!"".equals(maxBirthday) && maxBirthday != null) {
			try {
				condition.setMaxBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(maxBirthday));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//封装分页数据
		String pageNum = request.getParameter("pageNum") ;
		String itemCount = request.getParameter("itemCount") ;
		Page page = new Page() ;
		if(pageNum != null && !"".equals(pageNum)) {
			page.setPageNum(Integer.valueOf(pageNum));
		}
		if(itemCount != null && !"".equals(itemCount)) {
			page.setItemCount(Integer.valueOf(itemCount));
		}
		List<Customer> customers = customerService.findListCustomer(condition,page) ;
		//避免查询结果小于每页显示的条数时，每页查询条数为1
		if(page.getItemCount() > page.getAllItemCount()) {
			page.setItemCount(page.getAllItemCount());
			page.setPageNum(1);
		}
		request.setAttribute("customers", customers);
		request.setAttribute("page" , page);
		request.getRequestDispatcher("/customer/jsp/list.jsp").forward(request, response);
	}

	@SuppressWarnings("unused")
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer id = Integer.valueOf(request.getParameter("id")) ;
		customerService.deleteCustomerById(id);
		response.sendRedirect(request.getContextPath() + "/customerServlet?method=query");
	}

	@SuppressWarnings("unused")
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer id = Integer.valueOf(request.getParameter("id")) ;
		String name = request.getParameter("name") ;
		String gender = request.getParameter("gender") ;
		Date birthday = null ;
		try {
			birthday = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday")) ;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String email = request.getParameter("email") ;
		String phone = request.getParameter("phone") ;
		Customer customer = new Customer() ;
		customer.setId(id);
		customer.setName(name);
		customer.setGender(gender);
		customer.setBirthday(new java.sql.Date(birthday.getTime()));
		customer.setEmail(email);
		customer.setPhone(phone);	
		customerService.updateCustomer(customer);
		response.sendRedirect(request.getContextPath() + "/customerServlet?method=query");
	}

	@SuppressWarnings("unused")
	private void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer id = Integer.valueOf(request.getParameter("id")) ;
		Customer customer = customerService.findCustomerById(id) ;
		request.setAttribute("customer", customer);
		request.getRequestDispatcher("/customer/jsp/edit.jsp").forward(request, response);
	}
	@SuppressWarnings("unused")
	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name") ;
		String gender = request.getParameter("gender") ;
		Date birthday = null ;
		try {
			birthday = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday")) ;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String email = request.getParameter("email") ;
		String phone = request.getParameter("phone") ;
		Customer customer = new Customer() ;
		customer.setName(name);
		customer.setGender(gender);
		customer.setBirthday(birthday);
		customer.setEmail(email);
		customer.setPhone(phone);	
		customerService.insertCustomer(customer);
		response.sendRedirect(request.getContextPath() + "/customerServlet?method=query");
	}

}
