package com.taiko.utility;

import com.taiko.database.TableMusicOperator;
import com.taiko.database.TableUserOperator;
import com.taiko.model.Music;
import com.taiko.model.Player;

public class HMXHMZ {

	public Player getPlayer(int id){
		TableUserOperator userOp = new TableUserOperator();
		userOp.connectDB();
		//id,name,gender,photo,level
		Player p = new Player(id, userOp.selectUserName(id),
				userOp.selectGender(id), userOp.selectUserPhoto(id),
				userOp.selectUserLevel(id));
		userOp.disconnectDB();
		return p;
	}
	
	public Message addMusicInfo(Message msg, int musicID){
		TableMusicOperator musicOp = new TableMusicOperator();
		musicOp.connectDB();
		String musicURL = musicOp.selectMusicByID(musicID);
		musicOp.disconnectDB();
		Music music = new Music(musicURL);
		// 读music得json文件
		StringBuffer buffer = music.readFile();
		msg.addInfo(buffer);
		return msg;
	}
	
	public int selectMusicRandomly(){
		TableMusicOperator musicOp = new TableMusicOperator();
		musicOp.connectDB();
		int musicid = musicOp.selectMusicIDRandomly();
		musicOp.disconnectDB();
		return musicid;
	}
}
