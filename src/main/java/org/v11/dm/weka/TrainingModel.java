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

		Classifier model = new J48(); // new instance of tree
		
		String[] options = new String[2];
		options[0] = "-R"; // "range"
		options[1] = "1"; // first attribute
		Remove remove = new Remove(); // new instance of filter
		remove.setOptions(options); // set options
		remove.setInputFormat(data); // inform filter about dataset
		// **AFTER** setting options
		Instances newData = Filter.useFilter(data, remove); // apply filter
		Instances newValidataData = Filter.useFilter(validataData, remove);
		model.buildClassifier(newData); // build classifier
		
		Evaluation eval = new Evaluation(newData);
		eval.evaluateModel(model, newValidataData);
		File outputs = new File(evaluationFile);
		BufferedWriter out = new BufferedWriter(new FileWriter(outputs,false));
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
		out.close();
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		TrainingModel aModel = new TrainingModel();
		int T = 50;
		String path = Contants.bak_write_filepath;
		String files[]={"sameple_"+T+"_"+"filter_testing.csv","sameple_"+T+"_"+"filter_validata.csv","model.txt"};
		for(int i=0;i<files.length;i++) files[i] = path+files[i];
		aModel.traingRandomForest(files[0],files[1],files[2]);	

	}
}
