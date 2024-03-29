package com.taiko.process;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiko.database.TableResultOperator;
import com.taiko.database.TableWaitingRoomOperator;
import com.taiko.model.Feedback;
import com.taiko.utility.DBOperator;
import com.taiko.utility.Message;

/**
 * Servlet implementation class quickStart
 */
@WebServlet("/quickStart")
public class QuickStartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuickStartServlet() {
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

		TableWaitingRoomOperator wRoomOp = new TableWaitingRoomOperator();
		wRoomOp.connectDB();
		// 查询所有等待房间
		int countRoom = wRoomOp.countWaitingRoom();

		int myid = Integer.parseInt(request.getParameter("id"));
		Feedback feedback = new Feedback(false);
		// out.write(countRoom+";");
		try {
			if (countRoom != 0) {
				// 若表中有人
				ResultSet rs = wRoomOp.selectRoomByHost(myid);

				if (rs.next()) {// myID已建立room
					int guest = rs.getInt("guest");
					if (guest == 0)// 还未有房客加入
						msg.addInfo(feedback);
					else {
						feedback.setFeedback(true);
						msg.addInfo(feedback);
						msg.addInfo(dbOp.getPlayer(guest));// 返回房客信息
						int musicID = rs.getInt("musicID");
						msg = dbOp.addMusicInfo(msg, musicID);// 返回歌曲信息

						// 已完成配对，删除等待房间
						wRoomOp.deleteRoomByHost(myid);
						// 为两个玩家创建result记录
						TableResultOperator resultOp = new TableResultOperator();
						resultOp.connectDB();
						resultOp.insertResult(myid);
						resultOp.insertResult(guest);
						resultOp.disconnectDB();
					}
				} else {// myID并没有建立过room，即不在等候中
						// 随机选择其中一个房间
					Random random = new Random();
					int selectedRoomOrder = random.nextInt(countRoom) + 1;
					rs = wRoomOp.selectRoomByOrder(selectedRoomOrder);

					int host = rs.getInt("host");// 获取房主信息
					wRoomOp.updateGuest(host, myid);// 加入成为房客

					feedback.setFeedback(true);
					msg.addInfo(feedback);
					msg.addInfo(dbOp.getPlayer(host));// 返回房主信息
					int musicID = rs.getInt("musicID");
					msg = dbOp.addMusicInfo(msg, musicID);// 返回指定歌曲信息
				}
			} else {// 没有正在等待的空房间，创建新的房间
				wRoomOp.insertRoom(myid);
				int musicID = dbOp.selectMusicRandomly();
				wRoomOp.updateMusic(myid, musicID);
				msg.addInfo(feedback);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		wRoomOp.disconnectDB();

		out.write(msg.toJson());
		out.flush();
		out.close();

		if (!feedback.isFeedback()) {// 5秒后若还未有房客加入则删除记录
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			wRoomOp.connectDB();
			ResultSet rs = wRoomOp.selectRoomByHost(myid);
			try {
				if (rs.next()) {// myID已建立room
					int guest = 0;
					guest = rs.getInt("guest");
					if (guest == 0) {// 还未有房客加入
						//删除等待房间
						wRoomOp.deleteRoomByHost(myid);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			wRoomOp.disconnectDB();
		}
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
