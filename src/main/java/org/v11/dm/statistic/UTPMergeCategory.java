package org.v11.dm.statistic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import org.v11.dm.tool.Contants;
import org.v11.dm.tool.FileUtil;

public class UTPMergeCategory {
	String BAK = Contants.bak_write_filepath;
	String OUT = Contants.write_filepath;
	Map<String, String> cmap = new HashMap<String, String>();
	Map<String, String> tcmap = new HashMap<String, String>();
	void initItemCategoryMap(){
		tcmap.clear();
		FileUtil in = new FileUtil(Contants.item_pathname, "in");
		String str;
		int cnt = 0;
		while((str = in.readLine())!=null){
			if(cnt++ == 0) continue;
			String ls[] = str.split(",");
			tcmap.put(ls[0], ls[2]);
		}
		System.out.println("item-category map finish...");
	}
	public UTPMergeCategory(){
		initItemCategoryMap();
	}
	void initCmap(String ufile) {
		cmap.clear();
		File file = new File(BAK + ufile);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String str;
			while ((str = reader.readLine()) != null) {
				String ls[] = str.split(",", 2);
				cmap.put(ls[0], ls[1]);
			}
			System.out.println("cmap finish..");
			System.out.println(cmap.get("item_type"));
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void merge(String utpfile, String ufile) {
		initCmap(ufile);
		File file = new File(OUT + utpfile);
		File outfile = new File(OUT + "merge_tid_" + utpfile);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			BufferedWriter out = new BufferedWriter(new FileWriter(outfile));
			String str = null;
			int cnt = 0;
			while ((str = reader.readLine()) != null) {
				String ls[] = str.split(",", 3);
				out.write(ls[0] + "," + ls[1] + ",");
				String cid = tcmap.get(ls[1]);
				out.write(cmap.get(cid) + ",");
				out.write(ls[2]);
				out.newLine();
			}
			reader.close();
			out.flush();
			out.close();
			System.out.println(utpfile + " finished..");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
