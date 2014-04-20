package com.taiko.utility;

import java.sql.ResultSet;

import com.taiko.database.TableMusicOperator;
import com.taiko.database.TableUserOperator;
import com.taiko.model.Music;
import com.taiko.model.Player;

public class DBOperator {

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
	
	public Music getMusic(int id){
		TableMusicOperator musicOp = new TableMusicOperator();
		musicOp.connectDB();
		//id,name,level
		Music m = new Music(id, musicOp.selectMusicName(id),
				musicOp.selectMusicDifficulty(id));
		musicOp.disconnectDB();
		return m;
	}
	
	public ResultSet getMusicList(int id){
		TableMusicOperator musicOp = new TableMusicOperator();
		musicOp.connectDB();		
		ResultSet rs = musicOp.selectMusicList(musicOp.selectMusicDifficulty(id));
		musicOp.disconnectDB();
		return rs;
	}
	
	public Message addMusicInfo(Message msg, int musicID){
		TableMusicOperator musicOp = new TableMusicOperator();
		musicOp.connectDB();
		String rhythmURL = musicOp.selectMusicRhythm(musicID);
		musicOp.disconnectDB();
		Music music = getMusic(musicID);
		// 读music得json文件
		StringBuffer rhythmBuffer = music.readFile(rhythmURL);
		msg.addInfo(music);
		msg.addInfo(musicOp.selectMusicSound(musicID));
		msg.addInfo(rhythmBuffer);
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
