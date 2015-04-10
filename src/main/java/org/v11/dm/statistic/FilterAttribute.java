package org.v11.dm.statistic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.v11.dm.entity.UTPair;
import org.v11.dm.tool.Contants;

public class FilterAttribute {
	public void filter(String sourcePath,String targetPath){
		File readFile = new File(sourcePath);
		File witerFIle = new File(targetPath);
		String str ;
		int cnt = 0;
		BufferedReader read = null;
		BufferedWriter out = null;
		try {		
			read = new BufferedReader(new FileReader(readFile));
			out = new BufferedWriter(new FileWriter(witerFIle));
			String pre = null;
			while((str = read.readLine())!=null){
				if(cnt ++ == 0){
					out.write(str);
					out.newLine();
					continue;
				}
				if(!judgeDelete(str)){
					out.write(str);
					out.newLine();
				}
				if(cnt % 100000 == 0)System.out.println("read.."+cnt);
				pre = str;
			}
			System.out.println(pre);
			read.close();
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(cnt);
			e.printStackTrace();
		}
	}
	
	public static boolean judgeDelete(String str){
			String ele[] = str.split(",");
			int res = 0;
			for(int i=2;i<UTPair.Hours.length*4+2;i++){
				res += Integer.parseInt(ele[i]);
			}
			if(res == 0) return true; // need delete
			return false;
	}
	
	public static void main(String[] args) {

		FilterAttribute fa = new FilterAttribute();
		String f1 = "validata.csv";
		String sourcePath = Contants.write_filepath+f1;
		String targetPath = Contants.write_filepath+"filter_"+f1;
		fa.filter(sourcePath, targetPath);
	}
	
}
