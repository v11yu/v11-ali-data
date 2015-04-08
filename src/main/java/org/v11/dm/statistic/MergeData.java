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

public class MergeData {
	String ff = "C://Users//damao//Desktop//ali-data//output//";
	String paths[] = {"2015-04-07-17-18-09mod5T0.csv",
			"2015-04-07-17-51-27mod5T1.csv",
			"2015-04-07-18-19-14mod5T2.csv",
			"2015-04-07-18-50-17mod5T3.csv",
			"2015-04-07-19-22-22mod5T4.csv"};
	String targetpaths = "testing.csv";
	void merge(){
		File outputFile = new File(ff+targetpaths);
		for(int i=0;i<paths.length;i++){
			File file = new File(ff+paths[i]);
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				BufferedWriter w = new BufferedWriter(new FileWriter(outputFile,true));
				String str ;
				int cnt = 0;
				if(i==0&&cnt ==0) {
					w.write(Contants.attri_name);
					w.newLine();
				}
				while((str=reader.readLine())!=null){
					str = str.replace('#', '$');
					w.write(str);
					w.newLine();
					if(cnt++ % 1000 == 0) System.out.println("read.."+cnt);
				}
				reader.close();
				w.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {
		new MergeData().merge();
	}
}
