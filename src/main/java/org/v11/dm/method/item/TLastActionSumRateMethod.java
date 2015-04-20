package org.v11.dm.method.item;

import org.v11.dm.entity.Action;
import org.v11.dm.entity.Item;
import org.v11.dm.entity.TmpInfo;
import org.v11.dm.method.ItemMethod;

public class TLastActionSumRateMethod implements ItemMethod{

	@Override
	public void setAttribute(TmpInfo tmpInfo, Item u) {
		// TODO Auto-generated method stub
		for(int i=0;i<Item.LastActionSumRate.length;i++){
			int tim = Item.LastActionSumRate[i];
			int sum[] = new int[4];
			for(int j=0;j<4;j++) sum[j] = 1;
			for(Action r:tmpInfo.ls){
				if(r.dis<tim) {
					u.lastActionSumRate[i][r.op]+=1;
				}
				sum[r.op]++;
			}
			for(int j=0;j<4;j++) u.lastActionSumRate[i][j] /= sum[j];
		}
		
	}

}
