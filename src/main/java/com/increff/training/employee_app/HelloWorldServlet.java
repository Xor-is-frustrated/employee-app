package com.increff.training.employee_app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.hibernate.mapping.List;


public class HelloWorldServlet extends HttpServlet {

	/**
	 * 
	 */

	private static final long serialVersionUID = 7368913047807338038L;
	private static EmployeeHibernateApi api;

	static {
		HibernateUtil.configure();
		try {
			api = new EmployeeHibernateApi();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doGet(req, resp);
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
//		out.println("<p>hello work</p>");
		
		String action=req.getParameter("action");
		String idparam=req.getParameter("id");
		
		if(idparam==null)
		{
			List<EmployeePojo> list = null;
				try {
					list=api.selectAll();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				for(EmployeePojo p:list)
				{
						print(out,p);
				}
				
				return ;
		}
		
		int id = Integer.valueOf(req.getParameter("id"));
		
		
		if(action!=null && action.contentEquals("action"))
		{
				doDelete(req, resp);
				return ;
		}
		
		EmployeePojo p = null;
		try {
			p = api.select(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("<p>name</p>" + p.getName());
		out.println("<p>age</p>" + p.getAge());
		out.println("<p>ID</p>" + p.getId());

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doPost(req, resp);

		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String age = req.getParameter("age");

		EmployeePojo p = new EmployeePojo();
		p.setAge(Integer.valueOf(age));
		p.setId(Integer.valueOf(id));
		p.setName(name);
		try {
			api.insert(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException("error savinf object", e);
		}
		PrintWriter out = resp.getWriter();
		out.println("<p>employee created successfully.</p>");
		
		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doPut(req, resp)
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String age = req.getParameter("age");

		EmployeePojo p = new EmployeePojo();
		p.setAge(Integer.valueOf(age));
		p.setId(Integer.valueOf(id));
		p.setName(name);

		try {
			api.update(p.getId(), p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.valueOf(req.getParameter("id"));

		try {
			api.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter out = resp.getWriter();
		out.println("<p>employee deleted.</p>");
	}
	
	private void print(PrintWriter out,EmployeePojo p)
	{
//		PrintWriter out = resp.getWriter();
//		out.println("<p>name</p>" + p.getName());
//		out.println("<p>age</p>" + p.getAge());
//		out.println("<p>ID</p>" + p.getId());
		
	}

}
