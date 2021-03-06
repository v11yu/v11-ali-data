package org.v11.dm.tool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class F1Tools {
	public static Double f1(Set<String> pre,Set<String> res){
		int correct = 0;
		Iterator<String> iter = pre.iterator();
		while(iter.hasNext()){
			String utPair = iter.next();
			if(res.contains(utPair))
				correct++;
		}
		return f1(1.0*correct/res.size(),1.0*correct/pre.size());
	}
	public static Double f1(double precision,double recall){
		return precision*recall/(precision + recall);
	}
	public static List<String> getRes(Map<String, Double> mp,Double v){
		List<String> res = new ArrayList<String>();
		Iterator<Entry<String, Double>> iter = mp.entrySet().iterator();
		while(iter.hasNext()){
			Entry<String, Double> en = iter.next();
			if(en.getValue()>v) res.add(en.getKey());
		}
		return res;
	}
}
