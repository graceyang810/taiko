package com.taiko.utility;

import java.sql.ResultSet;

import com.taiko.database.TableMusicOperator;
import com.taiko.database.TableResultOperator;
import com.taiko.database.TableUserOperator;
import com.taiko.model.Music;
import com.taiko.model.MusicInfoURL;
import com.taiko.model.Player;
import com.taiko.model.Result;

public class DBOperator {// EntityGetter?

	public Player getPlayer(int id) {
		TableUserOperator userOp = new TableUserOperator();
		userOp.connectDB();
		// id,name,gender,photo,level
		Player p = new Player(id, userOp.selectUserName(id),
				userOp.selectGender(id), userOp.selectUserPhoto(id),
				userOp.selectUserLevel(id));
		userOp.disconnectDB();
		return p;
	}

	public Music getMusic(int id) {
		TableMusicOperator musicOp = new TableMusicOperator();
		musicOp.connectDB();
		// id,name,level
		Music m = new Music(id, musicOp.selectMusicName(id),
				musicOp.selectMusicDifficulty(id));
		musicOp.disconnectDB();
		return m;
	}

	public ResultSet getMusicList(int userID) {
		TableUserOperator userOp = new TableUserOperator();
		userOp.connectDB();
		int level = userOp.selectUserLevel(userID);
//		System.out.println("level = "+level);
		userOp.disconnectDB();
		TableMusicOperator musicOp = new TableMusicOperator();
		musicOp.connectDB();
		ResultSet rs = musicOp.selectMusicList(level);
		musicOp.disconnectDB();
		return rs;
	}

	public Message addMusicInfo(Message msg, int musicID) {
		TableMusicOperator musicOp = new TableMusicOperator();
		musicOp.connectDB();
		MusicInfoURL musicurl = new MusicInfoURL(
				musicOp.selectMusicSound(musicID),
				musicOp.selectMusicRhythm(musicID));
		// String rhythmURL = musicOp.selectMusicRhythm(musicID);
		musicOp.disconnectDB();
		Music music = getMusic(musicID);
		// 读music得json文件
		// StringBuffer rhythmBuffer = music.readFile(rhythmURL);
		msg.addInfo(music);
		msg.addInfo(musicurl);
		// msg.addInfo(rhythmURL);
		// msg.addInfo(rhythmBuffer);
		return msg;
	}

	public int selectMusicRandomly() {
		TableMusicOperator musicOp = new TableMusicOperator();
		musicOp.connectDB();
		int musicid = musicOp.selectMusicIDRandomly();
		musicOp.disconnectDB();
		return musicid;
	}

	public Result getResult(int id) {
		TableResultOperator resultOp = new TableResultOperator();
		resultOp.connectDB();
		// id,score,perfect,cool,miss,combo
		Result r = new Result(id, resultOp.selectValue(id, "score"),
				resultOp.selectValue(id, "perfect"), resultOp.selectValue(id,
						"cool"), resultOp.selectValue(id, "miss"),
				resultOp.selectValue(id, "combo"));
		resultOp.disconnectDB();
		return r;
	}
}
