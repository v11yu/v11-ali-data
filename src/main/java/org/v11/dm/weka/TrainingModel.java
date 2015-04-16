package org.v11.dm.weka;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;

import org.v11.dm.tool.Contants;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.Logistic;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
/**
 * 训练数据
 * 
 * 
 * @author v11
 */
public class TrainingModel {
	class Result{
		double precision, recall,fMeasure;
		Result(double precision, double recall,double fMeasure){
			this.fMeasure = fMeasure;
			this.precision = precision;
			this.recall = recall;
		}
		public String toString(){
			return "precision:" + precision +"\n"
					+"recall:" + recall +"\n"
					+"fMeasure:" + fMeasure +"\n";
			
		}
	}
	/**
	 * 
	 * @param testDateFile 训练集
	 * @param validataDataFile 验证集
	 * @param evaluationFile 参数输出
	 * @throws Exception
	 */
	public void traingRandomForest(String testDateFile,String validataDataFile,String evaluationFile) throws Exception{
		Instances data = DataSource.read(testDateFile);
		Instances validataData = DataSource.read(validataDataFile);
		data.setClassIndex(data.numAttributes() - 1);
		validataData.setClassIndex(validataData.numAttributes() - 1);
		data.deleteAttributeAt(0);
		data.deleteAttributeAt(1);
		validataData.deleteAttributeAt(0);
		validataData.deleteAttributeAt(1);
		Classifier model = new RandomForest(); // new instance of tree
		

	
		model.buildClassifier(data); // build classifier
		
		Evaluation eval = new Evaluation(data);
		eval.evaluateModel(model, validataData);
		File outputs = new File(evaluationFile);
		BufferedWriter out = new BufferedWriter(new FileWriter(outputs));
		String ans =eval.toMatrixString("result:");
		out.write(ans);
		out.newLine();
		out.write("0：");out.newLine();	
		out.write("precision:"+ eval.precision(0));
		out.newLine();
		out.write("recall:"+ eval.recall(0));
		out.newLine();
		out.write("fMeasure:"+ eval.fMeasure(0));
		out.newLine();
				
		out.write("1：");out.newLine();
		out.write("precision:"+ eval.precision(1));
		out.newLine();
		out.write("recall:"+ eval.recall(1));
		out.newLine();
		out.write("fMeasure:"+ eval.fMeasure(1));
		out.newLine();
		out.flush();
		out.close();
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int k =1;
		TrainingModel aModel = new TrainingModel();
		String path = Contants.write_filepath;
		String name = "train";
		if(k>1) name = name +k+".arff";
		else name = name +".arff";
		String files[]={name,"submit.arff","result_"+k+".csv"};
		for(int i=0;i<files.length;i++) files[i] = path+files[i];
		aModel.traingRandomForest(files[0],files[1],files[2]);	
	}
}