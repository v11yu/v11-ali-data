package org.v11.dm.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.v11.dm.entity.Record;

public class SortRecord {
	
	public void sortRecord(){
		File file = new File("D://"+Contants.record_filename);
		File outfile = new File("D://"+"urecord.csv");
		List<Record> ls = new ArrayList<Record>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String str;
			int cnt = 0;
			while((str=reader.readLine())!=null){
				if(cnt++ == 0) continue;
				Record r = Record.generate(str);
				if(r==null) continue;
				ls.add(r);
				if(cnt % 1000000 == 0) System.out.println("read "+cnt);
			}
			reader.close();
			System.out.println("read finish...");
			BufferedWriter out = new BufferedWriter(new FileWriter(outfile));
			out.write(Contants.record_attri_name);
			out.newLine();
			Collections.sort(ls);
			System.out.println("sort finish...");
			cnt = 0;
			for(Record r:ls){
				out.write(r.toString());
				out.newLine();
				if(cnt++% 1000000 == 0) System.out.println("write..."+cnt);
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new SortRecord().sortRecord();
	}
}
