package com.taiko.score;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiko.database.TableResultOperator;
import com.taiko.database.TableUserOperator;
import com.taiko.model.Feedback;
import com.taiko.model.Result;
import com.taiko.utility.DBOperator;
import com.taiko.utility.LevelExp;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Message msg = new Message();

		int myid = Integer.parseInt(request.getParameter("id"));
		int oppoID = Integer.parseInt(request.getParameter("anotherid"));
		int score = Integer.parseInt(request.getParameter("score"));
		int perfect = Integer.parseInt(request.getParameter("perfect"));
		int cool = Integer.parseInt(request.getParameter("cool"));
		int miss = Integer.parseInt(request.getParameter("miss"));
		int combo = Integer.parseInt(request.getParameter("combo"));

		TableResultOperator resultOp = new TableResultOperator();
		DBOperator dbOp = new DBOperator();

		resultOp.connectDB();
		resultOp.updateResult(myid, score, perfect, cool, miss, combo, 1);
		int status = resultOp.selectValue(oppoID, "status");
//		resultOp.disconnectDB();

		// resultOp.updateStatus(myid, 1);
		// try {
		// Thread.sleep(1000);//防止对方的数据还未写入数据库
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		Feedback feedback = new Feedback(false);

		if (status == 1) {
			feedback.setFeedback(true);
			msg.addInfo(feedback);
			Result opponent = dbOp.getResult(oppoID);
			msg.addInfo(opponent);
			
//			resultOp.connectDB();
			int oppoScore = resultOp.selectValue(oppoID, "score");
			int result = LevelExp.init;
			if(score>oppoScore)
				result = LevelExp.win;
			else if(score < oppoScore)
				result = LevelExp.loose;
			else if(score == oppoScore)
				result = LevelExp.dogfall;
			
			TableUserOperator userOp = new TableUserOperator();
			userOp.connectDB();
			int myExp = userOp.selectUserExp(myid);
			int myLevel = userOp.selectUserLevel(myid);
			
			LevelExp le = new LevelExp(myLevel,myExp);
			int newLevel = le.transfer(result);
			int newExp = le.getExp();
			/*
			 * 发送给客户端
			 */
			userOp.updateUserExp(myid, newExp);
			userOp.updateUserLevel(myid, newLevel);
			userOp.disconnectDB();
			
			resultOp.deleteResult(oppoID);// 删除记录
//			resultOp.disconnectDB();			
			
		}else
			msg.addInfo(feedback);
		
		resultOp.disconnectDB();
		
		out.write(msg.toJson());
		out.flush();
		out.close();
//		try {
//			Thread.sleep(2000);// 防止对方还未的数据
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
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
