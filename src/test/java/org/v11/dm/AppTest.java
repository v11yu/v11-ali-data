package org.v11.dm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.v11.dm.tool.Contants;

public class AppTest {
	public static void main(String[] args) throws Exception {
		
		File file = new File(Contants.bak_write_filepath+"tt.txt");
		File tar = new File(Contants.bak_write_filepath+"aa.txt");
		if(tar.exists()){
			tar.delete();
		}
		System.out.println(file.renameTo(tar));
		
	}
}
