package org.v11.dm;

import java.util.Date;

import org.v11.dm.statistic.FilterAttribute;
import org.v11.dm.statistic.GenerateUTPair;
import org.v11.dm.statistic.MergeData;
import org.v11.dm.tool.Contants;
import org.v11.dm.tool.TimeTool;

/**
 * Hello world!
 *
 */
public class App 
{
	static int N = 5;
	private static void generate(Date begin,Date end,String f1,int TT,int N){
		GenerateUTPair g = new GenerateUTPair(begin,end,TT,N);
		g.work();
		MergeData mg = new MergeData();
		mg.mergeAttribute(f1,N);
		FilterAttribute fa = new FilterAttribute();
		String sourcePath = Contants.write_filepath+f1;
		String targetPath = Contants.write_filepath+"filter_"+f1;
		fa.filter(sourcePath, targetPath);
	}
	public static void generateValidata(){
		Date begin = TimeTool.getTime(Contants.time17);
		Date end = TimeTool.getTime(Contants.time18);
		String f1 = "validata.csv";
		generate(begin,end,f1,24,N);
	}
	public static void generateTesting(){
		Date begin = TimeTool.getTime(Contants.time16);
		Date end = TimeTool.getTime(Contants.time17);
		String f1 = "testing.csv";
		generate(begin,end,f1,48,N);
	}
	public static void generate19(){
		Date begin = TimeTool.getTime(Contants.time18);
		Date end = TimeTool.getTime(Contants.time19);
		String f1 = "19.csv";
		generate(begin,end,f1,0,N);
	}
	public static void main(String[] args) {
		generateTesting();
	}
}
