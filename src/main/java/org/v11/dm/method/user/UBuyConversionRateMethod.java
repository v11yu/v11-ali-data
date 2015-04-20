package org.v11.dm.method.user;

import org.v11.dm.entity.Action;
import org.v11.dm.entity.TmpInfo;
import org.v11.dm.entity.User;
import org.v11.dm.method.UserMethod;

/**
 * n天转换率
 * 购买／收藏
 * 购买／购物车
 * 购买／点击
 * @author v11
 *
 */
public class UBuyConversionRateMethod implements UserMethod{

	@Override
	public void setAttribute(TmpInfo tmpInfo, User u) {
		// TODO Auto-generated method stub
		int i=0;
		for(int days:User.BuyConversionRate){
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
