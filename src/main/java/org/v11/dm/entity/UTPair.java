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
	public int dd3[] = new int[4];
	public int dd7[] = new int[4];
	public int i3[] = new int[4];
	public int i7[] = new int[4];
	public int clas = 0;
	public List<Action> ls = new ArrayList<Action>();
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str = array2String(d3).append(array2String(d5)).append(array2String(d10)).append(array2String(d16)).append(array2String(d16))
				.append(array2String(dd3)).append(array2String(dd7))
				.append(array2String(i3)).append( array2String(i7)).append(clas);
		return str.toString();
	};
	private StringBuilder array2String(int a[]){
		StringBuilder str = new StringBuilder();
		for(int i=0;i<a.length;i++){
			str.append(a[i]).append(',');
		}
		return str;
	}
	
}
