package com.increff.training.employee_app;

import java.sql.SQLException;
import java.util.List;

//import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;


public class EmployeeHibernateApi {
	
	private static final Logger  logger = Logger.getLogger(EmployeeHibernateApi.class);
	private HibernateUtil hbu;
	  
	public EmployeeHibernateApi() throws Exception {
		hbu = new HibernateUtil();
		
	}
	
	public void insert(EmployeePojo p) throws Exception  {
		logger.warn("selecting employees");
		hbu.createSession();
		hbu.beginTransaction();
		Session s = hbu.getSession();
		s.save(p);
		hbu.commitTransaction();
		hbu.closeSession();
		
	}

	public EmployeePojo select(int id) throws SQLException {
		logger.warn("selecting an employee");
		hbu.createSession();
		hbu.beginTransaction();
		Session s = hbu.getSession();
		EmployeePojo p= s.find(EmployeePojo.class, id);
		hbu.commitTransaction();
		hbu.closeSession();
		return p;
	}
	
	public List<EmployeePojo> selectAll() throws Exception {
		logger.warn("selecting all employees");
		hbu.createSession();
		hbu.beginTransaction();
		Session s = hbu.getSession();
		Query q=s.createQuery("select o from EmployeePojo o");
		List<EmployeePojo> list=q.getResultList();
		
		hbu.commitTransaction();
		hbu.closeSession();
		return list;
	}
	
	public void update(int id, EmployeePojo p) throws Exception
	{
			EmployeePojo pr=select(id);
			delete(id);
			insert(p);
	}
	
	
	public void delete(int id) throws Exception {
		logger.warn("deleting an employee");
		hbu.createSession();
		hbu.beginTransaction();
		Session s = hbu.getSession();
		EmployeePojo p= s.find(EmployeePojo.class,id);
		s.delete(p);
		hbu.commitTransaction();
		hbu.closeSession();
		
	}
	
	public void deleteAll() throws SQLException {
		logger.warn("deleting all");
		hbu.createSession();	 
		hbu.beginTransaction();
		Session s = hbu.getSession();
		Query q=s.createQuery("select o from EmployeePojo o");
		List<EmployeePojo> list=q.getResultList();
		for(EmployeePojo p:list)
			s.delete(p);
		hbu.commitTransaction();
		hbu.closeSession();
		
	}
	
	public void printAll() throws SQLException{
		hbu.createSession();
		hbu.beginTransaction();
		Session s = hbu.getSession();
		Query q=s.createQuery("select o from EmployeePojo o");
		List<EmployeePojo> list=q.getResultList();
		for(EmployeePojo p:list)
			logger.warn("employee id "+ p.getId()+" employee age "+p.getAge());
	}
	
}
