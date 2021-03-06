package org.v11.dm;

import java.io.File;

import org.v11.dm.tool.Contants;
import org.v11.dm.weka.TrainingModel;

/**
 * 训练数据，跑验证集
 * @author v11
 *
 */
public class TrainingModelAPP {
	public void work(int k){
		try{
			TrainingModel aModel = new TrainingModel();
			String path = Contants.write_filepath;//设置目录，并在目录下要建一个文件夹名字validata-output
			String name = "train";
			if (k > 1)
				name = name + k + ".arff";
			else
				name = name + ".arff";
			File submitDir = new File(path+"validata-output");		
			if(!submitDir.exists()) submitDir.mkdir();
			String files[] = { name, "validata.arff", "validata-output/validata_" + k + ".txt" };
			for (int i = 0; i < files.length; i++)
				files[i] = path + files[i];
			aModel.traingRandomForest(files[0], files[1], files[2]);
			System.out.println(k+" finished..");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		TrainingModelAPP app = new TrainingModelAPP();
		for(int i=1;i<=5;i++){
			app.work(i);
		}
		app.work(15);
		app.work(20);
	}
}
