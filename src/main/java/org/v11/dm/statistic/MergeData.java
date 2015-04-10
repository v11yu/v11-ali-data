package org.v11.dm.statistic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.v11.dm.entity.Record;
import org.v11.dm.tool.Contants;

/**
 * 合并数据
 * 
 * @author v11
 */
public class MergeData {
	/**
	 * 将多个文本合并成一个文本
	 * 
	 * @param targetpaths
	 *            合并后的目标文件名
	 * @param paths
	 *            待合并文件名
	 * @param ff
	 *            文件夹路径
	 */
	void merge(String targetpaths, String paths[], String ff, String firstLine) {
		File outputFile = new File(ff + targetpaths);
		boolean f = true;
		int ttt = 0;
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter(outputFile));
			for (int i = 0; i < paths.length; i++) {
				File file = new File(ff + paths[i]);
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String str;
				int cnt = 0;
				if (f) {
					w.write(firstLine);
					w.newLine();
					f = false;
				}
				while ((str = reader.readLine()) != null) {
					w.write(str);
					w.newLine();
					if (cnt++ % 100000 == 0)
						System.out.println("read.." + cnt);
				}
				reader.close();
			}
			w.flush();
			w.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 合并特征属性
	 */
	public void mergeAttribute(String targetpaths, int T) {
		String ff = Contants.write_filepath;
		String paths[] = new String[T];
		for (int i = 0; i < T; i++)
			paths[i] = i + ".csv";
		merge(targetpaths, paths, ff, Contants.getAttributeName());
	}

	/**
	 * 合并记录
	 */
	public void mergeRecord() {
		String ff = Contants.read_filepath;
		String paths[] = { "0.csv", "1.csv", "2.csv", "3.csv", "4.csv" };
		String targetpaths = "record.csv";
		merge(targetpaths, paths, ff, Contants.record_attri_name);
	}

	public static void main(String[] args) {
		MergeData m = new MergeData();
		m.mergeAttribute("validata.csv", 5);

	}
}
