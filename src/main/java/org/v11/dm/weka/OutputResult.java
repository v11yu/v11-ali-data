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
			String validataFile, String resultFile) throws Exception {
		Instances data = DataSource.read(testDateFile);
		data.setClassIndex(data.numAttributes() - 1);
		data.deleteAttributeAt(0);
		data.deleteAttributeAt(1);
		Classifier model = new RandomForest(); // new instance of tree
		model.buildClassifier(data); // build classifier

		Instances validata = DataSource.read(validataFile);
		validata.setClassIndex(validata.numAttributes() - 1);
		Instances outputData = new Instances(validata);
		validata.deleteAttributeAt(0);
		validata.deleteAttributeAt(1);
																		// filter
		// create copy
		//Instances labeled = new Instances(newunlabeled);
		// label instances
		for (int i = 0; i < validata.numInstances(); i++) {
			double clsLabel = model.classifyInstance(validata.instance(i));
			outputData.instance(i).setClassValue(clsLabel);
		}

		// save newly labeled data
		DataSink.write(resultFile, outputData);
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//int T = 50;
		int k = 1;
		OutputResult aModel = new OutputResult();
		String path = Contants.write_filepath;
		String name = "train";
		if(k>1) name = name +k+".arff";
		else name = name +".arff";
		String files[]={name,"submit.arff","result_"+k+".csv"};
		for(int i=0;i<files.length;i++) files[i] = path+files[i];
		aModel.classifyingInstances(files[0],files[1],files[2]);

	}
}
