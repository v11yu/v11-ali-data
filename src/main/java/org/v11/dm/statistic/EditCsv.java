package org.v11.dm.statistic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import org.v11.dm.entity.Record;
import org.v11.dm.tool.Contants;
/**
 * 修改Csv数据内容
 * 
 * @author v11
 */
public class EditCsv {
	/**
	 * 把dis + 48，因为原先是根据16号切，现在应该是12月18号24时
	 */
	public void editDis(){
		File file = new File(Contants.read_filepath+Contants.record_filename);
		File outfile = new File(Contants.read_filepath+"new_record.csv");
		try {
			BufferedReader read = new BufferedReader(new FileReader(file));
			BufferedWriter out  = new BufferedWriter(new FileWriter(outfile,true));
			String str;
			int cnt = 0;
			int err = 0;
			out.write(Contants.record_attri_name);
			out.newLine();
			while((str=read.readLine())!=null){
				if(cnt++ == 0) continue;
				//System.out.println(str);
				Record r = Record.generate(str);
				if(r == null){
					System.err.println(str);
					err++;
					System.err.println(err);
					continue;
				}
				r.dis+=48;
				out.write(r.toString());
				out.newLine();
				if(cnt % 10000 == 0){
					System.out.println("read ..."+cnt);
					//break;
				}
			}
			out.close();
			read.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		EditCsv e = new EditCsv();
		e.editDis();
	}
}
