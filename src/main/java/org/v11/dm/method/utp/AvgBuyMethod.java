package org.v11.dm.method.utp;

import org.v11.dm.entity.Action;
import org.v11.dm.entity.TmpInfo;
import org.v11.dm.entity.UTPair;
import org.v11.dm.method.UTPairMethod;

public class AvgBuyMethod implements UTPairMethod{
	static int INF = 100000;
	@Override
	public void setAttribute(TmpInfo tmpInfo, UTPair utp) {
		// TODO Auto-generated method stub
		int pre = -1;
		int cnt = 0;
		for(Action r:tmpInfo.ls){
			if(r.op == 3){
				if(pre != -1){
					utp.avgBuy += (r.dis-pre);
					cnt ++;
				}
				pre = r.dis;
			}
		}
		if(cnt!=0) utp.avgBuy /=cnt;
		else utp.avgBuy = INF;
	}

}
