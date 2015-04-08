package org.v11.dm.statistic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.v11.dm.entity.OldRecord;
import org.v11.dm.tool.Contants;
/**
 * 将OldRecord数据切割成5份
 * 
 * @author v11
 */
public class SplitData {
	int N = 5;
	public void split(){
		File readFile = new File(Contants.file+"Record-user-item//tianchi_mobile_recommend_train_user.csv");
		File outputs[] = new File[N];
		BufferedWriter out[] = new BufferedWriter[N];
		try {
			int cnt = 0;
			for(int i=0;i<N;i++){
				outputs[i] = new File(Contants.record_split_filepath+i+".csv");
				out[i] = new BufferedWriter(new FileWriter(outputs[i],true));
			}
			BufferedReader cin = new BufferedReader(new FileReader(readFile));
			String str = null;
			while((str = cin.readLine())!=null){
				if(cnt++ == 0) continue;
				OldRecord r = OldRecord.generate(str);
				if(r==null ) continue;
				int idx = (int) (r.uid%N);
				out[idx].write(r.toString());
				out[idx].newLine();
				if(cnt % 10000 == 0) System.out.println("read.." + cnt);
			}
			cin.close();
			for(int i=0;i<N;i++){
				out[i].close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		SplitData s = new SplitData();
		s.split();
	}
}
