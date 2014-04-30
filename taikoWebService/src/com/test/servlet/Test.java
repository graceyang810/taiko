package com.test.servlet;

import java.util.ArrayList;
import java.util.List;

import com.taiko.model.Feedback;
import com.taiko.model.Music;
import com.taiko.model.Player;
import com.taiko.model.Response;
import com.taiko.utility.Message;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Message msg = new Message();
		Player p = new Player(1, "name", "female", "touxiang", 1);
		Feedback feedback = new Feedback(true);
//		msg.addInfo(feedback);
		List<Player> list = new ArrayList<Player>();
		list.add(p);
		list.add(p);
//		msg.add(list);
		Music m = new Music(1,"musicName",2);
//		msg.add(m);
		Response r = new Response(1);
		msg.add(r);
		System.out.println(msg.toJson());
	}

}
