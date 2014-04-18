package com.taiko.process;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiko.database.DBController;
import com.taiko.database.TableUserOperator;
import com.taiko.model.Player;
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

		DBController db = new DBController();
		
		db.connect();
		//查询快速开始列表
		String sql = null;
		sql = "select * from quickstartwaitinglist";
		ResultSet rs = null;
		rs = db.selectSQL(sql);
		db.disconnect();
		
		int myid = Integer.parseInt(request.getParameter("id"));
		
		try {
			if(rs.next()){
				//若表中有人
				int id = rs.getInt("ID");
				if(id!=myid){
					TableUserOperator userOp = new TableUserOperator();
					userOp.connectDB();
					//id,name,gender,photo,level
					Player p = new Player(id, userOp.selectUserName(id),
							userOp.selectGender(id), userOp.selectUserPhoto(id),
							userOp.selectUserLevel(id));
					userOp.disconnectDB();
					msg.addInfo(p);
					
					//查询歌曲信息！！！！！！！！HERE！！！！！
					db.connect();
				}
			}else{
				db.connect();
				sql = "insert into quickstartwaitinglist values ("+ myid +");";
				db.insertSQL(sql);
				db.disconnect();
				boolean feedback = false;
				msg.addInfo(feedback);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}

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
