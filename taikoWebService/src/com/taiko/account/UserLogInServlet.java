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
 * Servlet implementation class userLogInServlet
 */
@WebServlet("/userLogInServlet")
public class UserLogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLogInServlet() {
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

		int id = Integer.parseInt(request.getParameter("id"));
		String pswd = userOp.selectUserPassword(id);

		if (request.getParameter("code") == pswd) {
			// id,name,gender,photo,level
			Player p = new Player(id, userOp.selectUserName(id),
					userOp.selectGender(id), userOp.selectUserPhoto(id),
					userOp.selectUserLevel(id));
			msg.addInfo(p);

		} else {
			msg.addInfo("error");
		}

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
