package org.v11.dm.tool;

import java.util.List;

import org.v11.dm.entity.Record;

public interface OutputTool {
	/**
	 * 输出所有记录(用于记录排序后，输出)
	 * @param filepath 输出路径
	 * @param records 记录列表
	 */
	void outputAllRecord(String filepath,List<Record> records);
	/**
	 * 向文件追加一行，内容为字符串line
	 * @param line 内容
	 * @param filepath 文件路径
	 */
	void outputLine(String line,String filepath);
}
