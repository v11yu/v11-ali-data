package org.v11.dm.statistic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.v11.dm.entity.Action;
import org.v11.dm.entity.Record;
import org.v11.dm.entity.UTPair;
import org.v11.dm.tool.Contants;
import org.v11.dm.tool.OutputToolImpl;
import org.v11.dm.tool.TimeTool;

public class GenerateValidate {
	Map<String, UTPair> mp = new HashMap<String,UTPair>();
	Set<String> clas = new HashSet<String>();
	Date begin = TimeTool.getTime(Contants.class_time);
	Date end = TimeTool.getTime(Contants.validata_time);
	void read(){
		File file = new File(Contants.record_filepath);
		List<Record> records = new ArrayList<Record>();
		try {
			UTPair utp = null;
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String str ;
			int cnt = 0;
			while((str=reader.readLine())!=null){
				if(cnt++ == 0) continue;
				Record r = Record.generate(str);
				String utStr = r.uid+"$"+r.tid;
				if(mp.containsKey(utStr)) {
					utp = mp.get(utStr);
				}else{
					utp = new UTPair();
				}
				if(r!=null && r.getTime().after(begin) && r.getTime().before(end) ){
					if(r.dis <3){
						utp.d3[r.op]++;
					}else if(r.dis<5){
						utp.d5[r.op]++;
					}else if(r.dis<10){
						utp.d10[r.op]++;
					}else if(r.dis<16){
						utp.d16[r.op]++;
					}else if(r.dis<24){
						utp.d24[r.op]++;
					}else if(r.dis<24*3){
						utp.dd3[r.op]++;
					}else if(r.dis<24*7){
						utp.dd7[r.op]++;
					}
					if(r.op == 3) utp.clas = 1;
					Action a = new Action(r.dis, r.op);
					utp.ls.add(a);
					mp.put(utStr, utp);
				}
				if(cnt % 1000 == 0){
					System.out.println("read.."+cnt);
					//break;
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
	}
	void sta(){
		for(Entry<String, UTPair> en: mp.entrySet()){
			UTPair ut = en.getValue();
			Collections.sort(ut.ls);
			int pre3 = -1;
			int pre7 = -1;
			for(int i=0;i<ut.ls.size();i++){
				Action action = ut.ls.get(i);
				if(pre3 == -1) {
					pre3 = ut.ls.get(i).dis;
					pre7 = ut.ls.get(i).dis;
				}else{
					int cur = ut.ls.get(i).dis;
					int dd3 = Math.abs(pre3 - cur);
					if(dd3 >=3){
						ut.i3[action.type]++;
						pre3 = cur;
					}
					int dd7 = Math.abs(pre7 - cur);
					if(dd7 >=7){
						ut.i7[action.type]++;
						pre7 = cur;
					}
				}
			}
		}
	}
	void output(){
		OutputToolImpl ot = new OutputToolImpl();
		String filepath =  Contants.write_filepath;
		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String tim = format.format(d);
		filepath += tim+"validata.csv";
		int cnt = 0;
		System.out.println(mp.size());
		ot.output(Contants.attri_name, filepath);
		for(Entry<String, UTPair> en: mp.entrySet()){
			cnt++;
			//System.out.println(en.getValue());
			StringBuilder tmp = new StringBuilder();
			tmp.append(en.getKey()).append(',').append(en.getValue().toString());
			ot.output(tmp.toString(),filepath);
			if(cnt % 10000 == 0) System.out.println("write.."+cnt);
		}
		System.out.println(cnt);
	}
	void work(){
		read();
		sta();
		output();
	}
	public static void main(String[] args) {
		GenerateValidate g = new GenerateValidate();
		g.work();
		
	}
}
