package org.v11.dm.tool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class F1Tools {
	public static Double f1(Set<String> pre,Set<String> res){
		return null;
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
