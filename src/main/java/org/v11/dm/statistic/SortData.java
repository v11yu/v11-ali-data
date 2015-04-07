package org.v11.dm.statistic;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.v11.dm.entity.Record;
import org.v11.dm.tool.Contants;
import org.v11.dm.tool.InputTool;
import org.v11.dm.tool.InputToolImpl;

public class SortData {
	public void work(){
		InputTool it = new InputToolImpl();
		List<Record> ls = it.getRecordALL(Contants.record_filepath);
		Collections.sort(ls);
		
	}
}
