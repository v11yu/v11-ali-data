package org.v11.dm.tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class OutputTool {
	/**
	 * 写入预测结果
	 * @param res 
	 * @param filepath 写入的路径
	 */
	public void output(List<String> res,String filepath){
		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String tim = format.format(d);
		filepath += tim+".csv";
		File file = new File(filepath);
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter(file));
			w.write("user_id,item_id");
			w.newLine();
			for(String str:res){
				w.write(str);
				w.newLine();
			}
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 写入预测结果
	 * @param res 
	 * @param filepath 写入的路径
	 * @param v 权重阈值
	 */
	public void output(Map<String, Double> res,String filepath,Double v){
		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String tim = format.format(d);
		filepath += tim+".csv";
		File file = new File(filepath);
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter(file));
			w.write("user_id,item_id");
			w.newLine();
			Iterator<Entry<String, Double>> iter = res.entrySet().iterator();
			while(iter.hasNext()){
				Entry<String, Double> en = iter.next();
				if(en.getValue()>v) {
					w.write(en.getKey());
					w.newLine();
				}
			}
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		OutputTool ot = new OutputTool();
		List<String> res = new ArrayList<String>();
		res.add("aaa");
		ot.output(res, Contants.write_filepath);
	}
}
