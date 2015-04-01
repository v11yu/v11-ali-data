package org.v11.dm.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.v11.dm.entity.Item;
import org.v11.dm.entity.Record;

public class InputToolImpl implements InputTool{

	@Override
	public List<Record> getRecord(String filepath, Date from, Date to) {
		// TODO Auto-generated method stub
		File file = new File(filepath);
		List<Record> records = new ArrayList<Record>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String str ;
			int cnt = 0;
			while((str=reader.readLine())!=null){
				if(cnt++ == 0) continue;
				Record r = Record.generate(str);
				if(r!=null && r.getTime().after(from) && r.getTime().before(to)){
					records.add(r);

				}
//				System.out.println(cnt+" "+r);
//				sleep();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return records;
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
		InputTool it = new InputToolImpl();
		//it.getRecord(Contants.record_filepath, new Date(0), new Date());
		it.getItem(Contants.item_filepath);
	}
}
