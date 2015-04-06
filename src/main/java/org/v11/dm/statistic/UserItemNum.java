package org.v11.dm.statistic;

import java.util.Map;

import org.v11.dm.tool.InputTool;
import org.v11.dm.tool.InputToolImpl;
import org.v11.dm.tool.TimeTool;

public class UserItemNum {
	public static void main(String[] args) {
		String s1 = "2014-12-19 00";
		String s2 = "2014-12-18 24";
		System.out.println(TimeTool.getTime(s1));
		System.out.println(TimeTool.getTime(s2));
//		InputTool it = new InputToolImpl();
//		Map<String, Integer> mp = it.getMap("C://Users//damao//Desktop//ali-data//tianchi_mobile_recommend_train_user//tianchi_mobile_recommend_train_user.csv");
//		
//		System.out.println(mp.size());
	}
}
