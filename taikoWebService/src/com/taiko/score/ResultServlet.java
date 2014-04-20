package com.taiko.score;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiko.database.TableResultOperator;
import com.taiko.model.Result;
import com.taiko.utility.DBOperator;
import com.taiko.utility.Message;

/**
 * Servlet implementation class ResultServlet
 */
@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Message msg = new Message();
		
		int myid = Integer.parseInt(request.getParameter("id"));
		int guestid = Integer.parseInt(request.getParameter("anotherid"));
		int score = Integer.parseInt(request.getParameter("score"));
		int perfect = Integer.parseInt(request.getParameter("perfect"));
		int cool = Integer.parseInt(request.getParameter("cool"));
		int miss = Integer.parseInt(request.getParameter("miss"));
		int combo = Integer.parseInt(request.getParameter("combo"));

		TableResultOperator resultOp = new TableResultOperator();
		DBOperator dbOp = new DBOperator();
		
		resultOp.connectDB();		
		resultOp.updateResult(myid, score, perfect, cool, miss, combo);
		resultOp.disconnectDB();
		
		try {
			Thread.sleep(1000);//防止对方的数据还未写入数据库
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Result opponent = dbOp.getResult(guestid);
		msg.addInfo(opponent);
				
		out.write(msg.toJson());
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
