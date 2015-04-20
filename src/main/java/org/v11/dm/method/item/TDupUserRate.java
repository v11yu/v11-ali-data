package org.v11.dm.method.item;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.v11.dm.entity.Action;
import org.v11.dm.entity.Item;
import org.v11.dm.entity.TmpInfo;
import org.v11.dm.method.ItemMethod;

/**
 * 重复购买用户数／购买用户数
 * @author v11
 *
 */
public class TDupUserRate implements ItemMethod{

	@Override
	public void setAttribute(TmpInfo tmpInfo, Item u) {
		// TODO Auto-generated method stub
		Map<Long,Integer> mp = new HashMap<Long,Integer>();
		int buyUserCnt = 0;
		int dup=0;
		for(Action r:tmpInfo.ls){
			if(r.op == 3){
				if(!mp.containsKey(r.tid)){
					buyUserCnt ++;
					mp.put(r.tid, 1);
				}
				else if(mp.get(r.tid) == 1){
					dup++;
					mp.put(r.tid,2);
				}
			}
		}
		if(buyUserCnt == 0) u.dupUserRate = 0;
		else u.dupUserRate = 1.0*dup/buyUserCnt;
	}

}
