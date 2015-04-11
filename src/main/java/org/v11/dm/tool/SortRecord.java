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
	public void filterRecord(){
		File file = new File(Contants.read_filepath+"user_sort_record.csv");
		File outfile = new File("");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String str;
			BufferedWriter out = new BufferedWriter(new FileWriter(outfile));
			int cnt = 0;
			out.write(Contants.record_attri_name);
			out.newLine();
			while((str=reader.readLine())!=null){
				if(cnt++ == 0) continue;

				Record r = Record.generate(str);
				if(r==null) continue;
				//ls.add(r);
				out.write(r.toString());
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
	public void sortRecord(){
		File file = new File(Contants.read_filepath+Contants.record_filename);
		File outfile = new File("");
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
			for(Record r:ls){
				out.write(r.toString());
				out.newLine();
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
