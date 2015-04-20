package org.v11.dm;

import java.io.File;

import org.v11.dm.tool.Contants;
import org.v11.dm.weka.GenerateSubmit;
import org.v11.dm.weka.OutputResult;
import org.v11.dm.weka.TrainingModel;

public class ResultApp {
	public void work(int k){
		try{
			OutputResult aModel = new OutputResult();
			String path = Contants.write_filepath; //设置目录
			String name = "train";
			if (k > 1)
				name = name + k + ".arff";
			else
				name = name + ".arff";
			File submitDir = new File(path+"submit-data");
			
			if(!submitDir.exists()) submitDir.mkdir();
			String files[]={name,"submit.arff","submit-data/result_"+k+".csv"};
			for (int i = 0; i < files.length; i++)
				files[i] = path + files[i];
			aModel.classifyingInstances(files[0],files[1],files[2],null);
			System.out.println(k+" finished..");
			new GenerateSubmit().work("result_"+k+".csv",path+"submit-data/");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ResultApp app = new ResultApp();
		for(int i=1;i<=5;i++){
			app.work(i);
		}
		app.work(15);
		app.work(20);
	}
}
