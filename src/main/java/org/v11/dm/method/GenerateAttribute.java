package org.v11.dm.method;

import java.util.Date;

import org.v11.dm.tool.Contants;
import org.v11.dm.tool.TimeTool;

public class GenerateAttribute {
	String recordFilePath = Contants.record_filepath + Contants.record_filename;
	static Date time16 = TimeTool.getTime(Contants.time16);
	static Date time17 = TimeTool.getTime(Contants.time17);
	static Date time18 = TimeTool.getTime(Contants.time18);
	static Date time19 = TimeTool.getTime(Contants.time19);
	static Date lastTime = TimeTool.getTime(Contants.time18);
	int leftDistance;
	int rightDistance;
	int TT;
	private GenerateAttribute(Date begin,Date end,int TT){
		this.leftDistance = (int) ((lastTime.getTime() - begin.getTime())/1000/3600);
		this.rightDistance = (int) ((lastTime.getTime() - end.getTime())/1000/3600);
		this.TT = TT;
	}
	public static GenerateAttribute Get16(){
		return new GenerateAttribute(time16,time17,48);
	}
	public static GenerateAttribute Get17(){
		return new GenerateAttribute(time17,time18,24);
	}
	public static GenerateAttribute Get18(){
		return new GenerateAttribute(time18,time19,24);
	}
}
