package org.v11.dm.method.user;

import org.v11.dm.entity.Action;
import org.v11.dm.entity.TmpInfo;
import org.v11.dm.entity.UTPair;
import org.v11.dm.entity.User;
import org.v11.dm.method.UserMethod;
/**
 * n天购买数统计
 * @author v11
 *
 */
public class UBuyCountMethod implements UserMethod{

	@Override
	public void setAttribute(TmpInfo tmpInfo, User u) {
		// TODO Auto-generated method stub
		for(Action r : tmpInfo.ls){
			for (int i = 0; i < User.Buy_n_count.length; i++) {
				if (r.dis <= User.Buy_n_count[i] &&r.op == 3) {
					u.buyCount[i]++;
				}
			}
		}
	}

}
