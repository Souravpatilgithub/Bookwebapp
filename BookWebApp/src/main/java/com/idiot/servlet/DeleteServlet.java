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

@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet {
	private static final String query="DELETE from BOOKDATA where id=?";
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get printWriter();
		PrintWriter pw=res.getWriter();
		//set content type
		res.setContentType("text/html");
		// get the id of record
		int id=Integer.parseInt(req.getParameter("id"));
		
		//LODA jdbc driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException cnf){
            cnf.printStackTrace();			
		}
		//generate the connection
	    try (Connection con = DriverManager.getConnection("jdbc:mysql:///book","root","Sourav@17");
	    		PreparedStatement ps = con.prepareStatement(query);){
	     ps.setInt(1, id);
	      int count=ps.executeUpdate();
	      if(count==1) {
	    	  pw.println("<h2>Record is Deleted Successfully</h2>");
	      }else {
	    	  pw.println("<h2>Record is not Deleted Successfully</h2>");
	      }
	    }catch(SQLException se){
	    	se.printStackTrace();
	    	pw.println("<h1>"+se.getMessage()+"</h2>");
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	pw.println("<h1>"+e.getMessage()+"</h2>");
	    }
	    pw.println("<a href='home.html'>Home</a>");
	    pw.println("<br>");
	    pw.println("<a href='booklist'>Book List</a>");
	}
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	doGet(req,res);
    }
}
