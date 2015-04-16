package org.v11.dm.weka;
import org.v11.dm.tool.Contants;

import weka.classifiers.Classifier;
import weka.classifiers.functions.Logistic;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSink;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
public class OutputResult {
	public void classifyingInstances(String testDateFile,
			String submitFile, String resultFile) throws Exception {
		Instances data = DataSource.read(testDateFile);
		data.setClassIndex(data.numAttributes() - 1);
		data.deleteAttributeAt(0);
		data.deleteAttributeAt(1);
		Classifier model = new RandomForest(); // new instance of tree
		model.buildClassifier(data); // build classifier

		Instances submitData = DataSource.read(submitFile);
		submitData.setClassIndex(submitData.numAttributes() - 1);
		Instances outputData = new Instances(submitData);
		submitData.deleteAttributeAt(0);
		submitData.deleteAttributeAt(1);
																		// filter
		// create copy
		//Instances labeled = new Instances(newunlabeled);
		// label instances
		int cnt1 = 0,cnt2=0;
		for (int i = 0; i < submitData.numInstances(); i++) {
			double clsLabel = model.classifyInstance(submitData.instance(i));
			System.out.println(clsLabel);
			if(clsLabel >0.9) cnt1++;
			else cnt2++;
			outputData.instance(i).setClassValue(clsLabel);
		}
		System.out.println(cnt1+" "+cnt2);

		// save newly labeled data
		DataSink.write(resultFile, outputData);
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//int T = 50;
		int k = 1;
		String path = Contants.daisy_write_filepath;

		String files[]={"sameple_50_intersection_filter_testing.csv","sameple_50_intersection_filter_validata.csv","result_"+k+".csv"};
		for(int i=0;i<files.length;i++) files[i] = path+files[i];
		OutputResult aModel = new OutputResult();
		
		aModel.classifyingInstances(files[0],files[1],files[2]);

	}
}
