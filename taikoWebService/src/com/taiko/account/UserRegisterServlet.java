package com.taiko.account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiko.database.TableUserOperator;
import com.taiko.model.Player;
import com.taiko.utility.Message;

/**
 * Servlet implementation class userRegisterServlet
 */
@WebServlet("/userRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		Message msg = new Message();

		TableUserOperator userOp = new TableUserOperator();
		userOp.connectDB();

		// UserName,Password,Gender,PhotoURL
		userOp.insertUser(request.getParameter("name"),
				request.getParameter("code"), request.getParameter("sex"),
				request.getParameter("avatar"));
		
		msg.addInfo(userOp.selectUserID(request.getParameter("name")));

		out.write(msg.toJson());
		out.flush();
		out.close();

		userOp.disconnectDB();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
