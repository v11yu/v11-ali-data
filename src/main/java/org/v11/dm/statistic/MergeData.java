package org.v11.dm.statistic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

import org.v11.dm.tool.Contants;

public class MergeData {
	String path = Contants.write_filepath;
	public void merge(String files[],String target){
		File outf = new File(path+target);
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(outf));
			
			for(int i=0;i<files.length;i++){
				File inf = new File(path+files[i]);
				BufferedReader reader = new BufferedReader(new FileReader(inf));
				String str;
				int cnt = 0;
				while((str = reader.readLine())!=null){
					if(cnt ++ == 0){
						if(i == 0) {
							out.write(str);
							out.newLine();
						}
						continue;
					}
					out.write(str);
					out.newLine();
				}
				reader.close();
			}
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		String files[] = new String[3];
		for(int i=0;i<3;i++){
			files[i] = "sameple_100_merge_merge_intersection_data"+(14+i)+"_clas"+(15+i)+".csv";
		}
		new MergeData().merge(files, "finalTrainData.csv");
	}
}
