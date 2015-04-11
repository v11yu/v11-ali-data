package org.v11.dm.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ViewFile {
	public static void main(String[] args) throws Exception {
		String filename = Contants.write_filepath+"validata.csv";
		File file = new File(filename);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String str = null;
		Scanner cin = new Scanner(System.in);
		while(cin.hasNext()){
			String s = cin.next();
			str = reader.readLine();
			System.out.println(str);
			System.out.println(str.split(",").length);
			if(str == null) break;
		}
		reader.close();
	}
}
