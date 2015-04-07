package org.v11.dm.tool;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.v11.dm.entity.Item;
import org.v11.dm.entity.Record;

public interface InputTool {
	List<Record> getRecordALL(String filepath);
	/**
	 * 从csv中读取record数据,区间[from,to]，包含左右端点
	 * @param filepath 文件路径
	 * @param from 开始时间
	 * @param to 结束时间
	 * @return record列表
	 */
	List<Record> getRecord(String filepath,Date from,Date to);
	/**
	 * 从csv中读取item信息
	 * @param filepath
	 * @return
	 */
	List<Item> getItem(String filepath);
	Map<String, Integer> getMap(String filepath);
	/**
	 * 获取 after之后的购买数据，作为测试集
	 * @param filepath 文件路径
	 * @param after 时间之后
	 * @return 购买集合<uid+'#'+tid>
	 */
	Set<String> getTestInstance(String filepath,Date after);
	Set<String> getTestInstance(String filepath, Date begin,Date end);
}
