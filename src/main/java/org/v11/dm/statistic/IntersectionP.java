package org.v11.dm.statistic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Set;

import org.v11.dm.tool.Contants;

public class IntersectionP {
	Set<String> items = new HashSet<String>();
	void init(){
		File itemFile = new File(Contants.item_pathname);
		try{
			BufferedReader reader = new BufferedReader(new FileReader(itemFile));
			String str = null;
			int cnt = 0;
			while((str = reader.readLine())!=null){
				if(cnt++ == 0) continue;
				items.add(str.split(",")[0]);
			}
			reader.close();
			System.out.println("init finished...");
			System.out.println("item size:"+items.size());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void intersection(String source,String target){
		File sourceFile = new File(source);
		File targetFile = new File(target);
		try{
			BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
			BufferedWriter out = new BufferedWriter(new FileWriter(target));
			String str;
			int cnt = 0;
			int has = 0;
			while((str = reader.readLine())!=null){
				if(cnt++ == 0||items.contains(getItem(str))){
					out.write(str);
					out.newLine();
					String ls[] = str.split(",");
					if(cnt>1 && ls[ls.length-1].equals("1"))has++;
				}
				if(cnt % 500000 == 0)System.out.println("read..."+cnt);
			}
			System.out.println("P buy count:"+has);
			reader.close();
			sourceFile.delete();
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	String getItem(String str){
		return str.split(",")[1];	
	}
	public static void main(String[] args) {
		String files[] ={"data14_clas15.csv"
				,"data15_clas16.csv"
				,"data16_clas17.csv"
				,"data17_clas18.csv"
				,"data18_clas19.csv"};
		IntersectionP ip = new IntersectionP();
		ip.init();
		for(int i=0;i<files.length;i++){
			String path = Contants.write_filepath;
			String name = files[i];
			String source = path+name;
			String target = path+"intersection_"+name;
			ip.intersection(source, target);
		}
	}
}
