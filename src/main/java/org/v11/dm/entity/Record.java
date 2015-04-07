package org.v11.dm.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.v11.dm.tool.Contants;
import org.v11.dm.tool.TimeTool;

import weka.core.pmml.Constant;



/**
 * 每次操作的一条记录
 * 
 * @author v11
 */
public class Record implements Comparable<Record>{
	public Long uid;
	public Long tid;
	/** 操作类型,包括浏览、收藏、加购物车、购买，对应取值分别是1、2、3、4。*/
	public int op;
	/**　用前缀来判断地理位置 */
	public String geo;
	/** 商品类型*/
	public Long ity;
	public Date time;
	/** 距离最后一天多少小时 */
	public int dis;
	
	public Record(){}
	public static Record generate(String str){
		Record r = new Record();
		String v[] = str.split(",");
		if(v.length != 6){
			System.err.println(str);
			return null;
		}
		for(int i=0;i<v.length;i++) v[i] = v[i].trim();
		r.uid = Long.parseLong(v[0]);
		r.tid = Long.parseLong(v[1]);
		r.op = Integer.parseInt(v[2]) - 1;
		r.geo = v[3];
		r.ity = Long.parseLong(v[4]);
		r.time = TimeTool.getTime(v[5]);
		r.dis = (int) ((TimeTool.getTime(Contants.last_time).getTime() - r.time.getTime())/1000/3600);
		return r;
	}

	@Override
	public String toString() {
		return uid+","+tid+","+op+","+geo+","+ity+","+dis;
	}

	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Long getTid() {
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}
	public int getOp() {
		return op;
	}
	public void setOp(int op) {
		this.op = op;
	}
	public String getGeo() {
		return geo;
	}
	public void setGeo(String geo) {
		this.geo = geo;
	}
	public Long getIty() {
		return ity;
	}
	public void setIty(Long ity) {
		this.ity = ity;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public int compareTo(Record o) {
		// TODO Auto-generated method stub
		if(this.uid>o.uid) return 1;
		else if(this.uid<o.uid) return -1;
		return 0;
	}
	
}
