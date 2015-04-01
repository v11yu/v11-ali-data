package org.v11.dm.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.v11.dm.tool.TimeTool;



/**
 * 每次操作的一条记录
 * 
 * @author v11
 */
public class Record {
	Long uid;
	Long tid;
	/** 操作类型,包括浏览、收藏、加购物车、购买，对应取值分别是1、2、3、4。*/
	int op;
	/**　用前缀来判断地理位置 */
	String geo;
	/** 商品类型*/
	Long ity;
	Date time;
	
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
		r.op = Integer.parseInt(v[2]);
		r.geo = v[3];
		r.ity = Long.parseLong(v[4]);
		r.time = TimeTool.getTime(v[5]);
		return r;
	}

	@Override
	public String toString() {
		return "Record [uid=" + uid + ", tid=" + tid + ", op=" + op + ", geo="
				+ geo + ", ity=" + ity + ", time=" + time + "]";
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
	
}
