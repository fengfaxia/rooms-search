package com.ffx.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ffx.domain.Messsage;
import com.ffx.domain.Room;
import com.ffx.utils.CharSet;
import com.ffx.utils.SqlHelper;

public class MesssageCrud extends HttpServlet {	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 response.setContentType("text/html");
		 int messsageid = Integer.parseInt(request.getParameter("messsageid"));
		 String dnumber = request.getParameter("dnumber");
		 String roomnumber = request.getParameter("roomnumber");
		 String studentnumber = request.getParameter("studentnumber");
		 String content = request.getParameter("content");
		 Messsage messsage = new Messsage();
		 messsage.setMesssageid(messsageid);
		 messsage.setDnumber(dnumber);
		 messsage.setRoomnumber(roomnumber);
		 messsage.setStudentnumber(CharSet.getString(studentnumber));
		 messsage.setContent(CharSet.getString(content));
		 SqlHelper sqlHelper = new SqlHelper();
		 sqlHelper.executeUpdate(messsage);
		 ServletContext application = request.getSession().getServletContext();  
		 Room al =  (Room)application.getAttribute("al");
		 application.setAttribute("al", al);
		 request.setAttribute("roomnumber", roomnumber);
		 request.getRequestDispatcher("GetMesssage").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		doGet(request,response);
	}

}
