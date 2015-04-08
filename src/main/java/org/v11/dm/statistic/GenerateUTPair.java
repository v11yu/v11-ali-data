package org.v11.dm.statistic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import org.v11.dm.entity.*;
import org.v11.dm.tool.Contants;

import org.v11.dm.tool.TimeTool;

public class GenerateUTPair {

	Map<String, UTPair> mp = new HashMap<String,UTPair>();
	/*
	 *  [0,begin) 区间做统计
	 *  [begin,end) 做clas信息，买or没买
	 */
	Date begin;
	Date end;
	static Date lastTime = TimeTool.getTime(Contants.time18);
	int leftDistance;
	int rightDistance;
	public GenerateUTPair(Date bg,Date ed){
		this.begin = bg;
		this.end = ed;
		this.leftDistance = (int) ((lastTime.getTime() - begin.getTime())/1000/3600);
		this.rightDistance = (int) ((lastTime.getTime() - end.getTime())/1000/3600);
	}
	void read(int T,int mod){
		File file = new File(Contants.read_filepath+Contants.record_filename);
		List<Record> records = new ArrayList<Record>();
		try {
			UTPair utp = null;
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String str ;
			int cnt = 0;
			while((str=reader.readLine())!=null){
				if(cnt % 100000 == 0){
					System.out.println(T+" read.."+cnt);
					//break;
				}
				if(cnt++ == 0) continue;
				Record r = Record.generate(str);
				if(r==null || r.uid%mod !=T) continue;
				String utStr = r.uid+"$"+r.tid;
				if(mp.containsKey(utStr)) {
					utp = mp.get(utStr);
				}else{
					utp = new UTPair();
				}
				if(r!=null && r.dis<= leftDistance && r.dis >= rightDistance
						&&r.op == 3){
					utp.clas = 1;
				}
				if(r!=null && r.dis>=leftDistance){
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
					Action a = new Action(r.dis, r.op);
					utp.ls.add(a);
					mp.put(utStr, utp);
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
	void output(int mod,int T){
		
		String filepath =  Contants.write_filepath;
		filepath += T+".csv";
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(filepath, true));
			int cnt = 0;
			System.out.println(mp.size());
			for (Entry<String, UTPair> en : mp.entrySet()) {
				cnt++;
				// System.out.println(en.getValue());
				StringBuilder tmp = new StringBuilder();
				tmp.append(en.getKey()).append(',')
						.append(en.getValue().toString());
				out.write(tmp.toString());
				out.newLine();
				if (cnt % 10000 == 0)
					System.out.println("write.." + cnt);
			}
			System.out.println(cnt);
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void work(){
		int N = 5;
		for(int i=0;i<N;i++){
			mp.clear();
			read(i,N);
			sta();
			output(N,i);
		}
	}
	public static void main(String[] args) {
		Date begin = TimeTool.getTime(Contants.time17);
		Date end = TimeTool.getTime(Contants.time18);
		String f1 = "validata.csv";
		GenerateUTPair g = new GenerateUTPair(begin,end);
		g.work();
		MergeData mg = new MergeData();
		mg.mergeAttribute(f1);
		FilterAttribute fa = new FilterAttribute();
		String sourcePath = Contants.write_filepath+f1;
		String targetPath = Contants.write_filepath+"filter_"+f1;
		fa.filter(sourcePath, targetPath);
		
	}
}
