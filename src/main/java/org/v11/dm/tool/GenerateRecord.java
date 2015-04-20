package org.v11.dm.tool;

import org.v11.dm.entity.OldRecord;

public class GenerateRecord {
	public void word(){
		FileUtil in = new FileUtil(Contants.read_filepath+"tianchi_mobile_recommend_train_user.csv","in");
		FileUtil out = new FileUtil(Contants.read_filepath+"record.csv", "out");
		String str = null;
		int cnt = 0;
		out.writeLine(Contants.record_attri_name);
		while((str = in.readLine())!=null){
			if(cnt ++ == 0) continue;
			if(cnt%100000 == 0) System.out.println("read .."+cnt);
			OldRecord r = OldRecord.generate(str);
			out.writeLine(r.toString());
		}
		System.out.println("read finish.");
		in.close();
		out.close();
	}
	public static void main(String[] args) {
		new GenerateRecord().word();
	}
}
