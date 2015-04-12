package org.v11.dm.statistic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

import org.v11.dm.tool.Contants;

public class SampleData {
	int T = 50;// 负样本比例
	public void sample(String filename){
		String path = Contants.bak_write_filepath;
		File readpath = new File(path+filename);
		File tarpath = new File(path+"sameple_"+T+"_"+filename);
		try{
			BufferedReader reader = new BufferedReader(new FileReader(readpath));
			BufferedWriter out = new BufferedWriter(new FileWriter(tarpath));
			String str = null;
			int cnt = 0;
			int correct = 0;
			while((str=reader.readLine())!=null){
				if(cnt++ == 0){
					out.write(str);
					out.newLine();
					continue;
				}
				if(cnt % 1000000 == 0) System.out.println("read "+cnt+getName(filename));
				String ls[] = str.split(",");
				int clas = Integer.parseInt(ls[ls.length-1]);
				if(clas == 1) {
					correct++;
					out.write(editStr(str,clas));
					out.newLine();
				}
			}
			reader.close();
			System.out.println("read finished .."+getName(filename));
			System.out.println("correct count:"+correct+getName(filename));
			/*
			 * random incorrect data, and write into file
			 */
			reader = new BufferedReader(new FileReader(readpath));
			int incorrect = correct * T;
			int numCount = cnt;
			int outCnt = 0;
			cnt = 0;
			Random random = new Random();
			while((str=reader.readLine())!=null){
				if(cnt++ == 0) continue;
				if(cnt % 1000000 == 0) System.out.println("read "+cnt+getName(filename));
				String ls[] = str.split(",");
				int clas = Integer.parseInt(ls[ls.length-1]);
				if(clas == 0) {
					int randomNum = random.nextInt(numCount);
					if(randomNum > incorrect) continue;
					outCnt++;
					out.write(editStr(str,clas));
					out.newLine();
				}
			}
			System.out.println("write finished .."+getName(filename));
			System.out.println("want incorrect:"+incorrect+getName(filename));
			System.out.println("reality incorrect:"+outCnt+getName(filename));
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private String editStr(String str,int clas){
		String strs[] = str.split(",");
		StringBuilder res = new StringBuilder();
		for(int i=0;i<strs.length-1;i++){
			res.append(strs[i]).append(',');
		}
		if(clas == 1) res.append("yes");
		else res.append("no");
		return res.toString();
		
	}
	private String getName(String str){return " ["+str+"]";}
	public static void main(String[] args) {
		SampleData sample = new SampleData();
		sample.sample("filter_validata.csv");
	}
}
