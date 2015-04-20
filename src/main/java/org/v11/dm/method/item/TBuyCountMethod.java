package org.v11.dm.method.item;

import org.v11.dm.entity.Action;
import org.v11.dm.entity.Item;
import org.v11.dm.entity.TmpInfo;
import org.v11.dm.entity.User;
import org.v11.dm.method.ItemMethod;

public class TBuyCountMethod implements ItemMethod{

	@Override
	public void setAttribute(TmpInfo tmpInfo, Item u) {
		// TODO Auto-generated method stub
		for(Action r : tmpInfo.ls){
			for (int i = 0; i < Item.Buy_n_count.length; i++) {
				if (r.dis <= Item.Buy_n_count[i] &&r.op == 3) {
					u.buyCount[i]++;
				}
			}
		}
	}

}
