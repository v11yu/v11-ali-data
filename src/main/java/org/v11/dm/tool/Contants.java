package org.v11.dm.tool;

import org.v11.dm.entity.Item;
import org.v11.dm.entity.UTPair;
import org.v11.dm.entity.User;

public class Contants {
	private static String attri_name = null;
	private static String user_attri_name = null;
	private static String item_attri_name = null;
	private static String get(String name){return name+",";}
	private static String getls(int len,String name){
		int cnt = 0;
		String str = "";
		for(int i=0;i<len;i++){
			str = str+name+cnt+",";
			cnt++;
		}
		return str;
	}
	/*
	 * attribute name
	 */
	public static final String record_attri_name="uid,tid,op,geo,item_type,dis";
	public static String getAttributeName(){
		String str = "uid,tid,";
		UTPair utp = new UTPair();
		if(attri_name == null){
			attri_name = str+getls(utp.hours.length*4,"utp_h")
					+getls(utp.intervals.length,"utp_interval")
					+getls(utp.lasVsSum.length*4,"utp_la")
					+getls(utp.lastAction.length,"utp_at")
					//+getls(utp.lastDupAction.length,"utp_atdup")
					+get("isbuy")
					+get("isDupBuy")
					+get("hasActionAfterBuy")
					+get("avgBuy")
					+"clas";
		}
		return attri_name;		
	}
	public static String getUserAttributeName(){
		String str = "uid,";
		User user = new User();
		if(user_attri_name == null){
			user_attri_name = str
					//+getls(user.BuyConversionRate.length*User.BuyConversionRateLength,"u_buyConversionRate")
					+getls(user.slideWin.length,"u_slide")
					//+getls(user.LastActionSumRate.length*User.LastActionSumRateLen,"u_lastActionSumRate")
					//+getls(user.Buy_n_count.length,"u_buyCount")
					+"u_avgBuy";
		}
		return user_attri_name;
	}
	public static String getItemAttributeName(){
		String str = "tid,";
		if(item_attri_name == null){
			item_attri_name = str
					+getls(Item.BuyConversionRate.length*Item.BuyConversionRateLength,"t_buyConversionRate")
					+getls(Item.LastActionSumRate.length*Item.LastActionSumRateLen,"t_lastActionSumRate")
					+getls(Item.Buy_n_count.length,"t_buyCount")
					+"t_dupUserRate";
		}
		return item_attri_name;
	}
	/*
	 * time
	 */
	public static final String time16 = "2014-12-16 24";
	public static final String time17 = "2014-12-17 24";
	public static final String time18 = "2014-12-18 24";
	public static final String time19 = "2014-12-19 24";
	public static final String last_time = time16;
	public static final String class_time = time17;
	public static final String validata_time = time18;
	/*
	 * workspace path
	 */
	public static final String home_file = "C://Users//v11/Desktop//2015阿里大数据//";
	public static final String mac_file ="/Users/v11/Documents/ali-data/";
	public static final String of_file = "C://Users//damao//Desktop//ali-data//";
	public static final String file = mac_file;
	/*
	 * filepath
	 */
	public static final String read_filepath = file+"tianchi_mobile_recommend_train_user//";
	public static final String write_filepath = file+"output//";
	public static final String bak_write_filepath = file+"output-bak//";
	public static final String daisy_write_filepath = file+"daisy-workspace//";
	/*
	 * fileName
	 */
	public static final String item_pathname = file+"tianchi_mobile_recommend_train_item.csv";
	public static final String record_filename = "record.csv";
	public static final String record_sortByTid_filename = "item_record.csv";

	public static void main(String[] args) {
		System.out.println(getAttributeName());
		String str = getAttributeName();
		System.out.println(str.split(",").length);
	}
}
