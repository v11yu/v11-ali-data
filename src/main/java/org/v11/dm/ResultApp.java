package org.v11.dm;

import org.v11.dm.tool.Contants;
import org.v11.dm.weka.GenerateSubmit;
import org.v11.dm.weka.OutputResult;
import org.v11.dm.weka.TrainingModel;

public class ResultApp {
	public void work(int k){
		try{
			OutputResult aModel = new OutputResult();
			String path = Contants.write_filepath;
			String name = "train";
			if (k > 1)
				name = name + k + ".arff";
			else
				name = name + ".arff";
			String files[]={name,"submit.arff","submit-data/result_"+k+".csv"};
			String tmp = files[2];
			for (int i = 0; i < files.length; i++)
				files[i] = path + files[i];
			aModel.classifyingInstances(files[0],files[1],files[2]);
			System.out.println(k+" finished..");
			new GenerateSubmit().work(tmp);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ResultApp app = new ResultApp();
		for(int i=1;i<=5;i++){
			app.work(i);
		}
	}
}