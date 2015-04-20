package org.v11.dm.method.utp;

import org.v11.dm.entity.Action;
import org.v11.dm.entity.TmpInfo;
import org.v11.dm.entity.UTPair;
import org.v11.dm.method.UTPairMethod;

/**
 * 最后一次的操作数的distance值
 * 初始化值为-1，即没有操作，值就为-1
 * @author v11
 */
public class LastActionMethod implements UTPairMethod{
	static int INF = 1000000;
	@Override
	public void setAttribute(TmpInfo tmpInfo, UTPair utp) {
		int cnt=0;
		boolean mk[] = new boolean[utp.lastAction.length];
		for(int i=0;i<utp.lastAction.length;i++) utp.lastAction[i] = INF;
		for(Action r:tmpInfo.ls){
			if(!mk[r.op]){
				cnt++;
				mk[r.op] = true;
				utp.lastAction[r.op] = r.dis;
			}
			if(cnt == 4) break;
		}
	}

}
