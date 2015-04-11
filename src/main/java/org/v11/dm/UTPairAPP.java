package org.v11.dm;

import java.util.Date;

import org.v11.dm.statistic.GenerateUTP;
import org.v11.dm.tool.Contants;
import org.v11.dm.tool.TimeTool;

public class UTPairAPP {
	public static void generateValidata(){
		Date begin = TimeTool.getTime(Contants.time17);
		Date end = TimeTool.getTime(Contants.time18);
		String f1 = "validata.csv";
		GenerateUTP g = new GenerateUTP(begin, end, 24, f1);
		g.work();
	}
	public static void generateTesting(){
		Date begin = TimeTool.getTime(Contants.time16);
		Date end = TimeTool.getTime(Contants.time17);
		String f1 = "testing.csv";
		GenerateUTP g = new GenerateUTP(begin, end, 48, f1);
		g.work();
	}
	public static void generate19(){
		Date begin = TimeTool.getTime(Contants.time18);
		Date end = TimeTool.getTime(Contants.time19);
		String f1 = "submit.csv";
		GenerateUTP g = new GenerateUTP(begin, end, 0, f1);
		g.work();
	}
	public static void main(String[] args) {
		long pre = new Date().getTime();
		generateTesting();
		System.out.println("use time:"+(new Date().getTime() - pre)/1000);
	}
}
