package org.v11.dm.entity;

public class User {
	static public int SlideWins[] ={3*24,5*24,7*24};
	public double avgBuy;
	public double slideWin[] = new double [SlideWins.length];
	
	private StringBuilder aa2s(int a[][]){
		StringBuilder str = new StringBuilder();
		for(int i=0;i<a.length;i++){
			str.append(a2s(a[i]));
		}
		return str;
	}
	private StringBuilder a2s(int a[]){
		StringBuilder str = new StringBuilder();
		for(int i=0;i<a.length;i++){
			str.append(a[i]).append(',');
		}
		return str;
	}
	private StringBuilder aa2s(double a[][]){
		StringBuilder str = new StringBuilder();
		for(int i=0;i<a.length;i++){
			str.append(a2s(a[i]));
		}
		return str;
	}
	private StringBuilder a2s(double a[]){
		StringBuilder str = new StringBuilder();
		for(int i=0;i<a.length;i++){
			str.append(a[i]).append(',');
		}
		return str;
	}
}
