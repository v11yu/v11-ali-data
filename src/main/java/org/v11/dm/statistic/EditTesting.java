package org.v11.dm.statistic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import org.v11.dm.entity.Record;
import org.v11.dm.tool.Contants;
import org.v11.dm.tool.TimeTool;

public class EditTesting {
	static Date from = TimeTool.getTime("2014-12-17 24");
	static Date to = TimeTool.getTime("2014-12-18 24");
	static int get(int op){
		if(op == 3) return 3;
		if(op == 2 || op == 1) return 2;
		return 1;
	}
	public static void main(String[] args) {
		File file = new File(Contants.record_filepath);
		List<Record> records = new ArrayList<Record>();
		Map<String,Integer> st = new HashMap<String,Integer>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String str ;
			int cnt = 0;
			List<String> strs = new ArrayList<String>();
			while((str=reader.readLine())!=null){
				if(cnt++ == 0) continue;
				strs.add(str);
				if(strs.size() > 100){
					Record r = Record.generate(str);
					String id = r.getUid()+","+r.getTid();
					if(r!=null && r.getTime().after(from) && r.getTime().before(to)){
						st.put(id,st.containsKey(id)?st.get(id)+get(r.op):get(r.op));
					}
					if(cnt % 100000 == 0) System.out.println("read.."+cnt);
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int val = 0;
		while(true){
			Scanner cin = new Scanner(System.in);
			int d = cin.nextInt();
			if(d == -1) break;
			int cnt = 0;
			Iterator<Entry<String, Integer>> iter = st.entrySet().iterator();
			while(iter.hasNext()){
				Entry<String, Integer> s = iter.next();
				if(s.getValue()>d) cnt ++;
			}
			System.out.println(cnt);
			val = d;
		}
		File wf = new File("C://Users//damao//Desktop//ali-data//output//submit.csv");
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(wf,true));
			out.write("user_id,item_id");
			out.newLine();
			Iterator<Entry<String, Integer>> iter = st.entrySet().iterator();
			while(iter.hasNext()){
				Entry<String, Integer> s = iter.next();
				if(s.getValue() <=val) continue;
				out.write(s.getKey());
				out.newLine();
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
