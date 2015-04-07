package org.v11.dm.algorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.v11.dm.entity.Record;
import org.v11.dm.tool.Contants;
import org.v11.dm.tool.F1Tools;
import org.v11.dm.tool.InputTool;
import org.v11.dm.tool.InputToolImpl;
import org.v11.dm.tool.OutputToolImpl;
import org.v11.dm.tool.TimeTool;

public class YY {
	InputTool it = new InputToolImpl();
	OutputToolImpl ot = new OutputToolImpl();
	List<Record> train = it.getRecord(Contants.record_filepath, new Date(0),
			TimeTool.getTime("2014-12-16 23"));
	List<Record> test = it.getRecord(Contants.record_filepath,
			TimeTool.getTime("2014-12-16 23"), new Date());
	Map<String, Double> mp= new HashMap<String, Double>();
	Date target = TimeTool.getTime("2014-12-16 17");
	Double getOP(int op){
		if(op == 1) return 0.1;
		if(op == 2) return 0.3;
		if(op == 3) return 0.3;
		return 0.2;
	}
	Double getWeight(Record r){
		Date d = r.getTime();
		int op = r.getOp();
		int dif = TimeTool.dif(d, target);
		dif = (30-dif)/5;
		return dif*getOP(op);
	}
	void add(String str,Double v){
		mp.put(str, mp.containsKey(str)?mp.get(str)+v:v);
	}
	public void work() {
		for (Record r : train) {
			String s = r.getUid()+","+r.getTid();
			add(s,getWeight(r));
		}
		List<String> res = F1Tools.getRes(mp, 2.0);
		System.out.println("res size:"+res.size());
		ot.output(res, Contants.write_filepath);
	}

	public static void main(String[] args) {
		YY y = new YY();
		y.work();
	}
}
