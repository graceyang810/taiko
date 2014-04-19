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

import com.taiko.database.TableWaitingRoomOperator;
import com.taiko.utility.HMXHMZ;
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
		
		HMXHMZ hmxhmz = new HMXHMZ();

		TableWaitingRoomOperator wroomOp = new TableWaitingRoomOperator();
		wroomOp.connectDB();
		//查询所有等待房间
		int countRoom = wroomOp.countWaitingRoom();
		
		int myid = Integer.parseInt(request.getParameter("id"));
		boolean feedback = false;
		try {
			if(countRoom!=0){
				//若表中有人				
				ResultSet rs = wroomOp.selectRoomByHost(myid);
				
				if(rs.next()){//myID已建立room
					int guest = rs.getInt("guest");
					if(guest == 0)//还未有房客加入
						msg.addInfo(feedback);
					else{
						msg.addInfo(hmxhmz.getPlayer(guest));//返回房客信息
						int musicID = rs.getInt("musicID");
						msg = hmxhmz.addMusicInfo(msg,musicID);//返回歌曲信息
						
						//已完成配对，删除等待房间
						wroomOp.deleteRoomByHost(myid);
					}
				}
				else{//myID并没有建立过room，即不在等候中
					//随机选择其中一个房间
					Random random = new Random();
					int selectedRoomOrder = random.nextInt(countRoom);
					rs = wroomOp.selectRoomByOrder(selectedRoomOrder);
					
					int host = rs.getInt("host");//获取房主信息
					wroomOp.updateGuest(host, myid);//加入成为房客
					
					msg.addInfo(hmxhmz.getPlayer(host));//返回房主信息
					int musicID = rs.getInt("musicID");
					msg = hmxhmz.addMusicInfo(msg,musicID);//返回歌曲信息
				}
			}else{//没有正在等待的空房间，创建新的房间
				wroomOp.insertRoom(myid);
				int musicID = hmxhmz.selectMusicRandomly();
				wroomOp.updateMusic(myid, musicID);
				msg.addInfo(feedback);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		wroomOp.disconnectDB();

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
