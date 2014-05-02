package com.taiko.score;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiko.database.TableResultOperator;
import com.taiko.model.Score;
import com.taiko.utility.Message;

/**
 * Servlet implementation class ScoreServlet
 */
@WebServlet("/ScoreServlet")
public class ScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoreServlet() {
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
		int myscore = Integer.parseInt(request.getParameter("score"));

		TableResultOperator resultOp = new TableResultOperator();
		
		resultOp.connectDB();		
		resultOp.uptateValue(myid, "score", myscore);
		int guestScore =resultOp.selectValue(guestid, "score");
		Score score = new Score(guestScore);
		msg.addInfo(score);
			
		resultOp.disconnectDB();
		
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
