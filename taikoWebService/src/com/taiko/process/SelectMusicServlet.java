package com.taiko.process;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiko.database.TableShakeApplyOperator;
import com.taiko.database.TableShakeRoomOperator;
import com.taiko.utility.DBOperator;
import com.taiko.utility.Message;

/**
 * Servlet implementation class SelectMusicServlet
 */
@WebServlet("/SelectMusicServlet")
public class SelectMusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectMusicServlet() {
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
		int musicid = Integer.parseInt(request.getParameter("song_id"));

		TableShakeRoomOperator tRoomOp = new TableShakeRoomOperator();
		DBOperator dbOp = new DBOperator();
		
		tRoomOp.connectDB();
		
		//更新选中的歌曲id
		tRoomOp.updateMusic(myid, musicid);
		//返回歌曲详细信息
		msg = dbOp.addMusicInfo(msg, musicid);	
			
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
