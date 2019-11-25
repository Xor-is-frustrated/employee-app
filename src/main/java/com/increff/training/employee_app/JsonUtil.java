package com.increff.training.employee_app;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {
	public static String writeJson(EmployeePojo p) throws JsonGenerationException, JsonMappingException, IOException
	{
//		PrintWriter out = resp.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(p);
	}
	
	public static String writeJson(List<EmployeePojo> p) throws JsonGenerationException, JsonMappingException, IOException
	{
//		PrintWriter out = resp.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(p);
	}
	
	public static EmployeePojo readJson(HttpServletRequest req) throws JsonGenerationException, JsonMappingException, IOException
	{
//		PrintWriter out = resp.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(req.getInputStream(), EmployeePojo.class); 	
//		return mapper.writeValueAsString(p);
	}
}
