 package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiko.database.TableUserOperator;

/**
 * Servlet implementation class fisrtServlet
 */
@WebServlet("/fisrtServlet")
public class ServletTestDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTestDB() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.setContentType("text");
		PrintWriter out = response.getWriter();
		
		TableUserOperator userOp = new TableUserOperator();
		userOp.connectDB();
		
	//	userOp.deleteUser(3);
		
		userOp.insertUser("servletDBTest", "xixixixi","male","photoURL");
//		int id = userOp.selectUserID("servletDBTest");
//		String pswd= userOp.selectUserPassword(id);
		
//		userOp.disconnectDB();
//		out.write(pswd);
		out.flush();
		out.close();		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
