package org.v11.dm.method.user;

import org.v11.dm.entity.Action;
import org.v11.dm.entity.TmpInfo;
import org.v11.dm.entity.User;
import org.v11.dm.method.UserMethod;
/**
 * 最后n天行为［i］/总行为数［i］
 * @author v11
 *
 */
public class ULastActionSumRateMethod implements UserMethod{

	@Override
	public void setAttribute(TmpInfo tmpInfo, User u) {
		// TODO Auto-generated method stub
		for(int i=0;i<User.LastActionSumRate.length;i++){
			int tim = User.LastActionSumRate[i];
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
