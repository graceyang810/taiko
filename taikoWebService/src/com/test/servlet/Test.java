package com.test.servlet;

import com.taiko.model.Feedback;
import com.taiko.model.Player;
import com.taiko.utility.Message;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Player p = new Player(1, "name", "nv", "touxiang", 1);
		Message msg = new Message();
		msg.addInfo(p);
		msg.addInfo(p);
		Feedback feedback = new Feedback(true);
		msg.add("error");
		msg.addInfo(feedback);
		System.out.println(msg.toJson());
	}

}
