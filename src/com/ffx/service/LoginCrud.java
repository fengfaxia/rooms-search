package com.ffx.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ffx.domain.Room;
import com.ffx.utils.SqlHelper;

public class LoginCrud extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		String bookid = request.getParameter("bookid");
		SqlHelper sqlHelper =new SqlHelper();
		Room al = sqlHelper.executeQuery(bookid);
		ServletContext application = request.getSession().getServletContext();  
		application.setAttribute("al", al);
		request.getRequestDispatcher("GetMesssage").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		
	}

}
