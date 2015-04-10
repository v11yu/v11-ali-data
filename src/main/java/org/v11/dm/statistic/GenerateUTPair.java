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
import org.v11.dm.method.UTPairMethod;
import org.v11.dm.method.utp.HoursMethod;
import org.v11.dm.method.utp.IntervalMethod;
import org.v11.dm.method.utp.IsBuyMethod;
import org.v11.dm.method.utp.LastActionMethod;
import org.v11.dm.method.utp.LastDupActionMethod;
import org.v11.dm.method.utp.LastVsSumMethod;
import org.v11.dm.tool.Contants;
import org.v11.dm.tool.TimeTool;

public class GenerateUTPair {
	int N;
	Map<String, TmpInfo> mp = new HashMap<String,TmpInfo>();
	/*
	 *  [0,begin) 区间做统计
	 *  [begin,end) 做clas信息，买or没买
	 */
	Date begin;
	Date end;
	static Date lastTime = TimeTool.getTime(Contants.time18);
	int leftDistance;
	int rightDistance;
	int TT;
	public GenerateUTPair(Date bg,Date ed,int TT,int N){
		this.begin = bg;
		this.end = ed;
		this.N = N;
		this.leftDistance = (int) ((lastTime.getTime() - begin.getTime())/1000/3600);
		this.rightDistance = (int) ((lastTime.getTime() - end.getTime())/1000/3600);
		this.TT = TT;
	}
	void read(int T,int mod){
		File file = new File(Contants.read_filepath+Contants.record_filename);
		try {
			TmpInfo tmpInfo = null;
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
				String utStr = r.uid+","+r.tid;
				if(mp.containsKey(utStr)) {
					tmpInfo = mp.get(utStr);
				}else{
					tmpInfo = new TmpInfo();
				}
				if(r!=null && r.dis<= leftDistance && r.dis >= rightDistance
						&&r.op == 3){
					tmpInfo.clas = 1;
					mp.put(utStr, tmpInfo);
				}
				if(r!=null && r.dis>=leftDistance){
					r.dis -= TT;
					Action a = new Action(r.dis, r.op);
					tmpInfo.ls.add(a);
					mp.put(utStr, tmpInfo);
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
	UTPair sta(TmpInfo tmpInfo){
		UTPair utp = new UTPair();
		utp.clas = tmpInfo.clas;
		Collections.sort(tmpInfo.ls);
		UTPairMethod methods[] = {new HoursMethod(),
				new IntervalMethod(),
				new IsBuyMethod(),
				new LastActionMethod(),
				new LastDupActionMethod(),
				new LastVsSumMethod()};
		for(UTPairMethod method : methods){
			method.setAttribute(tmpInfo, utp);
		}
		return utp;
		
	}
	void output(int T,int mod){
		String filepath =  Contants.write_filepath;
		filepath += T+".csv";
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(filepath));
			int cnt = 0;
			System.out.println(mp.size());
			for (Entry<String, TmpInfo> en : mp.entrySet()) {
				cnt++;
				UTPair utp = sta(en.getValue());
				// System.out.println(en.getValue());
				StringBuilder tmp = new StringBuilder();
				tmp.append(en.getKey()).append(',')
						.append(utp.toString());
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
	public void work(){
		for(int i=0;i<N;i++){
			mp.clear();
			read(i,N);
			output(i,N);
		}
	}
//	public static void generateTesting(){
//		Date begin = TimeTool.getTime(Contants.time16);
//		Date end = TimeTool.getTime(Contants.time17);
//		String f1 = "testing.csv";
//		GenerateUTPair g = new GenerateUTPair(begin,end,48,5);
//		g.work();
//		MergeData mg = new MergeData();
//		mg.mergeAttribute(f1,N);
//		FilterAttribute fa = new FilterAttribute();
//		String sourcePath = Contants.write_filepath+f1;
//		String targetPath = Contants.write_filepath+"filter_"+f1;
//		fa.filter(sourcePath, targetPath);
//	}
//	public static void generateValidata(){
//		Date begin = TimeTool.getTime(Contants.time17);
//		Date end = TimeTool.getTime(Contants.time18);
//		String f1 = "validata.csv";
//		GenerateUTPair g = new GenerateUTPair(begin,end,24,5);
//		g.work();
//		MergeData mg = new MergeData();
//		mg.mergeAttribute(f1,N);
//		FilterAttribute fa = new FilterAttribute();
//		String sourcePath = Contants.write_filepath+f1;
//		String targetPath = Contants.write_filepath+"filter_"+f1;
//		fa.filter(sourcePath, targetPath);
//	}
//	public static void main(String[] args) {
//		/**
//		 * 16,17 ->test -> dis -48
//		 * 17,18 -> validata -> dis -24
//		 */
//		GenerateUTPair.generateValidata();
//	}
}
