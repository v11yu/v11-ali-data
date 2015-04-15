package org.v11.dm.statistic;

import org.v11.dm.tool.Contants;
import org.v11.dm.tool.FileUtil;

public class StaticCorrect {
	public void work(){
		FileUtil in = new FileUtil(Contants.write_filepath+"train.arff", "in");
		String str;
		int cnt = 0;
		int correct = 0;
		int incorrect = 0;
		while((str = in.readLine())!=null){
			
			String ls[] = str.split(",");
			if(ls.length<4) continue;
			int clas = Integer.parseInt(ls[ls.length-1]);
			if(clas == 1) correct ++;
			else incorrect++;
		}
		in.close();
		System.out.println("read1 finish..");
		System.out.println("correct count:"+correct);
		System.out.println("incorrect count:"+incorrect);
	
	}
	public static void main(String[] args) {
		new StaticCorrect().work();
	}
}
