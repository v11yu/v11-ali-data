package org.v11.dm.statistic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import org.v11.dm.tool.Contants;

public class UTPMergeU {
	String BAK = Contants.bak_write_filepath;
	String OUT = Contants.write_filepath;
	Map<String, String> umap = new HashMap<String, String>();
	void initUmap(String ufile){
		umap.clear();
		File file = new File(BAK+ufile);
		try{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String str;
			while((str = reader.readLine())!=null){
				String ls[] = str.split(",", 2);
				umap.put(ls[0],ls[1]);
			}
			System.out.println(umap.get("uid"));
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void merge(String utpfile,String ufile){
		initUmap(ufile);
		File file = new File(OUT+utpfile);
		
		File outfile = new File(OUT+"merge_"+utpfile);
		try{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			BufferedWriter out = new BufferedWriter(new FileWriter(outfile));
			String str = null;
			int cnt = 0;
			while((str = reader.readLine())!=null){
				String ls[] = str.split(",",3);
				out.write(ls[0]+","+ls[1]+",");
				out.write(umap.get(ls[0])+",");
				out.write(ls[2]);
				out.newLine();
			}
			reader.close();
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		int N = 5;
		String ufiles[] = new String[N];
		for(int i=0;i<ufiles.length;i++){
			ufiles[i] = "del_ufeature_train_"+(15+i)+"_div.csv";
		}
		String ufiles2[] = new String[N];
		for(int i=0;i<ufiles2.length;i++){
			ufiles2[i] = "udata18_clas19.csv";
		}
		String utfiles[] = new String[N];
		for(int i=0;i<utfiles.length;i++){
			utfiles[i] = "intersection_data"+(14+i)+"_clas"+(15+i)+".csv";
		}
		String utfiles2[] = new String[N];
		for(int i=0;i<utfiles2.length;i++){
			utfiles2[i] = "merge_intersection_data"+(14+i)+"_clas"+(15+i)+".csv";
		}
		for(int i=0;i<N;i++){
			UTPMergeU m = new UTPMergeU();
			m.merge(utfiles[i], ufiles[i]);
			m.merge(utfiles2[i], ufiles2[i]);
		}
	}
}
