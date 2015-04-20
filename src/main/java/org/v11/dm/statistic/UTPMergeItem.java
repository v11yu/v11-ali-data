package org.v11.dm.statistic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import org.v11.dm.tool.Contants;

public class UTPMergeItem {
	String BAK = Contants.bak_write_filepath;
	String OUT = Contants.write_filepath;
	Map<String, String> tmap = new HashMap<String, String>();
	void initUmap(String ufile){
		tmap.clear();
		File file = new File(BAK+ufile);
		try{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String str;
			while((str = reader.readLine())!=null){
				String ls[] = str.split(",", 2);
				tmap.put(ls[0],ls[1]);
			}
			System.out.println(tmap.get("tid"));
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void merge(String utpfile,String ufile){
		
		File file = new File(OUT+utpfile);
		File outfile = new File(OUT+"merge_tid_"+utpfile);
		try{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			BufferedWriter out = new BufferedWriter(new FileWriter(outfile));
			String str = null;
			int cnt = 0;
			while((str = reader.readLine())!=null){
				String ls[] = str.split(",",3);
				out.write(ls[0]+","+ls[1]+",");
				out.write(tmap.get(ls[1])+",");
				out.write(ls[2]);
				out.newLine();
			}
			reader.close();
			out.flush();
			out.close();
			System.out.println(utpfile+" finished..");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		int N = 5;
		String ufiles2[] = new String[N];
		for(int i=0;i<ufiles2.length;i++){
			ufiles2[i] = "tdata18_clas19.csv";
		}
		String utfiles[] = new String[N];
		for(int i=0;i<utfiles.length;i++){
			utfiles[i] = "merge_intersection_data"+(14+i)+"_clas"+(15+i)+".csv";
		}
		UTPMergeItem m = new UTPMergeItem();
		m.initUmap(ufiles2[0]);
		for(int i=0;i<N;i++){
			m.merge(utfiles[i], ufiles2[i]);
		}
	}
}
