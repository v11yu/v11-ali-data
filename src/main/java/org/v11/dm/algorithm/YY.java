package org.v11.dm.algorithm;

import java.util.Date;
import java.util.List;

import org.v11.dm.entity.Record;
import org.v11.dm.tool.Contants;
import org.v11.dm.tool.InputTool;
import org.v11.dm.tool.InputToolImpl;
import org.v11.dm.tool.Tool;

public class YY {
	InputTool it = new InputToolImpl();
	List<Record> train = it.getRecord(Contants.record_filepath, 
			new Date(0), Tool.getTime("2014-12-16 23"));
	List<Record> test = it.getRecord(Contants.record_filepath, 
			Tool.getTime("2014-12-16 23"), new Date());
	public static void main(String[] args) {
		
	}
}
