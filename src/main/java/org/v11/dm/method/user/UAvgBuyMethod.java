package org.v11.dm.method.user;

import org.v11.dm.entity.Action;
import org.v11.dm.entity.TmpInfo;
import org.v11.dm.entity.User;
import org.v11.dm.method.UserMethod;

public class UAvgBuyMethod implements UserMethod{
	static int INF = 100000;
	@Override
	public void setAttribute(TmpInfo tmpInfo, User u) {
		// TODO Auto-generated method stub
		int pre = -1;
		int cnt = 0;
		for(Action r:tmpInfo.ls){
			if(r.op == 3){
				if(pre != -1){
					u.avgBuy += (r.dis-pre);
					cnt ++;
				}
				pre = r.dis;
			}
		}
		if(cnt!=0) u.avgBuy /=cnt;
		else u.avgBuy = INF;
	}

}
