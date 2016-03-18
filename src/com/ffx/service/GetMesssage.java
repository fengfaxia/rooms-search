package com.ffx.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ffx.domain.Messsage;
import com.ffx.domain.Room;
import com.ffx.utils.SqlHelper;

public class GetMesssage extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		ServletContext application = request.getSession().getServletContext();  
		Room al = (Room) application.getAttribute("al");
		String roomnumber = al.getRoomnumber();
		SqlHelper sqlHelper = new SqlHelper();
		int pageCount = sqlHelper.pageCount(roomnumber);
		int pageNow = 1;
		String strPageNow = request.getParameter("pageNow");
		if(strPageNow != null) {
			try {
			pageNow = Integer.parseInt(strPageNow);
		}catch(NumberFormatException e) {}
		}
		ArrayList<Messsage> messsage = sqlHelper.eQueryMesssage(roomnumber,pageNow);
		request.setAttribute("messsage", messsage);
		request.setAttribute("pageNow", pageNow);
		request.setAttribute("pageCount", pageCount);
		request.getRequestDispatcher("WEB-INF/ShowRoom.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		doGet(request,response);

	}

}
