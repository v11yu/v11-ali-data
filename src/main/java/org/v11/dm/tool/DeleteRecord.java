package org.v11.dm.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.v11.dm.entity.Record;

public class DeleteRecord {
	String path = Contants.bak_write_filepath;
	public void filterRecord(String source){
		File file = new File(path+source);
		File outfile = new File(path+"del_"+source);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String str;
			BufferedWriter out = new BufferedWriter(new FileWriter(outfile));
			int cnt = 0;
			while((str=reader.readLine())!=null){
				if(cnt++ == 0){
					out.write(str.substring(1));
					out.newLine();
					continue;
				}
				String ls[] = str.split(",");
				StringBuilder sb = new StringBuilder();
				for(int i=1;i<ls.length;i++){
					if(i+1 == ls.length) sb.append(ls[i]);
					else sb.append(ls[i]).append(',');
				}
				//ls.add(r);
				out.write(sb.toString());
				out.newLine();
				if(cnt % 100000 == 0) System.out.println("read "+cnt);
			}
			reader.close();
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		String paths[] = {"ufeature_train_15_div.csv","ufeature_train_16_div.csv","ufeature_train_17_div.csv",
				"ufeature_train_18_div.csv","ufeature_train_19_div.csv"};
		DeleteRecord dr = new DeleteRecord();
		for(String path:paths){
			dr.filterRecord(path);
		}
	}
}
