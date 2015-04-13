package org.v11.dm.generate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.v11.dm.entity.Action;
import org.v11.dm.entity.Record;
import org.v11.dm.entity.TmpInfo;
import org.v11.dm.entity.UTPair;
import org.v11.dm.method.UTPairMethod;
import org.v11.dm.method.utp.HoursMethod;
import org.v11.dm.method.utp.IntervalMethod;
import org.v11.dm.method.utp.IsBuyMethod;
import org.v11.dm.method.utp.LastActionMethod;
import org.v11.dm.method.utp.LastDupActionMethod;
import org.v11.dm.method.utp.LastVsSumMethod;
import org.v11.dm.tool.Contants;
import org.v11.dm.tool.TimeTool;
public class GenerateUTP {
	/*
	 *  [0,begin) 区间做统计
	 *  [begin,end) 做clas信息，买or没买
	 */
	static Date lastTime = TimeTool.getTime(Contants.time18);
	int leftDistance;
	int rightDistance;
	int TT;
	String fileName;
	public GenerateUTP(Date begin,Date end,int TT,String fileName){
		this.leftDistance = (int) ((lastTime.getTime() - begin.getTime())/1000/3600);
		this.rightDistance = (int) ((lastTime.getTime() - end.getTime())/1000/3600);
		this.TT = TT;
		this.fileName = fileName;
	}
	public void work(){
		File outFile = new File(Contants.write_filepath+fileName);  
		File file = new File(Contants.read_filepath+Contants.record_filename);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			BufferedWriter out = new BufferedWriter(new FileWriter(outFile));
			out.write(Contants.getAttributeName());
			out.newLine();
			String preUtp = null;
			String str ;
			int cnt = 0;
			UTPair utp = null;
			TmpInfo curInfo = new TmpInfo();
			int outCnt = 0;
			int delCnt = 0;
			while((str=reader.readLine())!=null){
				if(cnt++ == 0) continue;
				if(cnt % 100000 == 0) System.out.println("read.."+cnt);
				Record r = Record.generate(str);
				if(r==null) continue;
				String curUtp = r.uid+","+r.tid;
				if(preUtp!=null &&!curUtp.equals(preUtp)){
					utp = sta(curInfo);
					if(judgeHasValue(utp)){
						out.write(preUtp+","+utp);
						out.newLine();
						outCnt++;
					}else delCnt++;
					curInfo = new TmpInfo();
				}
				if(r!=null && r.dis<= leftDistance && r.dis >= rightDistance
						&&r.op == 3){
					curInfo.clas = 1;
				}
				if(r!=null && r.dis>=leftDistance){
					r.dis -= TT;
					Action a = new Action(r.dis, r.op);
					curInfo.ls.add(a);
				}
				preUtp = curUtp;
			}
			utp = sta(curInfo);
			if(judgeHasValue(utp)){
				out.write(preUtp+","+utp);
				out.newLine();
			}
			System.out.println("print delete count:"+delCnt);
			System.out.println("print add count:"+outCnt);
			reader.close();
			out.flush();
			out.close();
		} catch (Exception e) {
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
	boolean judgeHasValue(UTPair utp){
		for(int i=0;i<4;i++){
			if(utp.hours[utp.Hours.length-1][i] >0) return true;
		}
		return false;	
	}
}
