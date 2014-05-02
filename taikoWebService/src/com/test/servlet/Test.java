package com.test.servlet;

import com.taiko.model.Music;
import com.taiko.utility.Message;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Message msg = new Message();
//		Player p = new Player(1, "name", "female", "touxiang", 1);
//		Feedback feedback = new Feedback(true);
////		msg.addInfo(feedback);
//		List<Player> list = new ArrayList<Player>();
//		list.add(p);
//		list.add(p);
////		msg.add(list);
		Music m = new Music(1,"musicName",2);
		msg.add(m);
		StringBuffer buffer = m.readFile("RhythmInfo/rhythm_1.json");
//		Response r = new Response(1);
//		msg.add(r);
//		String s = "a\n"+"\\\"b\"";
//		System.out.println(s);
//		String s2=s.replace("\n", "");
//		String data= ReadFile.readFile("RhythmInfo/rhythm_1.json");
		String s = buffer.toString();
//		String s1 = s.replace("\\", "");
		String s2 = s.replace(" ", "");
		msg.addInfo(s2);
//		System.out.println(s2);
		System.out.println(msg.toJson());
	}

}
