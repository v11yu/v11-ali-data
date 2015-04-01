package org.v11.dm.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.v11.dm.entity.Item;
import org.v11.dm.entity.Record;

public class Tmp implements InputTool{
	Map<String, Double> mp= new HashMap<String, Double>();
	Date target = TimeTool.getTime("2014-12-16 17");
	OutputTool ot = new OutputTool();
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
	@Override
	public List<Record> getRecord(String filepath, Date from, Date to) {
		// TODO Auto-generated method stub
		File file = new File(filepath);

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String str ;
			int cnt = 0;
			while((str=reader.readLine())!=null){
				if(cnt++ == 0) continue;
				Record r = Record.generate(str);
				if(r!=null && r.getTime().after(from) && r.getTime().before(to)){
					String s = r.getUid()+","+r.getTid();
					add(s,getWeight(r));
				}
				if(cnt % 1000 == 0) System.out.println("read.."+cnt);
				if(cnt > 1900000) break;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		ot.output(mp, Contants.write_filepath,2.0);
		return null;
	}

	@Override
	public List<Item> getItem(String filepath) {
		// TODO Auto-generated method stub
		File file = new File(filepath);
		List<Item> items = new ArrayList<Item>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String str ;
			int cnt = 0;
			while((str=reader.readLine())!=null){
				if(cnt++ == 0) continue;
				Item r = Item.generate(str);
				if(r!=null){
					items.add(r);
				}
				System.out.println(cnt+" "+r);
				sleep();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;
	}
	void sleep(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Tmp t = new Tmp();
		t.getRecord(Contants.record_filepath, new Date(0),
				TimeTool.getTime("2014-12-16 23"));
	}
}
