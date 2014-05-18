package com.taiko.process;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiko.database.TableMusicOperator;
import com.taiko.database.TableShakeApplyOperator;
import com.taiko.database.TableShakeRoomOperator;
import com.taiko.database.TableUserOperator;
import com.taiko.model.Feedback;
import com.taiko.model.Music;
import com.taiko.model.Response;
import com.taiko.utility.DBOperator;
import com.taiko.utility.Message;

/**
 * Servlet implementation class ShakeInviteServlet
 */
@WebServlet("/ShakeInviteServlet")
public class ShakeInviteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShakeInviteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		Message msg = new Message();

		TableShakeRoomOperator sRoomOp = new TableShakeRoomOperator();
		DBOperator dbOp = new DBOperator();
		
		int myid = Integer.parseInt(request.getParameter("id"));
		int guestid = Integer.parseInt(request.getParameter("anotherid"));
		Feedback feedback = new Feedback(false);
		
		//若扔在摇一摇列表中，则删除自己
		TableShakeApplyOperator sApplyOp = new TableShakeApplyOperator();
		sApplyOp.connectDB();
		if (sApplyOp.checkApply(myid))
			sApplyOp.deleteApply(myid);
		sApplyOp.disconnectDB();
		
		sRoomOp.connectDB();
		
		//检查是否创建过房间
		if (sRoomOp.checkHost(myid))
			sRoomOp.updateGuest(myid, guestid);
		else{
			sRoomOp.insertRoom(myid);
			sRoomOp.updateGuest(myid, guestid);
		}
		//检查是否获得回应
		Response resp = new Response(sRoomOp.selectFeedback(myid));
		if(resp.getResp() == 1){
			feedback.setFeedback(true);
			msg.addInfo(feedback);
			msg.addInfo(resp);
			
			//加可选音乐列表			
//			ResultSet rs = dbOp.getMusicList(myid);
			
			TableUserOperator userOp = new TableUserOperator();
			userOp.connectDB();
			int level = userOp.selectUserLevel(myid);
//			System.out.println("level = "+level);
			userOp.disconnectDB();
			TableMusicOperator musicOp = new TableMusicOperator();
			musicOp.connectDB();
			ResultSet rs = musicOp.selectMusicList(level);
			
			
			ArrayList<Music> mList = new ArrayList<Music>();
			
			int count = 0;
			try{
				while(rs.next())
				{//逐个加入music信息
					count++;
					mList.add(dbOp.getMusic(rs.getInt("id")));
				}
			}catch (SQLException e) {
				System.out.println("mList 出错");
				e.printStackTrace();
			}
			msg.addInfo(mList);
			System.out.println("qualified music count = "+count);
			musicOp.disconnectDB();
//			sRoomOp.deleteRoomByHost(myid); //guest获取歌曲信息后删除
		}else if(resp.getResp() == 0){
			feedback.setFeedback(true);
			msg.addInfo(feedback);
			msg.addInfo(resp);
			sRoomOp.deleteRoomByHost(myid);
		}else
			msg.addInfo(feedback);
		
		
		sRoomOp.disconnectDB();
		
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
