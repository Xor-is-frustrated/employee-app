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

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


@WebServlet(name = "api ", description = "api servlet", urlPatterns = { "/api" })

public class ApiServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		resp.setContentType("application/json");
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
				
				String json=JsonUtil.writeJson(list);
				out.write(json);
				
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
		String json=JsonUtil.writeJson(p);
		out.write(json);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doPost(req, resp);
		

		EmployeePojo p = JsonUtil.readJson(req);

		try {
			api.insert(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doPut(req, resp);
		 

		EmployeePojo p = JsonUtil.readJson(req);
		

		try {
			api.update(p.getId(), p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doDelete(req, resp);
		int id = Integer.valueOf(req.getParameter("id"));

		try {
			api.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	

}
