package org.v11.dm.weka;

import org.v11.dm.tool.Contants;
import org.v11.dm.tool.FileUtil;

public class GenerateSubmit {
	public void work(String source){
		FileUtil in = new FileUtil(Contants.write_filepath+source, "in");
		FileUtil out = new FileUtil(Contants.write_filepath+"submit_"+source,"out");
		String str;
		int cnt = 0;
		int correct = 0;
		int incorrect = 0;
		out.writeLine("user_id,item_id");
		while((str = in.readLine())!=null){
			if(cnt ++ == 0){
				continue;
			}
			String ls[] = str.split(",");
			int clas = Integer.parseInt(ls[ls.length-1]);
			if(clas == 1) {
				correct ++;
				out.writeLine(ls[0]+","+ls[1]);
			}
			else incorrect++;
		}
		in.close();
		out.close();
		System.out.println("read1 finish..");
		System.out.println("correct count:"+correct);
		System.out.println("incorrect count:"+incorrect);
	
	}
	public static void main(String[] args) {
		new GenerateSubmit().work("result_5.csv");
	}
}
