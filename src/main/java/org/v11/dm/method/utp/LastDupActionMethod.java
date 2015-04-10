package org.v11.dm.method.utp;

import org.v11.dm.entity.Action;
import org.v11.dm.entity.TmpInfo;
import org.v11.dm.entity.UTPair;
import org.v11.dm.method.UTPairMethod;

public class LastDupActionMethod implements UTPairMethod{

	@Override
	public void setAttribute(TmpInfo tmpInfo, UTPair utp) {
		int cnt=0;
		int mk[] = new int[utp.lastDupAction.length];
		for(int i=0;i<utp.lastDupAction.length;i++) utp.lastDupAction[i] = -1;
		for(Action r:tmpInfo.ls){
			if(mk[r.op] == 0){
				mk[r.op] = 1;
			}
			else if(mk[r.op] == 1){
				cnt++;
				mk[r.op] = 2;
				utp.lastDupAction[r.op] = r.dis;
			}
			if(cnt == 4) break;
		}
	}

}
