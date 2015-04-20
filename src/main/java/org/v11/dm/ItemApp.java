package org.v11.dm;

import java.util.Date;

import org.v11.dm.generate.GenerateItem;
import org.v11.dm.generate.GenerateUser;
import org.v11.dm.tool.TimeTool;

public class ItemApp {
	/**
	 * [0,T-1] 作为统计
	 * T 数据作为clas
	 * @param T
	 */
	public static void generateItem(int T){
		int hours = (18-T+1) * 24;
		String beginStr = "2014-12-"+(T-1)+" 24";
		String endStr = "2014-12-"+T+" 24";
		Date begin = TimeTool.getTime(beginStr);
		Date end = TimeTool.getTime(endStr);
		/*
		 * 为了和utp对应
		 */
		String f1 = "tdata"+(T-1)+"_clas"+(T)+".csv";
		GenerateItem g = new GenerateItem(end, hours, f1);
		g.work();
	}
	public static void main(String[] args) {
		long pre = new Date().getTime();
		generateItem(19);
		System.out.println("use time:"+(new Date().getTime() - pre)/1000);
	}
}
