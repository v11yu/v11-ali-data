package org.v11.dm;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.v11.dm.tool.Contants;
import org.v11.dm.tool.FileUtil;

public class MergeSubmitApp {
	String path = Contants.write_filepath+"submit-data/";
	Set<String> st = new HashSet<String>();
	public void merge(String files[]){
		String fl = null;
		for(String file:files){
			FileUtil read = new FileUtil(path+file, "in");
			fl = read.readLine();
			String str;
			while((str = read.readLine())!=null){
				st.add(str);
			}
			read.close();
			System.out.println(" finish..set size:"+st.size());
		}
		FileUtil out = new FileUtil(path+"final_submit.csv","out");
		out.writeLine(fl);
		for(String str:st){
			out.writeLine(str);
		}
		out.close();
	}
	public void merge(int T){
		String[] files = new String[T];
		for(int i=0;i<T;i++){
			files[i] = "submit_result_"+(i+1)+".csv";
		}
		merge(files);
	}
	public static void main(String[] args) {
		//new MergeSubmitApp().merge(5);
		String ls[] = {"1.csv","2.csv"};
		new MergeSubmitApp().merge(ls);
	}
}
