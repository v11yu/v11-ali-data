package org.v11.dm.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
/**
 * 判断 attributeName是否value 每行个数相同
 * @author v11
 *
 */
public class JudgeAttrValue {
	public static void main(String[] args) throws Exception {
		String u = "udata18_clas19.csv";
		String t = "tdata18_clas19.csv";
		String ut= "data14_clas15.csv";
		String file = Contants.write_filepath+"train15_17.csv";
		FileUtil in = new FileUtil(file,"in");
		System.out.println(in.readLine().split(",").length);
		System.out.println(in.readLine().split(",").length);
		in.close();
		//String str = ",uid,uid_buy_cart_720_720,uid_buy_cart_168_720,uid_buy_cart_72_720,uid_buy_cart_168_168,uid_buy_cart_72_168,uid_buy_cart_72_72,uid_buy_fav_720_720,uid_buy_fav_168_720,uid_buy_fav_72_720,uid_buy_fav_168_168,uid_buy_fav_72_168,uid_buy_fav_72_72,uid_buy_click_720_720,uid_buy_click_168_720,uid_buy_click_72_720,uid_buy_click_168_168,uid_buy_click_72_168,uid_buy_click_72_72,uid_click3,uid_click7,uid_click24,uid_click72,uid_click168,uid_click720,uid_fav3,uid_fav7,uid_fav24,uid_fav72,uid_fav168,uid_fav720,uid_cart3,uid_cart7,uid_cart24,uid_cart72,uid_cart168,uid_cart720,uid_buy3,uid_buy7,uid_buy24,uid_buy72,uid_buy168,uid_buy720";
		//System.out.println(str.split(",").length);
	}
}
