package org.v11.dm.weka;
import org.v11.dm.tool.Contants;

import weka.classifiers.functions.Logistic;
import weka.classifiers.trees.J48;
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
		J48 model = new J48(); // new instance of tree
		//data.deleteAttributeAt(0);
//		String[] options = new String[2];
//		options[0] = "-R"; // "range"
//		options[1] = "1"; // first attribute
//		Remove remove = new Remove(); // new instance of filter
//		remove.setOptions(options); // set options
//		remove.setInputFormat(data); // inform filter about dataset
//		data = Filter.useFilter(data, remove); // apply
		// **AFTER** setting options

		//data.deleteAttributeAt(0);
		model.buildClassifier(data); // build classifier

		Instances validata = DataSource.read(validataFile);
		validata.setClassIndex(validata.numAttributes() - 1);
		Instances outputData = new Instances(validata);
		validata.deleteAttributeAt(0);
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
		int T = 50;
		OutputResult aModel = new OutputResult();
		String path = Contants.bak_write_filepath;
		String files[]={"sameple_"+T+"_"+"filter_testing.csv","sameple_"+T+"_"+"filter_validata.csv","OutputResult.csv"};
		for(int i=0;i<files.length;i++) files[i] = path+files[i];
		aModel.classifyingInstances(files[0],files[1],files[2]);

	}
}
