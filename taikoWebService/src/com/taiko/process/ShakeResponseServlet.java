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

import com.taiko.database.TableShakeApplyOperator;
import com.taiko.database.TableShakeRoomOperator;
import com.taiko.model.Music;
import com.taiko.utility.DBOperator;
import com.taiko.utility.Message;

/**
 * Servlet implementation class ShakeResponseServlet
 */
@WebServlet("/ShakeResponseServlet")
public class ShakeResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShakeResponseServlet() {
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
		int resp = Integer.parseInt(request.getParameter("feedback"));
		int hostid = Integer.parseInt(request.getParameter("anotherid"));		

		TableShakeRoomOperator tRoomOp = new TableShakeRoomOperator();
		DBOperator dbOp = new DBOperator();
		boolean feedback = false;
		
		//若仍在摇一摇列表中，则删除自己
		TableShakeApplyOperator sApplyOp = new TableShakeApplyOperator();
		sApplyOp.connectDB();
		if (sApplyOp.checkApply(myid))
			sApplyOp.deleteApply(myid);
		sApplyOp.disconnectDB();
		
		tRoomOp.connectDB();
		
		//更新同意或拒绝信息
		tRoomOp.updateFeedback(hostid, resp);
		
		//检查是否获得选歌结果
		int musicid = tRoomOp.selectMusic(hostid);
		if(musicid != 0){//已经有选定的歌曲
			feedback = true;
			msg.addInfo(feedback);
			msg = dbOp.addMusicInfo(msg, musicid);		
		}else
			msg.addInfo(feedback);
			
		tRoomOp.disconnectDB();
		
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
