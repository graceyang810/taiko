package com.taiko.process;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiko.database.TableShakeApplyOperator;
import com.taiko.database.TableShakeRoomOperator;
import com.taiko.model.Feedback;
import com.taiko.model.Player;
import com.taiko.utility.DBOperator;
import com.taiko.utility.Message;

/**
 * Servlet implementation class ShakeServlet
 */
@WebServlet("/ShakeServlet")
public class ShakeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShakeServlet() {
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

		DBOperator dbOp = new DBOperator();
		TableShakeApplyOperator sApplyOp = new TableShakeApplyOperator();
		sApplyOp.connectDB();

		int myid = Integer.parseInt(request.getParameter("id"));
		Feedback feedback = new Feedback(false);

		Timestamp time = new Timestamp(System.currentTimeMillis());
		/*
		 * Date date = new Date();
		 * Timestamp nousedate = new Timestamp(date.getTime());
		 */

		if (sApplyOp.checkApply(myid))
			sApplyOp.updateTime(myid, time);
		else
			sApplyOp.insertApply(myid);
		
		try {
			Thread.sleep(3000);//睡眠3秒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ResultSet rs = sApplyOp.selectApplyList(myid);
		ArrayList<Player> pList = new ArrayList<Player>();
		
		try{
			while(rs.next())
			{//逐个加入player信息
				pList.add(dbOp.getPlayer(rs.getInt("id")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		msg.addInfo(pList);
		
		//检查时候被别的玩家邀请
		TableShakeRoomOperator sRoomOp = new TableShakeRoomOperator();
		sRoomOp.connectDB();
		
		if(sRoomOp.checkGuest(myid)){
			int hostid = sRoomOp.selectHost(myid);
			Player pHost = dbOp.getPlayer(hostid);
			feedback.setFeedback(true);
			msg.addInfo(pHost);
		}
		
		msg.addInfo(feedback);		
		sRoomOp.disconnectDB();

		sApplyOp.disconnectDB();
		out.write(msg.toJson());
		out.flush();
		out.close();
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
