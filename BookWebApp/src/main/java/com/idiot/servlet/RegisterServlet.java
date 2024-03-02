package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final String query="insert into bookdata(bookname,bookedition,bookprice) values(?,?,?)";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get printWriter();
		PrintWriter pw=res.getWriter();
		//set content type
		res.setContentType("text/html");
		//get the book info
		String bookname = req.getParameter("bookname");
		String bookEdition = req.getParameter("bookEdition");
		float bookprice= Float.parseFloat(req.getParameter("bookPrice"));
		//LODA jdbc driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException cnf){
            cnf.printStackTrace();			
		}
		//generate the connection
	    try (Connection con = DriverManager.getConnection("jdbc:mysql:///book","root","Sourav@17");
	    		PreparedStatement ps = con.prepareStatement(query);){
	    	ps.setString(1, bookname);
	    	ps.setString(2, bookEdition);
	    	ps.setFloat(3, bookprice);
	    	int count=ps.executeUpdate();
	    	if(count==1) {
	    		pw.println("<h2>Record is Registered Sucessfully</h2>");
	    	}else {
	    		pw.println("<h2>Record is not Registered Sucessfully</h2>");
	    	}
	    }catch(SQLException se){
	    	se.printStackTrace();
	    	pw.println("<h1>"+se.getMessage()+"</h2>");
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	pw.println("<h1>"+e.getMessage()+"</h2>");
	    }
	    pw.println("<a href='home.html'>Home List</a>");
	   pw.println("<br>");
	    pw.println("<a href='booklist'>Book List</a>");
	}
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	doGet(req,res);
    }
}
