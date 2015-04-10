package org.v11.dm.method.utp;

import org.v11.dm.entity.Action;
import org.v11.dm.entity.TmpInfo;
import org.v11.dm.entity.UTPair;
import org.v11.dm.method.UTPairMethod;

/**
 * 最后N小时，操作数比总操作数
 * 
 * @author v11
 */
public class LastVsSumMethod implements UTPairMethod{

	@Override
	public void setAttribute(TmpInfo tmpInfo, UTPair utp) {
		// TODO Auto-generated method stub
		int sum[] = new int[4];
		for(int i=0;i<4;i++) sum[i]++;
		int lastOp[][] = new int[UTPair.LasVsSum.length][4];
		for(Action r : tmpInfo.ls) sum[r.op] ++;
		for(Action r : tmpInfo.ls){
			for (int i = 0; i < UTPair.LasVsSum.length; i++) {
				if(r.dis < UTPair.LasVsSum[i]){
					lastOp[i][r.op]++;
				}
			}
		}
		for (int i = 0; i < UTPair.LasVsSum.length; i++){
			for(int j=0;j<4;j++){
				utp.lasVsSum[i][j] = 1.0*lastOp[i][j] / sum[j];
			}
		}
	}
}
