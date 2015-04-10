package org.v11.dm.method.utp;

import org.v11.dm.entity.Action;
import org.v11.dm.entity.TmpInfo;
import org.v11.dm.entity.UTPair;
import org.v11.dm.method.UTPairMethod;

/**
 * 该method处理
 * public int isBuy;
 * public int isDupBuy;
 * public int hasActionAfterBuy;
 * 
 * @author v11
 */
public class IsBuyMethod implements UTPairMethod{

	@Override
	public void setAttribute(TmpInfo tmpInfo, UTPair utp) {
		int cnt = 0;
		boolean f = false;
		for(Action r:tmpInfo.ls){
			if(r.op == 3) cnt ++;
			if(cnt>0 && r.op!=3) f = true;
		}
		if(cnt>0) utp.isBuy = 1;
		if(cnt>1) utp.isDupBuy = 1;
		if(f) utp.hasActionAfterBuy = 1;
	}

}
