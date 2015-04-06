package org.v11.dm.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UTPair {
	public int d3[] = new int[4];
	public int d5[] = new int[4];
	public int d10[] = new int[4];
	public int d16[] = new int[4];
	public int d24[] = new int[4];
	public int i3[] = new int[4];
	public int i7[] = new int[4];
	public int clas = 0;
	public List<Action> ls = new ArrayList<Action>();
	@Override
	public String toString() {
		String str = "";
		str = array2String(d3)+ array2String(d5) + array2String(d10) + array2String(d16) + array2String(d24)
				+ array2String(i3) + array2String(i7) + clas;
		return str;
	};
	private String array2String(int a[]){
		String str = "";
		for(int i=0;i<a.length;i++){
			str += a[i]+",";
		}
		return str;
	}
	
}
