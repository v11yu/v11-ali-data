package org.v11.dm.entity;

public class User {
	final static public int SlideWins[] ={3*24,5*24,7*24};
	static public int BuyConversionRate[]={3*24,7*24,30*24};//3,7,30 days:buy conversion rate
	static public int LastActionSumRate[] = {1*24,3*24,7*24};//n day,action[i]_sum(n days)/action[i]_sum(30 days).
	static public int Buy_n_count[]={1*24,3*24,7*24};//只看n天的购买数量
	final static public int BuyConversionRateLength = 3;
	final static public int LastActionSumRateLen = 4;
	public double avgBuy;
	public double slideWin[] = new double [SlideWins.length];
	public double buyConversionRate[][] = new double[BuyConversionRate.length][3];
	public double lastActionSumRate[][] = new double[LastActionSumRate.length][4];
	public int buyCount[] = new int[Buy_n_count.length];
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str = str
		//.append(a2s(buyConversionRate))
		.append(a2s(slideWin))
		//.append(a2s(lastActionSumRate))
		//.append(a2s(buyCount))
		.append(avgBuy);
		return str.toString();
	}
	private StringBuilder a2s(int a[][]){
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
	private StringBuilder a2s(double a[][]){
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
