package org.v11.dm.entity;

public class Action implements Comparable<Action>{
	public int dis;
	public int type;
	public Action(int dis,int type){this.dis = dis;this.type = type;}
	@Override
	public int compareTo(Action o) {
		// TODO Auto-generated method stub
		return this.dis-o.dis;
	}
}
