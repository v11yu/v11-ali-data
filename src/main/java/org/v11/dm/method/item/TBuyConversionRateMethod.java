package org.v11.dm.method.item;

import org.v11.dm.entity.Action;
import org.v11.dm.entity.Item;
import org.v11.dm.entity.TmpInfo;

import org.v11.dm.method.ItemMethod;

public class TBuyConversionRateMethod implements ItemMethod{

	@Override
	public void setAttribute(TmpInfo tmpInfo, Item u) {
		// TODO Auto-generated method stub
		int i=0;
		for(int days:Item.BuyConversionRate){
			int cnt[] = new int[4];
			for(Action r:tmpInfo.ls){
				if(r.dis <= days){
					cnt[r.op]++;
				}
			}
			for(int j=0;j<3;j++) u.buyConversionRate[i][j] = 1.0*cnt[3] / (cnt[j]+1);
			i++;
		}
	}

}
