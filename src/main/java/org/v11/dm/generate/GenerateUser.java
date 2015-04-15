package org.v11.dm.generate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import org.v11.dm.entity.Action;
import org.v11.dm.entity.Record;
import org.v11.dm.entity.TmpInfo;
import org.v11.dm.entity.UTPair;
import org.v11.dm.entity.User;
import org.v11.dm.method.UTPairMethod;
import org.v11.dm.method.UserMethod;
import org.v11.dm.method.user.UAvgBuyMethod;
import org.v11.dm.method.user.UslideWinMethod;
import org.v11.dm.method.utp.AvgBuyMethod;
import org.v11.dm.method.utp.HoursMethod;
import org.v11.dm.method.utp.IntervalMethod;
import org.v11.dm.method.utp.IsBuyMethod;
import org.v11.dm.method.utp.LastActionMethod;
import org.v11.dm.method.utp.LastDupActionMethod;
import org.v11.dm.method.utp.LastVsSumMethod;
import org.v11.dm.tool.Contants;
import org.v11.dm.tool.TimeTool;

public class GenerateUser {
	/*
	 *  [0,begin) 区间做统计
	 *  [begin,end) 做clas信息，买or没买
	 */
	static Date lastTime = TimeTool.getTime(Contants.time18);
	int distance;
	int TT;
	String fileName;
	public GenerateUser(Date tim,int TT,String fileName){
		this.distance = (int) ((lastTime.getTime() - tim.getTime())/1000/3600);
		this.TT = TT;
		this.fileName = fileName;
	}
	public void work(){
		File outFile = new File(Contants.write_filepath+fileName);  
		File file = new File(Contants.read_filepath+Contants.record_filename);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			BufferedWriter out = new BufferedWriter(new FileWriter(outFile));
			out.write(Contants.getUserAttributeName());
			out.newLine();
			String preUtp = null;
			String str ;
			int cnt = 0;
			User uInfo = null;
			TmpInfo curInfo = new TmpInfo();
			while((str=reader.readLine())!=null){
				if(cnt++ == 0) continue;
				if(cnt % 100000 == 0) System.out.println("read.."+cnt);
				Record r = Record.generate(str);
				if(r==null) continue;
				String curUtp = r.uid+"";
				if(preUtp!=null &&!curUtp.equals(preUtp)){
					uInfo = sta(curInfo);
					out.write(preUtp+","+uInfo);
					out.newLine();
					curInfo = new TmpInfo();
				}
				if(r!=null && r.dis>=distance){
					r.dis -= TT;
					Action a = new Action(r.dis, r.op,r.tid);
					curInfo.ls.add(a);
				}
				preUtp = curUtp;
			}
			uInfo = sta(curInfo);
			out.write(preUtp+","+uInfo);
			out.newLine();
			reader.close();
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	User sta(TmpInfo tmpInfo){
		User uinfo = new User();
		Collections.sort(tmpInfo.ls);
		UserMethod methods[] = {new UAvgBuyMethod(),
				new UslideWinMethod()};
		for(UserMethod method : methods){
			method.setAttribute(tmpInfo, uinfo);
		}
		return uinfo;
	}
}
